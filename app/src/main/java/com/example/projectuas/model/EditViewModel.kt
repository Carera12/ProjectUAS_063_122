package com.example.projectuas.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.repositori.RepositoriOrder
import com.example.projectuas.ui.halaman.EditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriOrder
) : ViewModel() {

    var orderUiState by mutableStateOf(UIStateOrder())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[EditDestination.orderIdArg])

    init {
        viewModelScope.launch {
            orderUiState = repositoriSiswa.getOrderStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateOrder(true)
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(orderUiState.detailOrder)) {
            repositoriSiswa.updateOrder(orderUiState.detailOrder.toOrder())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailOrder: DetailOrder) {
        orderUiState =
            UIStateOrder(detailOrder = detailOrder, isEntryValid = validasiInput(detailOrder))
    }

    private fun validasiInput(uiState: DetailOrder = orderUiState.detailOrder) : Boolean{
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && noTelp.isNotBlank() && makanan.isNotBlank() && transaksi.isNotBlank()
        }
    }
}
