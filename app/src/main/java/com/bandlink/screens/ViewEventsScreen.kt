package com.bandlink.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bandlink.models.Event
import com.google.firebase.database.*

@Composable
fun ViewEventsScreen() {

    var eventList by remember {
        mutableStateOf(listOf<Event>())
    }

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

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(eventList) { event ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(text = event.eventName)
                    Text(text = event.venue)
                    Text(text = event.date)
                }
            }
        }
    }
}