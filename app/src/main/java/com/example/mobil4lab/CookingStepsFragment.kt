package com.example.mobil4lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

class CookingStepsFragment : Fragment() {
    private val viewModel: RecipeViewModel by viewModels()
    private val args: CookingStepsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cooking_steps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stepsTextView: TextView = view.findViewById(R.id.stepsTextView)

        viewModel.cookingSteps.observe(viewLifecycleOwner, Observer { steps ->
            stepsTextView.text = steps.joinToString("\n")
        })

        viewModel.loadCookingSteps(args.recipeId)
    }
}