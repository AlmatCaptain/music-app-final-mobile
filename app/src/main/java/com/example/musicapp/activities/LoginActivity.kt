package com.example.musicapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.Firebase
import com.example.musicapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        sign_in_button.setOnClickListener { signIn() }
        sign_up_link.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegistrationActivity::class.java
                )
            )
            finish()
        }
    }

    private fun signIn() {
        val email = email_edit_text.text.toString()
        val password = password_edit_text.text.toString()


        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, FavActivity::class.java))
                    finish()
                    return@addOnCompleteListener
                }

                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG)
                    .show()
            }

    }
}
