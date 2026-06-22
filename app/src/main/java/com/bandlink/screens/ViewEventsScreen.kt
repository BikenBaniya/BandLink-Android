package com.bandlink.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bandlink.firebase.FirebaseRepository
import com.bandlink.models.Event
import com.google.firebase.database.*

@Composable
fun ViewEventsScreen(
    navController: NavController
) {


    var eventList by remember {
        mutableStateOf(listOf<Event>())
    }

    val context = LocalContext.current
    val firebaseRepository = FirebaseRepository()

    LaunchedEffect(Unit) {

        FirebaseDatabase.getInstance()
            .getReference("events")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val tempList = mutableListOf<Event>()

                    for (child in snapshot.children) {

                        val event = child.getValue(Event::class.java)

                        if (event != null) {
                            tempList.add(event)
                        }
                    }

                    eventList = tempList
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TextButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Back")
            }

            Text(
                text = "🎤 Events",
                color = Color(0xFF9C4DFF),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(eventList) { event ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A1A1A)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "🎤 ${event.eventName}",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "📍 ${event.venue}",
                            color = Color.LightGray
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "📅 ${event.date}",
                            color = Color.LightGray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            Button(
                                onClick = {

                                    navController.navigate(
                                        "updateEvent/${event.eventId}"
                                    )

                                }
                            ) {
                                Text("Edit")
                            }

                            Button(
                                onClick = {

                                    firebaseRepository.deleteEvent(
                                        event.eventId
                                    ) { success, message ->

                                        Toast.makeText(
                                            context,
                                            message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }


}
