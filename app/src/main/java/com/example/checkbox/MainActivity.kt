package com.example.checkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.checkbox.ui.theme.CheckboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckboxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CheckboxCard(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CheckboxCard(name: String, modifier: Modifier = Modifier) {

    // Checkboxes
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }
    var checked3 by remember { mutableStateOf(false) }

    // Switch / button
    var switchOn by remember { mutableStateOf(true) }
    var displayOff by remember { mutableStateOf("") }


    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(8.dp)) { Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.teal_200)),
        verticalArrangement = Arrangement.SpaceAround, // !!!!!!!!!!!! REVISAR cómo queda cuando esté el resto
        horizontalAlignment = Alignment.CenterHorizontally){

            Text("Daily Quests: ")

            Spacer(modifier = Modifier.padding(bottom = 8.dp))

            CheckboxQuest("Task 1", checked1) { checked1 = it }
            CheckboxQuest("Task 2", checked2) { checked2 = it }
            CheckboxQuest("Task 3", checked3) { checked3 = it }

            Spacer(modifier = Modifier.padding(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Unlock button")

                Spacer(modifier = Modifier.padding(8.dp))

                Switch(
                    checked = switchOn,
                    onCheckedChange = {
                        switchOn = it

                        if (!it){
                            displayOff = ""
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    displayOff = when{
                        checked1 -> "Task 1"

                        checked2 -> "Task 2"

                        checked3 -> "Task 3"

                        else -> "No task has been selected."
                    }
                },
                enabled = switchOn
            ) {
                Text("Show current task")
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = displayOff,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

@Composable
fun CheckboxQuest(quest: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {


    Row(
        verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Text(text = quest, modifier = Modifier.padding(start = 6.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CheckboxTheme {
        CheckboxCard("Android")
    }
}
