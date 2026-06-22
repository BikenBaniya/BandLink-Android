package com.bandlink.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bandlink.firebase.FirebaseRepository
import com.bandlink.models.Band
import com.google.firebase.database.FirebaseDatabase

@Composable
fun UpdateBandScreen(
    navController: NavController,
    bandId: String
) {


    var bandName by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    val context = LocalContext.current
    val firebaseRepository = FirebaseRepository()

    LaunchedEffect(Unit) {

        FirebaseDatabase.getInstance()
            .getReference("bands")
            .child(bandId)
            .get()
            .addOnSuccessListener { snapshot ->

                bandName =
                    snapshot.child("bandName")
                        .getValue(String::class.java) ?: ""

                genre =
                    snapshot.child("genre")
                        .getValue(String::class.java) ?: ""

                location =
                    snapshot.child("location")
                        .getValue(String::class.java) ?: ""
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))
            TextButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Back")
            }

            Text(
                text = "✏️ Update Band",
                color = Color(0xFF9C4DFF),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    OutlinedTextField(
                        value = bandName,
                        onValueChange = { bandName = it },
                        label = { Text("Band Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = genre,
                        onValueChange = { genre = it },
                        label = { Text("Genre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = location,
                        onValueChange = { location = it },
                        label = { Text("Location") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {

                            val updatedBand = Band(
                                bandId = bandId,
                                bandName = bandName,
                                genre = genre,
                                location = location
                            )

                            firebaseRepository.updateBand(
                                updatedBand
                            ) { success, message ->

                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()

                                if (success) {
                                    navController.popBackStack()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Changes")
                    }
                }
            }
        }
    }


}
