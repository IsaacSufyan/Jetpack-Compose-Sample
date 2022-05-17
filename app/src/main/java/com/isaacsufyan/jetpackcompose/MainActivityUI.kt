package com.isaacsufyan.jetpackcompose

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R

class MainActivityUI(var mainActivity: MainActivity) {

    @Composable
    fun MessageCard(msg: Message) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_start),
                    contentDescription = "Content Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterVertically)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))

                var isExpanded by remember { mutableStateOf(false) }
                Log.e("TAG", "MessageCard: $isExpanded")

                val surfaceColor: Color by animateColorAsState(
                    if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                )

                Column(modifier = Modifier.clickable {
                    isExpanded = !isExpanded
                }) {
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        elevation = 1.dp,
                        color = surfaceColor,
                        modifier = Modifier
                            .animateContentSize()
                            .padding(1.dp)
                    ) {
                        Text(
                            text = msg.body,
                            modifier = Modifier.padding(all = 3.dp),
                            style = MaterialTheme.typography.body2,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        Column (Modifier.fillMaxSize()) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(messages) {
                    MessageCard(it)
                }
            }

            Button(
                onClick = { performAction("Login") },
                modifier = Modifier.padding(5.dp).align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Login")
            }

            Button(
                onClick = { performAction("Signup") },
                modifier = Modifier.padding(5.dp).align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Signup")
            }

            Spacer(modifier = Modifier.height(2.dp))
        }
    }

    private fun performAction(message: String) {
        Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show()
    }
}