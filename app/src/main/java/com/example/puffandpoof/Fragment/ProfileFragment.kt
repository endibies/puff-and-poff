package com.example.puffandpoof.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.puffandpoof.databinding.FragmentProfileBinding
import com.example.puffandpoof.model.profile
import android.content.SharedPreferences
import com.example.puffandpoof.R
import androidx.navigation.fragment.findNavController
import com.example.puffandpoof.LoginActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Initialize other views and variables
        sharedPreferences = requireActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // Retrieve data from SharedPreferences
        val storedUsername = sharedPreferences.getString("username", "")
        val storedEmail = sharedPreferences.getString("email", "")
        val storedPhoneNumber = sharedPreferences.getString("phoneNumber", "")

        // Display retrieved data in TextViews
        binding.usernameTextView.text = storedUsername
        binding.emailTextView.text = storedEmail
        binding.phoneTextView.text = storedPhoneNumber
        // Handle logout button click
        binding.logoutButton.setOnClickListener {
            logout()
        }

        return binding.root
    }

    private fun logout() {
        // Clear any user-related data or preferences if needed

        // Navigate to the login activity
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)

        // Finish the current activity to prevent the user from returning to the profile screen
        activity?.finish()
    }
}




