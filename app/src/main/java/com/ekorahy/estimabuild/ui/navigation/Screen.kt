package com.ekorahy.estimabuild.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Estimation : Screen("estimation")
    object Profile : Screen("profile")
    object DetailProduct : Screen("home/{productId}") {
        fun createRoute(productId: String) = "home/$productId"
    }
}