package com.example.projectuas.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.R
import com.example.projectuas.data.DataOrder.transaksi
import com.example.projectuas.model.TransaksiViewModel
import com.example.projectuas.navigasi.DestinasiNavigasi
import com.example.projectuas.navigasi.OrderTopAppBar

object DestinasiTransaksi : DestinasiNavigasi {
    override val route: String = "Transaksi"
    override val titleRes: Int = R.string.app_name
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanTransaksi(
    navigateOrder: () -> Unit,
    navigateNoOrder: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TransaksiViewModel = viewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            OrderTopAppBar(
                title = stringResource(DestinasiTransaksi.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        },

        ) { innerPadding ->
        val context = LocalContext.current
        IsiTransaksi(
            modeTransaksi = transaksi.map { id -> context.resources.getString(id)},
            onSelectionTransaksi = {viewModel.setTransaksi(it)},
            onNextButtonClicked =  navigateOrder ,
            onCancelButtonClicked = navigateNoOrder ,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun IsiTransaksi(
    modeTransaksi: List<String>,
    onSelectionTransaksi: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    var pilihanTransaksi by rememberSaveable { mutableStateOf("")    }


    Column (modifier= modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {

            Column {
                Text(
                    text = "Transaksi"
                )
                modeTransaksi.forEach { item ->
                    Row(modifier = Modifier.selectable(
                        selected = pilihanTransaksi == item,
                        onClick = {
                            pilihanTransaksi = item
                            onSelectionTransaksi(item)
                        }
                    ),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = pilihanTransaksi == item,
                            onClick = {
                                pilihanTransaksi = item
                                onSelectionTransaksi(item)
                            }
                        )
                        Text(item)
                    }
                }
                Divider(
                    thickness = dimensionResource(R.dimen.thickness_divider),
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
                )

            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.Bottom
            ){
                OutlinedButton(modifier = Modifier.weight(1f),
                    onClick = onCancelButtonClicked){
                    Text(stringResource(R.string.cancel))
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onNextButtonClicked
                ) {
                    Text(stringResource(R.string.order))
                }
            }

        }
    }
}