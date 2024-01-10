package com.example.projectuas.navigasi

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectuas.R
import com.example.projectuas.ui.home.MenuDestination
import com.example.projectuas.ui.home.MenuScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController = navController)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        })

}
@Composable
fun HostNavigasi(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = MenuDestination.route,
        modifier = Modifier.padding()

    ) {
//        composable(HomeDestination.route) {
//            HalamanHome(
//                onNextButtonClicked = {
//                    navController.navigate(MenuDestination.route)
//                })
//        }
        composable(MenuDestination.route) {
            MenuScreen(navigateBack = { navController.popBackStack() })
        }
    }
}