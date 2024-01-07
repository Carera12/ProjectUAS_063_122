package com.example.projectuas.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.projectuas.R
import com.example.projectuas.navigasi.DestinasiNavigasi
import com.example.projectuas.navigasi.OrderTopAppBar
import kotlinx.coroutines.launch

object HomeDestination: DestinasiNavigasi {
    override val route  = "item_entry"
    override val titleRes = R.string.app_name
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier= Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            OrderTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ){innerPadding ->
        IsiHome (
            onNextButtonClicked = onNavigateUp,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun IsiHome(
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {
    val image = painterResource(id = R.drawable.cafe)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Row(
            modifier = Modifier,
        ) {
            Button(
                modifier = Modifier,
                onClick =  onNextButtonClicked
            ) {
                Text(stringResource(R.string.order))
            }
        }

    }
}