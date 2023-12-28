package com.ekorahy.estimabuild.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailProduct: Screen("home/{productId}") {
        fun createRoute(productId: String) = "home/$productId"
    }
    object Estimation: Screen("estimation")
}