package com.example.mydb

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.mydb.ui.theme.MyDBTheme
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow



class MainActivity : ComponentActivity(), CompanyFetchListener {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    private val viewModel by viewModels<MyViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MyViewModel(db.companyDao()) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
               ) {
                    DatabaseListView(vm = viewModel)

                }
            }
        }
    }

    override fun onCompaniesFetched(companies: List<Company>) {
        // Handle the fetched companies here
       // viewModel.companies.value = companies
    }
}

@Composable
fun DatabaseListView(vm: MyViewModel) {
    val dataList = vm.getAllRecords().collectAsState(initial = emptyList())
    Column {
        Button(onClick = {
            vm.fetchCompanies()
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
                    Text(item.company_name)
                    Spacer(Modifier.weight(1f))
                   // Text(item.priority.toString())
                }
            }
        }
    }
}

