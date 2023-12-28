package com.ekorahy.estimabuild

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ekorahy.estimabuild.ui.components.BottomBar
import com.ekorahy.estimabuild.ui.navigation.Screen
import com.ekorahy.estimabuild.ui.screen.home.Home
import com.ekorahy.estimabuild.ui.theme.EstimaBuildTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstimaBuildApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailProduct.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                Home(
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId))
                    }
                )
            }
            composable(Screen.Profile.route) {

            }
            composable(Screen.DetailProduct.route) {

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EstimaBuildPreview() {
    EstimaBuildTheme {
        EstimaBuildApp()
    }
}