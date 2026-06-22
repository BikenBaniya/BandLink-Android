package com.bandlink.firebase

import com.bandlink.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.bandlink.models.Band
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
    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    onResult(true, "Login Successful")
                } else {
                    onResult(
                        false,
                        task.exception?.message ?: "Login Failed"
                    )
                }
            }
    }
    fun createBand(
        bandName: String,
        genre: String,
        location: String,
        onResult: (Boolean, String) -> Unit
    ) {

        val bandId = FirebaseDatabase.getInstance()
            .getReference("bands")
            .push()
            .key ?: ""

        val band = Band(
            bandId = bandId,
            bandName = bandName,
            genre = genre,
            location = location
        )

        FirebaseDatabase.getInstance()
            .getReference("bands")
            .child(bandId)
            .setValue(band)
            .addOnSuccessListener {
                onResult(true, "Band Created")
            }
            .addOnFailureListener {
                onResult(false, it.message ?: "Error")
            }
    }
    fun deleteBand(
        bandId: String,
        onResult: (Boolean, String) -> Unit
    ) {

        FirebaseDatabase.getInstance()
            .getReference("bands")
            .child(bandId)
            .removeValue()
            .addOnSuccessListener {
                onResult(true, "Band Deleted")
            }
            .addOnFailureListener {
                onResult(false, it.message ?: "Delete Failed")
            }
    }
}