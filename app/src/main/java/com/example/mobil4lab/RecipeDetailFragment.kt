package com.example.mobil4lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecipeDetailFragment : Fragment() {
    private val viewModel: RecipeViewModel by viewModels()
    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailName: TextView = view.findViewById(R.id.detailName)
        val detailStatus: TextView = view.findViewById(R.id.detailStatus)
        val detailInstructions: TextView = view.findViewById(R.id.detailInstructions)
        val detailImage: ImageView = view.findViewById(R.id.detailImage)
        val toStepsButton: Button = view.findViewById(R.id.to_steps)
        val addToShoppingListButton: Button = view.findViewById(R.id.add_to_shopping_list)

        toStepsButton.setOnClickListener {
            val action = RecipeDetailFragmentDirections.actionToCookingSteps(args.recipeId)
            findNavController().navigate(action)
        }

        addToShoppingListButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("добавить в корзину")
                .setMessage("Хотите добавить ингредиенты в корзину?")
                .setPositiveButton("Да") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("Нет") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        viewModel.recipeDetails.observe(viewLifecycleOwner, Observer { meal ->
            meal?.let {
                detailName.text = it.strMeal
                detailStatus.text = "Area: ${it.strArea ?: "Unknown"}"
                detailInstructions.text = it.strInstructions?.replace("\r\n", "\n") ?: "No instructions"
                it.strMealThumb?.let { url ->
                    Glide.with(this@RecipeDetailFragment)
                        .load(url.replace("\\/", "/"))
                        .into(detailImage)
                }
            } ?: run {
                detailName.text = "Error loading details"
            }
        })

        viewModel.loadRecipeDetails(args.recipeId)
    }
}