package com.example.recipeapppage

sealed class Screen(val route:String) {
    object RecipeScreen:Screen("recipescreen")
    object CategoryDetailScreen: Screen("detailscreen")
}