package com.example.recipeapppage.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapppage.Category
import com.example.recipeapppage.RecipeServices
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val _categorieState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorieState

    init {
        fetchingData()
    }

    fun fetchingData() {
        viewModelScope.launch {
            try {
                val response = RecipeServices.getCategory()
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )
            } catch (e: Exception) {
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error happens${e.message}"
                )
            }
        }
    }
}

data class RecipeState(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)
