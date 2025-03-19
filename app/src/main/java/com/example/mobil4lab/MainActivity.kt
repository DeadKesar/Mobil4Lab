package com.example.mobil4lab

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    { super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Создание транзакции на добавление фрагмента
         */
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_host, FirstFragment.newInstance(), null)
            .commit()

        supportFragmentManager.setFragmentResultListener(
            FirstFragment.REQUEST_FRAGMENT, this, ::onFirstFragmentResult
        )
    }

    private fun onFirstFragmentResult(requestKey: String, data: Bundle) {
        val text = data.getString(FirstFragment.EXTRA_TEXT) ?:
            throw IllegalArgumentException("TODO")

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_host, SecondFragment.newInstance(text), null)
            .addToBackStack("SecondFragment")
            .commit()
    }


}