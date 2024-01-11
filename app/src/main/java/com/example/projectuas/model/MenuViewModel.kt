package com.example.projectuas.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.projectuas.data.Order
import com.example.projectuas.repositori.RepositoriOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 5000
class MenuViewModel(private val repositoriOrder: RepositoriOrder) : ViewModel() {
    private val _stateUI = MutableStateFlow(DetailOrder())
    val stateUI: StateFlow<DetailOrder> = _stateUI.asStateFlow()

    var uiStateOrder by mutableStateOf(UIStateOrder())
        private set

    private fun validasiInputData(uiState: DetailOrder = uiStateOrder.detailOrder): Boolean{
        return with(uiState) {
            makanan.isNotBlank() && transaksi.isNotBlank()
        }
    }

    fun updateUiState(detailOrder: DetailOrder) {
        uiStateOrder =
            UIStateOrder(detailOrder = detailOrder, isEntryValid = validasiInputData(detailOrder))
    }

    suspend fun saveSiswa() {
        if (validasiInputData()) {
            repositoriOrder.insertOrder(uiStateOrder.detailOrder.toOrder())
        }
    }


    fun setJumlah(jmlEsJumbo:Int){
        _stateUI.update { stateSaatIni ->

            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHargaMinuman(jmlEsJumbo)
            )
        }
    }
    private fun hitungHargaMinuman(
        jumlah: Int = _stateUI.value.jumlah,
    ): String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format((kalkulasiHarga))
    }

    fun setMakanan(pilih:String){
        _stateUI.update { currentState -> currentState.copy(makanan = pilih)
        }
    }
        fun setTransaksi(pilih:String){
            _stateUI.update { currentState -> currentState.copy(transaksi = pilih)
            }
        }

}

