package com.example.mobil4lab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun getMealsByFirstLetter(@Query("f") letter: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): MealResponse
}

data class MealResponse(val meals: List<MealItem>)

data class MealItem(
    val idMeal: String,
    val strMeal: String,
    val strArea: String?,
    val strMealThumb: String?,
    val strInstructions: String?,
    val strCategory: String?
)

class RecipeViewModel : ViewModel() {
    val recipes = MutableLiveData<List<MealItem>>()
    val recipeDetails = MutableLiveData<MealItem?>()
    val cookingSteps = MutableLiveData<List<String>>()

    private val api = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MealApi::class.java)

    fun loadRecipes() {
        viewModelScope.launch {
            try {
                val response = api.getMealsByFirstLetter("b")
                recipes.value = response.meals
            } catch (e: Exception) {
                recipes.value = emptyList()
            }
        }
    }

    fun loadRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            try {
                val response = api.getMealById(recipeId)
                recipeDetails.value = response.meals.firstOrNull()
            } catch (e: Exception) {
                recipeDetails.value = null
            }
        }
    }

    fun loadCookingSteps(recipeId: String) {
        viewModelScope.launch {
            try {
                val response = api.getMealById(recipeId)
                val meal = response.meals.firstOrNull()
                cookingSteps.value = meal?.strInstructions?.split("\n")?.filter { it.isNotBlank() } ?: emptyList()
            } catch (e: Exception) {
                cookingSteps.value = listOf("Error loading steps")
            }
        }
    }
}