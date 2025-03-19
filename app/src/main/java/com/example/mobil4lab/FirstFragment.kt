package com.example.mobil4lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class FirstFragment : Fragment(R.layout.fragment_first) {
    /**
     * Создание нашего View ручками.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // пример ручного создание View
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    /**
     * Работа с View. Своего рода "точка входа"
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nextButton: Button = view.findViewById(R.id.next)
        nextButton.setOnClickListener {
            openSecondFragment() }
    }
    private fun openSecondFragment() {
        val navController = findNavController()
        navController.navigate(R.id.action_to_secondFragment) }

    companion object {
        /**
         * Factory метод создания фрагмента.
         * Необходим для создания контакта использования.
         * Использование newInstance - best practice
         */
        const val REQUEST_FRAGMENT = "request"
        const val EXTRA_TEXT = "text"
        fun newInstance(): FirstFragment { return FirstFragment() }
    }


}