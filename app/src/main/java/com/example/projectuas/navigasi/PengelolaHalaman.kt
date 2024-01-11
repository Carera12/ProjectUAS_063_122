package com.example.projectuas.navigasi

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectuas.R
import com.example.projectuas.model.MenuViewModel
import com.example.projectuas.model.RiwayatViewModel
import com.example.projectuas.ui.halaman.DestinasiForm
import com.example.projectuas.ui.halaman.DestinasiMenu
import com.example.projectuas.ui.halaman.DestinasiRiwayat
import com.example.projectuas.ui.halaman.DestinasiStart
import com.example.projectuas.ui.halaman.HalamanForm
import com.example.projectuas.ui.halaman.HalamanMenu
import com.example.projectuas.ui.halaman.HalamanRiwayat
import com.example.projectuas.ui.halaman.HomeStart

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
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        })

}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiStart.route,
        modifier = Modifier
    ) {
        composable(DestinasiStart.route){
            HomeStart(
                navigateHome = { navController.navigate(DestinasiForm.route) } // Pindahkan navigasi ke halaman menu di sini
            )
        }
        composable(DestinasiForm.route){
            HalamanForm(navigateBack = { navController.navigate(DestinasiRiwayat.route) })
        }
        composable(DestinasiMenu.route){
            HalamanMenu(
                navigateSave = {

                    navController.navigate(DestinasiRiwayat.route) },
                navigateCancel = { navController.navigate(DestinasiForm.route)}
            )
        }

        composable(DestinasiRiwayat.route){
            HalamanRiwayat(navigateToItemEntry = { /*TODO*/ })
        }
    }
}





