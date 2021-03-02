package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.Infra.AppConstants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null)
            supportActionBar!!.hide()

        verifyName()

        buttonSave.setOnClickListener(this)
    }

    private fun verifyName() {
        val name = mSecurityPreferences.getString(AppConstants.Key.PERSON_NAME)

        if (!name.isNullOrEmpty() || !name.isBlank()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    override fun onClick(v: View) {
        val id = v.id

        if (R.id.buttonSave == id) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()

        if (!name.isNullOrEmpty()) {
            // Salva em sessão o nome informado no edit
            mSecurityPreferences.storeString(AppConstants.Key.PERSON_NAME, name)

            // Navegar para outra Active
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            //Exibir notificação
            Toast.makeText(this, "Informe seu nome !", Toast.LENGTH_SHORT).show()
        }
    }
}