package com.example.mobil4lab

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class SecondFragment: Fragment(R.layout.fragment_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val text = requireArguments().getString(EXTRA_TEXT)
        val textView: TextView = view.findViewById(R.id.text)
        textView.text = text

    }
    companion object {
        private const val EXTRA_TEXT = "extra_text"
        /**
         * На этот раз передают аргументы во фрагмент
         */
        fun newInstance(text: String): SecondFragment {
            return SecondFragment().apply {
                arguments = bundleOf(EXTRA_TEXT to text)
            }
        }
    }



}