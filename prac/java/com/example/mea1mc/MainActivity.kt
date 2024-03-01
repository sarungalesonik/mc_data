package com.example.mea1mc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mea1mc.ui.theme.MEA1MCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataManager.loadData(this)
        setContent {
            MEA1MCTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        topButton()
                        companyList()

                    }
                }
            }
        }
    }
}
@Composable
fun companyList(){
    if(dataManager.isLoaded){
        LazyColumn{
            itemsIndexed(dataManager.data){ index, company ->
                Row{
                    Text(text = company.company_name)
                }
            }
        }
    }
}
@Composable
fun topButton(){
    val context = LocalContext.current
    Row (
        horizontalArrangement = Arrangement.End
    ){
        Button(onClick = { context.startActivity(Intent(context, AddCompany::class.java)) }) {
            Text(text = "Add")
        }
    }
}
