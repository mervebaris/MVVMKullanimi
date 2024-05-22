package com.example.mvvmkullanimi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen

class SayfaViewModel: ScreenModel {
    var sonuc = MutableLiveData<String>()
    var mrepo = MatematikRepository()
    val tfSayi1 = mutableStateOf("")
    val tfSayi2 = mutableStateOf("")

    init {
        sonuc = mrepo.matematikselSonucGetir()

    }

    fun toplamaYap(alinanSayi1:Int, alinanSayi2:Int){
        mrepo.topla(alinanSayi1, alinanSayi2)

    }

    fun carpmaYap(alinanSayi1:String, alinanSayi2:String){
        mrepo.carp(alinanSayi1, alinanSayi2)

    }
}