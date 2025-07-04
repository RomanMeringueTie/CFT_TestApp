package ru.cft.test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.cft.common.data.model.UserModel
import ru.cft.common.ui.EnterAnimation
import ru.cft.feature_user_details.ui.UserDetailsScreen
import ru.cft.feature_users_list.ui.UsersListScreen
import ru.cft.test_app.navigation.Route
import ru.cft.test_app.ui.theme.CFT_Test_AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            CFT_Test_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.UsersList,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Route.UsersList> {
                            UsersListScreen(
                                modifier = Modifier.fillMaxSize(),
                                onItemClick = {
                                    navController.navigate(
                                        Route.UserDetails(it),
                                    ) { launchSingleTop = true }
                                })
                        }
                        composable<Route.UserDetails> {
                            val encodedUser = it.toRoute<Route.UserDetails>().encodedUser
                            val decodedUser = Json.decodeFromString<UserModel>(encodedUser)
                            EnterAnimation {
                                UserDetailsScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    viewModel = koinViewModel(parameters = {
                                        parametersOf(
                                            decodedUser
                                        )
                                    }),
                                    onBackClick = navController::navigateUp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
