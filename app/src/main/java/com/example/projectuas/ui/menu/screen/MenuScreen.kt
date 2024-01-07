package com.example.projectuas.ui.menu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.R
import com.example.projectuas.model.DataOrder
import com.example.projectuas.model.DataOrder.makanan
import com.example.projectuas.model.DataOrder.minuman
import com.example.projectuas.navigasi.DestinasiNavigasi
import com.example.projectuas.navigasi.OrderTopAppBar
import com.example.projectuas.ui.DetailOrder
import com.example.projectuas.ui.PenyediaViewModel
import com.example.projectuas.ui.UIStateOrder
import com.example.projectuas.ui.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.launch


object MenuDestination: DestinasiNavigasi {
    override val route  = "item_entry"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = viewModel(factory = PenyediaViewModel.Factory),
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            OrderTopAppBar(
                title = stringResource(MenuDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        MenuBody(
            uiStateOrder = viewModel.uiStateOrder,
            onOrderValueChange = viewModel::updateUiState,
            onSaveClick = { coroutineScope.launch {
                viewModel.saveOrder()
                navigateBack()
            }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}
@Composable
fun MenuBody(
    uiStateOrder: UIStateOrder,
    onOrderValueChange: (DetailOrder) -> Unit,
    onSaveClick: () -> Unit,
    menuViewModel: MenuViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        val context = LocalContext.current
        IsiMenuScreen(
            detailOrder = uiStateOrder.detailOrder,
            orderPilihanMakanan = makanan.map { id -> context.resources.getString(id) },
            orderPilihanMinuman = minuman.map { id -> context.resources.getString(id) },
            onSelectionChanged = { menuViewModel.setMakanan(it) },
            onPilihChanged = { menuViewModel.setMinuman(it) }
        )

        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.submit_button))
        }
    }
}

@Composable
fun IsiMenuScreen(
    detailOrder: DetailOrder,
    orderPilihanMakanan: List<String>,
    orderPilihanMinuman: List<String>,
    onSelectionChanged: (String) -> Unit,
    onPilihChanged: (String) -> Unit,

){
    var orderYgDipilih by remember { mutableStateOf("") }
    var orderDipilih by remember { mutableStateOf("") }
    var listData: MutableList<String> = mutableListOf( orderYgDipilih, orderDipilih)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(R.dimen.padding_small)),
            verticalArrangement = Arrangement.SpaceEvenly

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Makanan"
                    )
                    orderPilihanMakanan.forEach { item ->
                        Row(modifier = Modifier.selectable(
                            selected = orderYgDipilih == item,
                            onClick = {
                                orderYgDipilih = item
                                onSelectionChanged(item)
                            }
                        ),
                            verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = orderYgDipilih == item,
                                onClick = {
                                    orderYgDipilih = item
                                    onSelectionChanged(item)
                                }
                            )
                            Text(item)
                        }
                    }
                }
                Column {
                    Text(
                        text = "Minuman"
                    )
                    orderPilihanMinuman.forEach { item ->
                        Row(modifier = Modifier.selectable(
                            selected = orderDipilih == item,
                            onClick = {
                                orderDipilih = item
                                onPilihChanged(item)
                            }
                        ),
                            verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = orderDipilih == item,
                                onClick = {
                                    orderDipilih = item
                                    onPilihChanged(item)
                                }
                            )
                            Text(item)
                        }
                    }
                }
            }

        }
    }

}