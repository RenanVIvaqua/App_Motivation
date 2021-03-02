package com.example.motivation.Infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(val context: Context) {
    //Classe usada para gravar dados na sessao da app

    private val mSharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        //Caso o mSharedPreferences.getString for nulo, retorna string vazia
        return mSharedPreferences.getString(key, "") ?: ""
    }


}