package com.example.masshcet

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.masshcet.navigation.AppNav
import com.example.masshcet.ui.theme.MassHCETTheme
import dagger.hilt.android.AndroidEntryPoint



@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MassHCETTheme {
                AppNav()
            }
        }
    }
}

