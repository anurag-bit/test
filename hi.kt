package com.anurag.slimchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        btnlogin = findViewById(R.id.login_button)
        btnsignup = findViewById(R.id.signup_button)
        edtPassword = findViewById(R.id.editTextTextPassword)
        edtEmail = findViewById(R.id.editTextTextEmailAddress)

        btnsignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()


            login(email,password)
        }



    }

    private fun login(email: String?, password: String?){
        //login framework
        if (password != null) {
            if (email != null) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //login
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            finish()
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this@LoginActivity, "User does not exist", Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }

    }



}