package com.mertfatih.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertfatih.kotlincountries.model.Country

class CountryViewModel: ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.ss.com")
        countryLiveData.value = country
    }
}