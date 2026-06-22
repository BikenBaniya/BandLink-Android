package com.bandlink.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bandlink.models.Band
import com.google.firebase.database.*
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.bandlink.firebase.FirebaseRepository
import androidx.compose.material3.Button
@Composable
fun ViewBandsScreen() {

    var bandList by remember {
        mutableStateOf(listOf<Band>())
    }
    val context = LocalContext.current
    val firebaseRepository = FirebaseRepository()

    LaunchedEffect(Unit) {

        FirebaseDatabase.getInstance()
            .getReference("bands")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val tempList = mutableListOf<Band>()

                    for (child in snapshot.children) {

                        val band = child.getValue(Band::class.java)

                        if (band != null) {
                            tempList.add(band)
                        }
                    }

                    bandList = tempList
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(bandList) { band ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(text = band.bandName)
                    Text(text = band.genre)
                    Text(text = band.location)
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {

                            firebaseRepository.deleteBand(
                                band.bandId
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