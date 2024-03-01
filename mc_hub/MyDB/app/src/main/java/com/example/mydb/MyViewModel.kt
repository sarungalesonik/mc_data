package com.example.mydb

// MyViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

interface CompanyFetchListener {
    fun onCompaniesFetched(companies: List<Company>)
}

class MyViewModel(private val dao: CompanyDAO) : ViewModel() {
    val companies: MutableStateFlow<List<Company>> = MutableStateFlow(emptyList())
    fun fetchCompanies( ) {
        viewModelScope.launch(Dispatchers.IO) {
            // Insert 3 companies with IDs
            dao.insertAll(
                Company(uid = 55, company_name = "Company 6")
            )
            // Update the state with the newly inserted companies
           // val fetchedCompaniesFlow = dao.getAll()

           // fetchedCompaniesFlow.collect() { fetchedCompanies ->
           //     companies.value = fetchedCompanies
          //  }
            //listener.onCompaniesFetched(fetchedCompanies)
        }
    }

    fun getAllRecords(): Flow<List<Company>> {
        return dao.getAll()
    }

}