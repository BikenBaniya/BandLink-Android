package com.bandlink.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bandlink.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(Unit) {

        delay(2000)

        if (FirebaseAuth.getInstance().currentUser != null) {

            navController.navigate("home") {
                popUpTo("splash") {
                    inclusive = true
                }
            }

        } else {

            navController.navigate("login") {
                popUpTo("splash") {
                    inclusive = true
                }
            }

        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.guitar),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "BandLink",
                color = Color(0xFF9C4DFF),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Connect Bands • Find Gigs • Build Music",
                color = Color.LightGray
            )
        }
    }
}