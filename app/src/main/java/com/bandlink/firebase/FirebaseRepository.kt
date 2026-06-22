package com.bandlink.firebase

import com.google.firebase.auth.FirebaseAuth
import com.bandlink.firebase.FirebaseRepository
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

class FirebaseRepository {

    private val auth = FirebaseAuth.getInstance()

    fun registerUser(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    onResult(true, "Registration Successful")
                } else {
                    onResult(
                        false,
                        task.exception?.message ?: "Registration Failed"
                    )
                }
            }
    }
}