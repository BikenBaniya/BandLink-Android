package com.bandlink.screens

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavController) {


    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = tween(1000)
            ) + slideInVertically(
                animationSpec = tween(1000),
                initialOffsetY = { 300 }
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "🎸 BandLink",
                    color = Color(0xFF9C4DFF),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Connect Bands • Find Gigs • Build Music",
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        navController.navigate("createBand")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Create Band")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        navController.navigate("viewBands")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("View Bands")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        navController.navigate("createEvent")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Create Event")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        navController.navigate("viewEvents")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("View Events")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {

                        FirebaseAuth.getInstance().signOut()

                        navController.navigate("login") {
                            popUpTo("home") {
                                inclusive = true
                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Logout")
                }
            }
        }
    }


}
