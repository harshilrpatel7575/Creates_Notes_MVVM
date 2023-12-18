package com.example.notesmvvm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesmvvm.Database.NotesDatabase
import com.example.notesmvvm.Model.Notes
import com.example.notesmvvm.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository= NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun getlowNotes(): LiveData<List<Notes>> = repository.getLowNotes()

    fun getmediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()

    fun gethighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

}