package com.example.projectuas.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.projectuas.R
import com.example.projectuas.data.DataOrder.makanan
import com.example.projectuas.data.DataOrder.minuman
import com.example.projectuas.data.Order
import com.example.projectuas.model.MenuViewModel

@Composable
fun MenuOrderView() {
}

@Composable
fun BodyMenu(
    onOrderValueChange: (Order) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        val context = LocalContext.current
        IsiMenu(
            menumakanan = makanan.map {id -> context.resources.getString(id)},
            menuminuman = minuman.map {id -> context.resources.getString(id)},
            onSelectionMakanan = {viewModel.setMakanan(it)},
            onSelectionMinuman = {viewModel.setMinuman(it)},
            onConfirmButtonClicked = {viewModel.setJumlah(it)},
            onNextButtonClicked = {  },
            onCancelButtonClicked = {  })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsiMenu(
    menumakanan: List<String>,
    menuminuman: List<String>,
    onSelectionMakanan: (String) -> Unit,
    onSelectionMinuman: (String) -> Unit,
    onConfirmButtonClicked: (Int) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    var pilihanmakanan by rememberSaveable { mutableStateOf("")    }
    var pilihanminuman by rememberSaveable { mutableStateOf("")    }
    var textJmlBeli by remember { mutableStateOf("")  }

    Column (modifier= modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            )  {
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
                        OutlinedTextField(value = textJmlBeli,
                            singleLine = true,
                            shape = MaterialTheme.shapes.large,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(150.dp),
                            label = { Text(text = "Jumlah Order") },
                            onValueChange = {textJmlBeli = it}
                        )
                        Button(modifier = Modifier.weight(1f),
                            enabled = textJmlBeli.isNotEmpty(),
                            onClick = {onConfirmButtonClicked(textJmlBeli.toInt())
                            }) {
                            Text(stringResource(R.string.confirm))
                        }
                    }
                }
                Column {
                    Text(
                        text = "Minuman"
                    )
                    menuminuman.forEach { item ->
                        Row(modifier = Modifier.selectable(
                            selected = pilihanminuman == item,
                            onClick = {
                                pilihanminuman = item
                                onSelectionMinuman(item)
                            }
                        ),
                            verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = pilihanminuman == item,
                                onClick = {
                                    pilihanminuman = item
                                    onSelectionMinuman(item)
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
                        OutlinedTextField(value = textJmlBeli,
                            singleLine = true,
                            shape = MaterialTheme.shapes.large,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(150.dp),
                            label = { Text(text = "Jumlah Order") },
                            onValueChange = {textJmlBeli = it}
                        )
                        Button(modifier = Modifier.weight(1f),
                            enabled = textJmlBeli.isNotEmpty(),
                            onClick = {onConfirmButtonClicked(textJmlBeli.toInt())
                            }) {
                            Text(stringResource(R.string.confirm))
                        }
                    }
                }
            }

        }
    }
}