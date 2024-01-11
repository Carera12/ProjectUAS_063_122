package com.example.projectuas.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.R
import com.example.projectuas.data.DataOrder
import com.example.projectuas.data.DataOrder.makanan
import com.example.projectuas.data.DataOrder.transaksi
import com.example.projectuas.model.DetailOrder
import com.example.projectuas.model.EditViewModel
import com.example.projectuas.model.MenuViewModel
import com.example.projectuas.model.PenyediaViewModel
import com.example.projectuas.model.UIStateOrder
import com.example.projectuas.navigasi.DestinasiNavigasi
import com.example.projectuas.navigasi.OrderTopAppBar
import kotlinx.coroutines.launch

object EditDestination: DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.app_name
    const val orderIdArg  = "itemId"
    val routeWithArgs = "$route/{$orderIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanEdit(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            OrderTopAppBar(
                title = stringResource(DestinasiStart.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EditBody(
            uiStateOrder = viewModel.orderUiState,
            onSiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                viewModel.updateSiswa()
                onNavigateUp()
                }
            },  modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun EditBody(
    uiStateOrder: UIStateOrder,
    onSiswaValueChange: (DetailOrder) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        val context = LocalContext.current
        IsiEdit(
            detailOrder = uiStateOrder.detailOrder,
            menumakanan = makanan.map { id -> context.resources.getString(id)},
            transaksi = transaksi.map { id -> context.resources.getString(id)},
            onSelectionMakanan = {viewModel.setMakanan(it)},
            onSelectionTransaksi = {viewModel.setTransaksi(it)},
            onConfirmButtonClicked = {
                viewModel.setJumlah(it)},
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateOrder.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.order))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsiEdit(
    detailOrder: DetailOrder,
    modifier: Modifier = Modifier,
    onValueChange: (DetailOrder) -> Unit = {},
    enabled: Boolean = true,
    menumakanan: List<String>,
    transaksi: List<String>,
    onSelectionMakanan: (String) -> Unit,
    onSelectionTransaksi: (String) -> Unit,
    onConfirmButtonClicked: (Int) -> Unit,
) {
    var pilihanmakanan by rememberSaveable { mutableStateOf("") }
    var pilihantransaksi by rememberSaveable { mutableStateOf("") }
    var textJmlMakanan by remember { mutableStateOf("") }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = detailOrder.nama,
            onValueChange = { onValueChange(detailOrder.copy(nama = it)) },
            label = { Text(stringResource(id = R.string.nama)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailOrder.alamat,
            onValueChange = { onValueChange(detailOrder.copy(alamat = it)) },
            label = { Text(stringResource(id = R.string.alamat)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailOrder.noTelp,
            onValueChange = { onValueChange(detailOrder.copy(noTelp = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(id = R.string.notelp)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Column(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(R.dimen.padding_small)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Column {
                    Text(
                        text = "Transaksi"
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
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
            )
            Row(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
            ) {
                OutlinedTextField(value = textJmlMakanan,
                    singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(150.dp),
                    label = { Text(text = "Jumlah Order") },
                    onValueChange = { textJmlMakanan = it }
                )
                Button(modifier = Modifier.weight(1f),
                    enabled = textJmlMakanan.isNotEmpty(),
                    onClick = {
                        onConfirmButtonClicked(textJmlMakanan.toInt())
                    }) {
                    Text(stringResource(R.string.confirm))
                }
            }

        }
    }
}