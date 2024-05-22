package com.example.mvvmkullanimi

import androidx.lifecycle.MutableLiveData

class MatematikRepository {
    var matematikselSonuc = MutableLiveData<String>()

    init {
        matematikselSonuc = MutableLiveData<String>("0")

    }

    fun matematikselSonucGetir():MutableLiveData<String>{
        return matematikselSonuc
    }

    fun topla(alinanSayi1:Int, alinanSayi2:Int){
        val sayi1 =
            alinanSayi1 //Değerler String olarak geliyor ilk başta TextField özelliğinden Biz bu değerleri sayısala dönüştüreceğiz Matematiksel işlem yapacağımızdan
        val sayi2 = alinanSayi2

        val toplam = sayi1 + sayi2

        matematikselSonuc.value = toplam.toString()

    }

    fun carp(alinanSayi1:String, alinanSayi2:String){
        val sayi1 = alinanSayi1.toInt() //Değerler String olarak geliyor ilk başta TextField özelliğinden Biz bu değerleri sayısala dönüştüreceğiz Matematiksel işlem yapacağımızdan
        val sayi2 = alinanSayi2.toInt()

        val carpma = sayi1 * sayi2

        matematikselSonuc.value = carpma.toString()

    }
}