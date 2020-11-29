package com.example.musicapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.Firebase
import com.example.musicapp.R
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init() {
        sign_up_button.setOnClickListener { signUp() }
    }

    private fun signUp() {

        val email = email_edit_text.editText?.text.toString()
        val confirm = confirm_password.editText?.text.toString()
        val password = password_edit_text.editText?.text.toString()

        if (confirm == password) {
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Success", Toast.LENGTH_LONG)
                            .show()

                        startActivity(
                            Intent(
                                this,
                                LoginActivity::class.java
                            )
                        )
                        finish()
                    }

                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG)
                        .show()
                }
        } else {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG)
                .show()
        }

    }
}
