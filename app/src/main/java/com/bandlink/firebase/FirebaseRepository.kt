package com.bandlink.firebase

import com.bandlink.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepository {

    private val auth = FirebaseAuth.getInstance()

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser?.uid ?: ""

                    val user = User(
                        uid = uid,
                        name = name,
                        email = email
                    )

                    FirebaseDatabase.getInstance()
                        .getReference("users")
                        .child(uid)
                        .setValue(user)
                        .addOnSuccessListener {
                            onResult(true, "Registration Successful")
                        }
                        .addOnFailureListener {
                            onResult(false, it.message ?: "Database Error")
                        }

                } else {

                    onResult(
                        false,
                        task.exception?.message ?: "Registration Failed"
                    )
                }
            }
    }
}