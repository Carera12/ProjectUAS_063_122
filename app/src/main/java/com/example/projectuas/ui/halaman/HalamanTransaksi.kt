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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.projectuas.R




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