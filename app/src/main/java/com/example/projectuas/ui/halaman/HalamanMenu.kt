package com.example.projectuas.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.R
import com.example.projectuas.data.DataOrder.makanan
import com.example.projectuas.data.DataOrder.minuman
import com.example.projectuas.data.DataOrder.transaksi
import com.example.projectuas.model.MenuViewModel
import com.example.projectuas.navigasi.DestinasiNavigasi
import com.example.projectuas.navigasi.OrderTopAppBar

object DestinasiMenu : DestinasiNavigasi {
    override val route: String = "Menu"
    override val titleRes: Int = R.string.app_name
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanMenu(
    navigateSave: () -> Unit,
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
        val context = LocalContext.current
        IsiMenu(
            menumakanan = makanan.map {id -> context.resources.getString(id)},
            transaksi = transaksi.map {id -> context.resources.getString(id)},
            onSelectionMakanan = {viewModel.setMakanan(it)},
            onSelectionTransaksi = {viewModel.setTransaksi(it)},
            onConfirmButtonClicked = {jumlah -> viewModel.setJumlah(jumlah)},
            onNextButtonClicked =    navigateSave,
            onCancelButtonClicked =  navigateCancel ,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsiMenu(
    menumakanan: List<String>,
    transaksi: List<String>,
    onSelectionMakanan: (String) -> Unit,
    onSelectionTransaksi: (String) -> Unit,
    onConfirmButtonClicked: (Int) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    var pilihanmakanan by rememberSaveable { mutableStateOf("")    }
    var pilihantransaksi by rememberSaveable { mutableStateOf("")    }
    var textJmlMakanan by remember { mutableStateOf("")  }
    var textJmlMinuman by remember { mutableStateOf("")  }


    Column (modifier= modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {

                Column {
                    Text(
                        text = "Makanan"
                    )
                    menumakanan.forEach { item ->
                        Row(modifier = Modifier.selectable(
                            selected = pilihanmakanan == item,
                            onClick = {
                                pilihanmakanan = item
                                onSelectionMakanan(item)
                            }
                        ),
                            verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = pilihanmakanan == item,
                                onClick = {
                                    pilihanmakanan = item
                                    onSelectionMakanan(item)
                                }
                            )
                            Text(item)
                        }
                    }
                    Divider(
                        thickness = dimensionResource(R.dimen.thickness_divider),
                        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
                    )
                    Row (
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_small)),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
                    ){
                        OutlinedTextField(value = textJmlMakanan,
                            singleLine = true,
                            shape = MaterialTheme.shapes.large,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(150.dp),
                            label = { Text(text = "Jumlah Order") },
                            onValueChange = {textJmlMakanan = it}
                        )
                        Button(modifier = Modifier.weight(1f),
                            enabled = textJmlMakanan.isNotEmpty(),
                            onClick = {onConfirmButtonClicked(textJmlMakanan.toInt())
                            }) {
                            Text(stringResource(R.string.confirm))
                        }
                    }
                }
                Column {
                    Text(
                        text = "Minuman"
                    )
                    transaksi.forEach { item ->
                        Row(modifier = Modifier.selectable(
                            selected = pilihantransaksi == item,
                            onClick = {
                                pilihantransaksi = item
                                onSelectionTransaksi(item)
                            }
                        ),
                            verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = pilihantransaksi == item,
                                onClick = {
                                    pilihantransaksi = item
                                    onSelectionTransaksi(item)
                                }
                            )
                            Text(item)
                        }
                    }
                }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
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
                    enabled = textJmlMakanan.isNotEmpty(),
                    onClick = onNextButtonClicked
                ) {
                    Text(stringResource(R.string.order))
                }
            }

        }
    }
}