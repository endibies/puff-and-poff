package com.example.puffandpoof

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puffandpoof.Fragment.ProfileFragment
import com.example.puffandpoof.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // Set click listener for the login button
        binding.LoginBT.setOnClickListener {
            val username = binding.UsernameET.text.toString()
            val password = binding.PasswordET.text.toString()

            // Validate username and password fields
            if (username.isEmpty() || password.isEmpty()) {
                showToast("Please fill in all fields")
            } else {
                // Authenticate user
                if (authenticateUser(username, password)) {
                    // Redirect to HomeActivity
                    redirectToHome(username)
                } else {
                    showToast("Incorrect Username or Password")
                }
            }
        }

        // Set click listener for the "Register here" text view
        binding.RegisBT.setOnClickListener {
            // Redirect to RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to authenticate user
    private fun authenticateUser(username: String, password: String): Boolean {
        // Retrieve stored credentials
        val storedUsername = sharedPreferences.getString("username", "")
        val storedPassword = sharedPreferences.getString("password", "")

        // Compare provided credentials with stored credentials after trimming
        val trimmedStoredUsername = storedUsername?.trim()
        val trimmedStoredPassword = storedPassword?.trim()

        return if (username == trimmedStoredUsername && password == trimmedStoredPassword) {
            showToast("Welcome, $username!")
            true
        } else {
            false
        }
    }

    // Function to show toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Function to redirect to HomeActivity
    private fun redirectToHome(username: String) {
        // Replace MainActivity::class.java with your actual home activity class
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", username) // Pass username to MainActivity

        startActivity(intent)
        finish() // Finish the LoginActivity
    }

    private fun toprofile(username: String,phone: String){
        val phonenumber = sharedPreferences.getString("phoneNumber","23")
        val fragment = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString("username",username)
                putString("phone",phonenumber)

            }
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.profileFragment,fragment)
            addToBackStack(null)
            commit()
        }
    }

}
