package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.motivation.Infra.AppConstants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import com.example.motivation.Repository.Mock


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter: Int = AppConstants.PhraseFilter.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        textMessage.text = Mock().getPhrase(AppConstants.PhraseFilter.ALL)

        btnNewMessage.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)

        if (supportActionBar != null)
            supportActionBar!!.hide()

        mSecurityPreferences = SecurityPreferences(this)
        textName.text = "Olá, ${mSecurityPreferences.getString(AppConstants.Key.PERSON_NAME)}"
    }

    override fun onClick(v: View) {
        val id = v.id

        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if (id == R.id.btnNewMessage) {
            handleNewPhrase()

        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {
        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = AppConstants.PhraseFilter.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = AppConstants.PhraseFilter.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = AppConstants.PhraseFilter.MORNING
            }
        }
    }

    private fun handleNewPhrase() {
        textMessage.text = Mock().getPhrase(mPhraseFilter)
    }

}