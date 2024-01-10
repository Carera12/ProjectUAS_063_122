package com.example.projectuas.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RiwayatViewModel :ViewModel(){
    private val _stateUI = MutableStateFlow(DetailOrder())
    fun setRiwayat(listContact: MutableList<String>) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nama = listContact[0],
                alamat = listContact[1],
                noTelp = listContact[2],
                makanan = listContact[3],
                minuman = listContact[4],
                transaksi = listContact[5],
            )
        }
    }

}