package com.example.mea1mc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mea1mc.ui.theme.MEA1MCTheme

class AddCompany : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MEA1MCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    compnayForm(context)
                }
            }
        }
    }
}
@Composable
fun compnayForm(context: Context){
    var company_name by remember{ mutableStateOf("") }
    var eligible by remember{ mutableStateOf("") }
    var not_eligible by remember{ mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Row(
            modifier = Modifier.padding(10.dp)
        ){
            TextField(value = company_name,
                onValueChange = {
                    company_name = it
                },
                placeholder = {
                Text(text = "Company Name")
            })
        }
        Row(
            modifier = Modifier.padding(10.dp)
        ){
            TextField(value = eligible, onValueChange = {
                eligible = it
            }, placeholder = {
                Text(text = "Eligible")
            })
        }
        Row(
            modifier = Modifier.padding(10.dp)
        ){
            TextField(value = not_eligible, onValueChange = {
                not_eligible = it
            }, placeholder = {
                Text(text = "Not Eligible")
            })
        }
        Row(
            horizontalArrangement = Arrangement.End
        ){
            Button(onClick = {
                dataManager.appendData(Company(company_name, Integer.parseInt(eligible), Integer.parseInt(not_eligible)))

                context.startActivity(Intent(context, MainActivity::class.java))
            }) {
                Text("Add")
            }
        }

    }
}