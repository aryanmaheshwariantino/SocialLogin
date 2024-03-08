package com.aryan.sociallogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.aryan.sociallogin.databinding.ActivityGoogleLoginBinding
import com.aryan.sociallogin.databinding.ActivityHomeScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutId.setOnClickListener {
            Log.d("Logout", "Logout button clicked")
            signOut()
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }
    private fun signOut() {
//        Log.d("Logout", "Signing out")
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, GoogleLogin::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Sign out failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}