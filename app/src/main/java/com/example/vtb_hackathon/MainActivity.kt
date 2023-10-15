package com.example.vtb_hackathon

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import com.example.vtb_hackathon.navigation.NavGraph
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val TAG = MainActivity::class.simpleName
    private val vm:MainVM by viewModels()

    private var locationPermissionRequest: ActivityResultLauncher<Array<String>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.initialize(this)

        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.d(TAG, "FINE LOCATION ARE ALLOWED")
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Log.d(TAG, "COARSE LOCATION ARE ALLOWED")
                }

                permissions.getOrDefault(Manifest.permission.POST_NOTIFICATIONS, false) -> {
                    Log.d(TAG, "NOTIFICATION ARE ALLOWED")
                }
            }
        }

        askForePermissions()

        vm.checkPhoneNumber()

        setContent {

            val isAuth  = vm.getNavLiveData().observeAsState().value

            val navController = rememberNavController()

            Log.d(TAG, "IS_AUTH is $isAuth")

            if (isAuth == true) {
                Log.d(TAG, "IS_AUTH == TRUE")
                NavGraph(
                    navController = navController,
                    startDestination = "map",
                    activityContext = this
                )
            } else {
                Log.d(TAG, "IS_AUTH == FALSE")
                NavGraph(
                    navController = navController,
                    startDestination = "login",
                    activityContext = this
                )
            }
        }
    }


    private fun askForePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            locationPermissionRequest?.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            )
        } else {
            locationPermissionRequest?.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }

    }
}

