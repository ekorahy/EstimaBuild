package com.ekorahy.estimabuild

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ekorahy.estimabuild.ui.components.BottomBar
import com.ekorahy.estimabuild.ui.navigation.Screen
import com.ekorahy.estimabuild.ui.screen.detail.DetailProduct
import com.ekorahy.estimabuild.ui.screen.estimation.Estimation
import com.ekorahy.estimabuild.ui.screen.home.Home
import com.ekorahy.estimabuild.ui.screen.profile.Profile
import com.ekorahy.estimabuild.ui.theme.EstimaBuildTheme

@Composable
fun EstimaBuildApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
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
            composable(Screen.Estimation.route) {
                val context = LocalContext.current
                Estimation(
                    navigateBack = { navController.navigateUp() },
                    onEstimateButtonClicked = { totalPrice ->
                        Toast.makeText(
                            context,
                            String.format(
                                "The total estimate for all products is $ %.2f",
                                totalPrice
                            ),
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    },
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                Profile(
                    navigateBack = { navController.navigateUp() },
                )
            }
            composable(
                route = Screen.DetailProduct.route,
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) {
                val id = it.arguments?.getString("productId") ?: ""
                DetailProduct(
                    productId = id,
                    navigateBack = { navController.navigateUp() },
                    navigateToEstimate = {
                        navController.popBackStack()
                        navController.navigate(Screen.Estimation.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
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