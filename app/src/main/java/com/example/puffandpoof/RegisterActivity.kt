package com.example.puffandpoof
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puffandpoof.Fragment.ProfileFragment
import com.example.puffandpoof.databinding.ActivityRegisterBinding
import com.example.puffandpoof.model.profile
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // Initially, disable the registration button
        binding.RegisBT.isEnabled = false

        // Set onClickListener for the Register Button
        binding.RegisBT.setOnClickListener {
            val username = binding.UsernameET.text.toString()
            val email = binding.EmailET.text.toString()
            val password = binding.PasswordET.text.toString()
            val phoneNumber = binding.PhoneET.text.toString()

            // Validate all fields
            if (validateFields(username, email, password, phoneNumber)) {
                // Check if the username is unique
                if (!isUsernameUnique(username)) {
                    showToast("Username is already taken")
                } else {
                    // Save user info in SharedPreferences
                    saveUserInfo(username, email, password, phoneNumber)

                    // Navigate to the ProfileFragment
                    val profileFragment = ProfileFragment().apply {
                        // Buat objek profile dengan konstruktor yang sesuai
                        val profile = profile(username, email, phoneNumber)
                        arguments = Bundle().apply {
                            // Kirim objek profile ke ProfileFragment melalui Bundle
                            putParcelable("profile", profile)
                        }
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .commit()
                    showToast("Registration Successful")
                    navigateToLogin()
                }
            }
        }

        // Set onClickListener for the "Already have an account? Log in here" button
        binding.LoginBT.setOnClickListener {
            // Move to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Add text change listeners to enable/disable registration button based on field completion
        binding.UsernameET.addTextChangedListener(textWatcher)
        binding.EmailET.addTextChangedListener(textWatcher)
        binding.PasswordET.addTextChangedListener(textWatcher)
        binding.PhoneET.addTextChangedListener(textWatcher)
    }

    // TextWatcher to enable/disable registration button
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            checkFields()
        }
    }

    // Function to validate all fields
    private fun validateFields(username: String, email: String, password: String, phoneNumber: String): Boolean {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            showToast("Please fill in all fields")
            return false
        }
        if (password.length < 8) {
            showToast("Password must be at least 8 characters long")
            return false
        }
        if (!email.endsWith("@puff.com")) {
            showToast("Email must end with '@puff.com'")
            return false
        }
        if (phoneNumber.length !in 11..13) {
            showToast("Phone number length must be between 11 and 13 characters")
            return false
        }
        return true
    }

    // Function to check if the username is unique
    private fun isUsernameUnique(username: String): Boolean {
        // Placeholder logic to check uniqueness
        // You should implement your own logic to check against the database or other storage
        return true
    }

    // Function to save user info in SharedPreferences
    private fun saveUserInfo(username: String, email: String, password: String, phoneNumber: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password) // Save the password
        editor.putString("phoneNumber", phoneNumber)
        editor.apply()
    }

    // Function to show toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Function to enable/disable registration button based on field completion
    private fun checkFields() {
        val username = binding.UsernameET.text.toString()
        val email = binding.EmailET.text.toString()
        val password = binding.PasswordET.text.toString()
        val phoneNumber = binding.PhoneET.text.toString()

        binding.RegisBT.isEnabled = username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty()
    }

    // Function to navigate to the Login screen
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity
    }
}


//
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.widget.RadioButton
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.puffandpoof.databinding.ActivityRegisterBinding
//import com.example.puffandpoof.model.profile
//
//class RegisterActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityRegisterBinding
//    private lateinit var sharedPreferences: SharedPreferences
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
//
//        // Initially, disable the registration button
//        binding.RegisBT.isEnabled = false
//
//        // Set onClickListener for the Register Button
//        binding.RegisBT.setOnClickListener {
//            val username = binding.UsernameET.text.toString()
//            val email = binding.EmailET.text.toString()
//            val password = binding.PasswordET.text.toString()
//            val phoneNumber = binding.PhoneET.text.toString()
//            val gender = findViewById<RadioButton>(binding.GenderRG.checkedRadioButtonId)?.text.toString()
//
//            // Validate all fields
//            if (validateFields(username, email, password, phoneNumber)) {
//                // Check if the username is unique
//                if (!isUsernameUnique(username)) {
//                    showToast("Username is already taken")
//                } else {
//                    // Save user info in SharedPreferences
//
//                    saveUserInfo(username, email, password, phoneNumber)
//
//                    // Navigate to the Login Form
//                    navigateToLogin()
//                }
//            }
//        }
//
//        // Set onClickListener for the "Already have an account? Log in here" button
//        binding.LoginBT.setOnClickListener {
//            // Move to com.example.aol_mcs.LoginActivity
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
//
//        // Add text change listeners to enable/disable registration button based on field completion
//        binding.UsernameET.addTextChangedListener(textWatcher)
//        binding.EmailET.addTextChangedListener(textWatcher)
//        binding.PasswordET.addTextChangedListener(textWatcher)
//        binding.PhoneET.addTextChangedListener(textWatcher)
//    }
//
//    // TextWatcher to enable/disable registration button
//    private val textWatcher = object : TextWatcher {
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//
//        override fun afterTextChanged(s: Editable?) {
//            checkFields()
//        }
//    }
//
//    // Function to validate all fields
//    private fun validateFields(username: String, email: String, password: String, phoneNumber: String): Boolean {
//        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
//            showToast("Please fill in all fields")
//            return false
//        }
//        if (password.length < 8) {
//            showToast("Password must be at least 8 characters long")
//            return false
//        }
//        if (!email.endsWith("@puff.com")) {
//            showToast("Email must end with '@puff.com'")
//            return false
//        }
//        if (phoneNumber.length !in 11..13) {
//            showToast("Phone number length must be between 11 and 13 characters")
//            return false
//        }
//        return true
//    }
//
//    // Function to check if the username is unique
//    private fun isUsernameUnique(username: String): Boolean {
//        // Placeholder logic to check uniqueness
//        // You should implement your own logic to check against the database or other storage
//        return true
//    }
//
//    // Function to save user info in SharedPreferences
//    private fun saveUserInfo(username: String, email: String, password: String, phoneNumber: String) {
//        val editor = sharedPreferences.edit()
//        editor.putString("username", username)
//        editor.putString("email", email)
//        editor.putString("password", password) // Save the password
//        editor.putString("phoneNumber", phoneNumber)
//        editor.apply()
//
//
//    }
//
//    // Function to navigate to the Login Form
//    private fun navigateToLogin() {
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish() // Finish the current activity
//        showToast("Registration Successful")
//    }
//
//    // Function to show toast messages
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//
//    // Function to enable/disable registration button based on field completion
//    private fun checkFields() {
//        val username = binding.UsernameET.text.toString()
//        val email = binding.EmailET.text.toString()
//        val password = binding.PasswordET.text.toString()
//        val phoneNumber = binding.PhoneET.text.toString()
//
//        binding.RegisBT.isEnabled = username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty()
//    }
//}