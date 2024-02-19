package com.anurag.firebaseauthflow.common.userForm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.CustomButtonV2

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun userForm() {
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }
    var learnTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Help us know you more!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = occupation,
            onValueChange = { occupation = it },
            label = { Text("Occupation") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = learnTime,
            onValueChange = { learnTime = it },
            label = { Text("How much time can you learn daily?") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Perform form submission or validation here
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text("Submit")
        }
    }
}
