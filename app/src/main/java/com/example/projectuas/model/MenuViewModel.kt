package com.example.projectuas.model

import androidx.lifecycle.ViewModel
import com.example.projectuas.data.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 3000
class MenuViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(Order())
    val stateUI: StateFlow<Order> = _stateUI.asStateFlow()

    fun setJumlah(jmlEsJumbo:Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHarga(jumlah = jmlEsJumbo)
            )
        }
    }
    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ): String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format((kalkulasiHarga))
    }
    fun setMakanan(pilih:String){
        _stateUI.update { currentState -> currentState.copy(makanan = pilih)
        }
    }

    fun setMinuman(pilih2:String){
        _stateUI.update { currentState -> currentState.copy(minuman = pilih2)
        }
    }
}