@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.bridging

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bridging.ui.theme.BridgingTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            BridgingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bridge ()
                }
            }
        }
    }
}

@Composable
fun Bridge(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var buttonPressed by remember { mutableStateOf(false) }
    val dictionary by remember { mutableStateOf(mutableSetOf<String>()) }
    val dictionarySorted = dictionary.sortedWith(
        compareBy(String.CASE_INSENSITIVE_ORDER, { it })
    )
    
    Column (
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Dictionary",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )
        InputText(
            value = inputText,
            modifier = Modifier,
            onValueChanged = { inputText = it },
            )
        if (inputText != ""){
            Button(
                onClick = {buttonPressed = true}
            ){
                Text("Add to dictionary")
            }
        }
        if (buttonPressed){
            dictionary.add(inputText)
            inputText = ""
            buttonPressed = false
        }
        Text(
            text = "My words",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
        Dictionary(
            dictionary = dictionarySorted,
            modifier = Modifier
        )
    }
}

@Composable
fun InputText(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text("Type a word or phrase") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
fun Dictionary(
    dictionary: List<String>,
    modifier: Modifier
) {
    for (word in dictionary) {
        Text(word)
    }
}

@Preview(showBackground = true)
@Composable
fun BridgePreview() {
    BridgingTheme {
        Bridge()
    }
}