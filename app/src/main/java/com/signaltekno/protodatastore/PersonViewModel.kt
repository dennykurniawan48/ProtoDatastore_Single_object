package com.signaltekno.protodatastore

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ProtoRepository(application)

    val person = repository.readPerson.asLiveData()


    fun updateFirstName(firstName: String){
        Log.d("Data", person.value?.firstName.toString())
        viewModelScope.launch(Dispatchers.IO) {
            repository.setPerson(firstName = firstName)
        }
    }
}