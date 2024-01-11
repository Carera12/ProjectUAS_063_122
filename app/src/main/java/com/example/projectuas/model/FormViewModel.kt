package com.example.projectuas.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.projectuas.data.Order
import com.example.projectuas.repositori.RepositoriOrder

class FormViewModel(private val repositoriSiswa: RepositoriOrder): ViewModel() {

    /*
    * Berisi status Siswa saat ini
    */
    var uiStateSiswa by mutableStateOf(UIStateOrder())
        private set

    /* Fungsi untuk memvalidasi input*/
    private fun validasiInput(uiState: DetailOrder = uiStateSiswa.detailOrder): Boolean{
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && noTelp.isNotBlank()
        }
    }

    fun updateUiState(detailOrder: DetailOrder) {
        uiStateSiswa =
            UIStateOrder(detailOrder = detailOrder, isEntryValid = validasiInput(detailOrder))
    }

    suspend fun saveOrder() {
        if (validasiInput()) {
            repositoriSiswa.insertOrder(uiStateSiswa.detailOrder.toOrder())
        }
    }
}

data class UIStateOrder(
    val detailOrder: DetailOrder = DetailOrder(),
    val isEntryValid: Boolean = false
)

data class DetailOrder(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val noTelp: String = "",
    val jumlah: Int = 0,
    val minuman: String = "",
    val makanan: String = "",
    val harga: String = "",
    val transaksi: String = ""
)

/*Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun DetailOrder.toOrder(): Order = Order(
    id = id,
    nama = nama,
    alamat = alamat,
    noTelp = noTelp,
    jumlah = 0,
    minuman = minuman,
    makanan = makanan,
    harga = harga,
    transaksi = transaksi
)

fun Order.toUiStateOrder(isEntryValid: Boolean = false): UIStateOrder = UIStateOrder(
    detailOrder = this.toDetailOrder(),
    isEntryValid = isEntryValid
)

fun Order.toDetailOrder(): DetailOrder = DetailOrder(
    id = id,
    nama = nama,
    alamat = alamat,
    noTelp = noTelp,
    jumlah = 0,
    minuman = minuman,
    makanan = makanan,
    harga = harga,
    transaksi = transaksi
)