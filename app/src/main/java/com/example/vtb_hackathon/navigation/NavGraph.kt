package com.example.vtb_hackathon.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import com.example.vtb_hackathon.ui.screen.code.CodeScreen
import com.example.vtb_hackathon.ui.screen.code.CodeScreenVM
import com.example.vtb_hackathon.ui.screen.login.LoginScreen
import com.example.vtb_hackathon.ui.screen.login.LoginVM
import com.example.vtb_hackathon.ui.screen.map.MapScreenManipulator
import com.example.vtb_hackathon.ui.screen.map.MapVM

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    activityContext: Context
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "login") {

            val viewModel = hiltViewModel<LoginVM>()

            LoginScreen(
                viewModel = viewModel,
                navigateToCode = {
                    navController.navigate("code")
                }
            )
        }
        composable(route = "code") {
            val viewModel = hiltViewModel<CodeScreenVM>()

            CodeScreen(
                viewModel = viewModel,
                navigateToMap = {
                    navController.navigate("map")
                }
            )
        }
        composable(route = "map") {

            val viewModel = hiltViewModel<MapVM>()
            val mapScreenManipulator =
                MapScreenManipulator(
                    context = LocalContext.current,
                    activityContext = activityContext
                )

            mapScreenManipulator.MapScreen(
                viewModel = viewModel
            )

        }
    }
}