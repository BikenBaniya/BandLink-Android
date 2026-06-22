package com.bandlink
import com.bandlink.screens.LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bandlink.navigation.AppNavigation
import com.bandlink.screens.CreateBandScreen
import com.bandlink.screens.CreateEventScreen
import com.bandlink.screens.HomeScreen
import com.bandlink.screens.RegisterScreen
import com.bandlink.screens.ViewBandsScreen
import com.bandlink.screens.ViewEventsScreen
import com.bandlink.ui.theme.BandLinkTheme
import com.bandlink.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BandLinkTheme {
                AppNavigation()
            }

        }
    }
}

