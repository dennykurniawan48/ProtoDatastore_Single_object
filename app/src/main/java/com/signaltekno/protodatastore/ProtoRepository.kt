package com.signaltekno.protodatastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository(context: Context) {
    private val Context.preferencesDataStore: DataStore<Person> by dataStore(fileName="PERSON_DATASTORE", serializer = MySerializer)
    private val dataStore = context.preferencesDataStore

    val readPerson: Flow<Person> = dataStore.data.catch { exception ->
        if(exception is IOException){
            Log.d("error", exception.message.toString())
            emit(Person.getDefaultInstance())
        }else{
            throw exception
        }
    }

    suspend fun setPerson(firstName: String){
        dataStore.updateData { preferences ->
            preferences.toBuilder().setFirstName(firstName).build()
        }
    }
}