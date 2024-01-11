package com.example.projectuas.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.R
import com.example.projectuas.data.DataOrder
import com.example.projectuas.model.MenuViewModel
import com.example.projectuas.navigasi.DestinasiNavigasi
import com.example.projectuas.navigasi.OrderTopAppBar

object DestinasiForm : DestinasiNavigasi {
    override val route: String = "Form"
    override val titleRes: Int = R.string.app_name
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanForm(
    navigateSave: (MutableList<String>) -> Unit,
    navigateCancel: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = viewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            OrderTopAppBar(
                title = stringResource(DestinasiStart.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        },

        ) { innerPadding ->
        IsiForm(
            onCancelButtonClicked = { /*TODO*/ },
            onSubmitButtonClick = navigateSave,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsiForm(
    //onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    onSubmitButtonClick: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var namaTxt by rememberSaveable {
        mutableStateOf("")
    }
    var alamatTxt by rememberSaveable {
        mutableStateOf("")
    }
    var telponTxt by rememberSaveable {
        mutableStateOf("")
    }
    var listDataTxt: MutableList<String> = mutableListOf(namaTxt, alamatTxt, telponTxt)

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        Text(text = "Data Pelanggan", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Column (modifier = Modifier.padding(20.dp)) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = namaTxt,
                label = { Text(text = stringResource(id = R.string.nama)) },
                onValueChange = {namaTxt = it})
            Spacer(modifier = Modifier.padding(15.dp))
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = telponTxt,
                label = { Text(text = stringResource(id = R.string.notelp)) },
                onValueChange = {telponTxt = it})
            Spacer(modifier = Modifier.padding(15.dp))
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = alamatTxt,
                label = { Text(text = stringResource(id = R.string.alamat)) },
                onValueChange = {alamatTxt = it})
            Spacer(modifier = Modifier.padding(16.dp))

        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            ElevatedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            ElevatedButton(
                modifier = Modifier.weight(1f),
                onClick = {onSubmitButtonClick(listDataTxt)}
            ) {
                Text(stringResource(R.string.order))
            }
        }
    }
}