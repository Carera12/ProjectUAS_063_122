package com.example.projectuas.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.repositori.RepositoriOrder
import com.example.projectuas.ui.halaman.DetailDestinasi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriOrder: RepositoriOrder
): ViewModel(){
    private val siswaId: Int = checkNotNull(savedStateHandle[DetailDestinasi.orderIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriOrder.getOrderStream(siswaId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(detailSiswa = it.toDetailOrder())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )
    suspend fun deleteItem() {
        repositoriOrder.deleteOrder(uiState.value.detailSiswa.toOrder())
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailSiswa: DetailOrder = DetailOrder()
)