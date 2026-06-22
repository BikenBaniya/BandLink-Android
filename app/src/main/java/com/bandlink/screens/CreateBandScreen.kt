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
import com.bandlink.firebase.FirebaseRepository

@Composable
fun CreateBandScreen() {

    var bandName by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    val context = LocalContext.current
    val firebaseRepository = FirebaseRepository()

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

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Create Band",
                color = Color(0xFF9C4DFF),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

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

                    firebaseRepository.createBand(
                        bandName = bandName,
                        genre = genre,
                        location = location
                    ) { success, message ->

                        Toast.makeText(
                            context,
                            message,
                            Toast.LENGTH_LONG
                        ).show()

                    }

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Create Band")
            }
        }
    }
}