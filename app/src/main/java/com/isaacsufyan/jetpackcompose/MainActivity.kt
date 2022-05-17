package com.isaacsufyan.jetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.isaacsufyan.jetpackcompose.ui.theme.JetPackComposeTheme

class MainActivity : ComponentActivity() {

    private val mainActivityUI: MainActivityUI by lazy {
        MainActivityUI(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                mainActivityUI.Conversation(messages = SampleData.conversationSample)
            }
        }
    }

    @Preview(
        name = "Light Mode", showBackground = true,
        showSystemUi = true
    )
    @Preview(
        name = "Dark Mode", showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_YES
    )
    @Composable
    fun PreviewMessageCard() {
        JetPackComposeTheme {
            mainActivityUI.Conversation(messages = SampleData.conversationSample)
        }
    }
}