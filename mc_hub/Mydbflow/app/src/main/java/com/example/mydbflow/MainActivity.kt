package com.example.mydbflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mydbflow.ui.theme.MydbflowTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = DataModelDatabase::class.java,
            name = "datamodel.db"
        ).build()
    }
    private val viewModel by viewModels<DataModelViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DataModelViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MydbflowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DatabaseListView(vm = viewModel)
                }
            }
        }
    }
}

@Composable
fun DatabaseListView(vm: DataModelViewModel) {
    val dataList = vm.getAllRecords().collectAsState(initial = emptyList())

    Column {
        Button(onClick = {
            vm.addData()
        }) {
            Text("Add Items")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            items(dataList.value) { item ->
                Row {
                    Text(item.text)
                    Spacer(Modifier.weight(1f))
                    Text(item.priority.toString())
                }
            }
        }
    }
}