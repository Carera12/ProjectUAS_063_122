package com.example.projectuas.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.projectuas.R
import com.example.projectuas.model.DetailOrder
import com.example.projectuas.navigasi.DestinasiNavigasi
import com.example.projectuas.navigasi.OrderTopAppBar
import com.example.projectuas.ui.komponen.FormatLabelHarga
import kotlinx.coroutines.launch


object DestinasiRiwayat : DestinasiNavigasi {
    override val route: String = "Riwayat"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanRiwayat(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier =modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            OrderTopAppBar(
                title = stringResource(DestinasiRiwayat.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior)
        }
    ) {innerPadding ->
        IsiRiwayat(
            detailOrder = DetailOrder(),
            onCancelButtonClicked = navigateBack,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@Composable
fun IsiRiwayat(
    detailOrder: DetailOrder,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Pair(stringResource(R.string.jumlah), detailOrder.jumlah),
        Pair(stringResource(R.string.makanan), detailOrder.makanan),
        Pair(stringResource(R.string.minuman), detailOrder.minuman)
    )

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column (
            modifier=
            Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(text = "Nama")
            Text(text = detailOrder.nama)
            Divider(thickness =
            dimensionResource(R.dimen.thickness_divider))
            Text(text = "Alamat")
            Text(text = detailOrder.alamat)
            Divider(thickness =
            dimensionResource(R.dimen.thickness_divider))
            Text(text = "No Telepon")
            Text(text = detailOrder.noTelp)
            Divider(thickness =
            dimensionResource(R.dimen.thickness_divider))
            Text(text = "Makanan")
            Text(text = detailOrder.makanan)
            Divider(thickness =
            dimensionResource(R.dimen.thickness_divider))
            Text(text = "Minuman")
            Text(text = detailOrder.minuman)
            Text(text = "Metode Pembayaran")
            Text(text = detailOrder.transaksi)
            Divider(thickness =
            dimensionResource(R.dimen.thickness_divider))
            items.forEach { item ->
                Column {
                    Text(item.first.uppercase())
                    Text(text = item.second.toString(), fontWeight = FontWeight.Bold)
                }
                Divider(thickness =
                dimensionResource(R.dimen.thickness_divider))
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            FormatLabelHarga(subtotal = detailOrder.harga,
                modifier = Modifier.align(Alignment.End)
            )
        }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)) //ke samping
        ) {
            items.forEach { item ->
                Column {
                    Text(text = item.first.uppercase())
                    Text(text = item.second.toString(), fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                Divider()

            }

            Row(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCancelButtonClicked
                ) {
                    Text(stringResource(R.string.back))
                }
            }
        }
    }
    }
}