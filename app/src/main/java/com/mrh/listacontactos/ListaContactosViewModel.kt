package com.mrh.listacontactos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListaContactosViewModel : ViewModel() {
    private val _contactos = MutableLiveData<List<Contacto>>()
    var contactos : LiveData<List<Contacto>> = _contactos
}