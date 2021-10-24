package com.signaltekno.protodatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.signaltekno.protodatastore.ui.theme.ProtoDatastoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: PersonViewModel by viewModels()
        setContent {
            val firstName by viewModel.person.observeAsState()
            var textFirstName by remember { mutableStateOf(firstName?.firstName ?: "")}
            ProtoDatastoreTheme {
                Column(){
                    OutlinedTextField(value = textFirstName, onValueChange = {textFirstName = it})
                    Button(onClick = { viewModel.updateFirstName(firstName = textFirstName.toString()) }) {
                        Text("Save", modifier = Modifier.padding(5.dp))
                    }
                    Text(text=firstName?.firstName ?: "")
                }
            }
        }
    }
}
