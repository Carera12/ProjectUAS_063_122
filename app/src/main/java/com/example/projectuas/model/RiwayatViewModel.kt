package com.example.projectuas.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.repositori.RepositoriOrder
import com.example.projectuas.ui.halaman.DestinasiRiwayat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RiwayatViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriOrder
): ViewModel(){
    private val orderId: Int = checkNotNull(savedStateHandle[DestinasiRiwayat.siswaIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriSiswa.getOrderStream(orderId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(detailOrder = it.toDetailOrder())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )
    suspend fun deleteItem() {
        repositoriSiswa.deleteOrder(uiState.value.detailOrder.toOrder())
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailOrder: DetailOrder = DetailOrder()
)