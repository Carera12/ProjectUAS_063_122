package com.example.projectuas.model

import androidx.lifecycle.ViewModel
import com.example.projectuas.data.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 5000
class MenuViewModel : ViewModel() {
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
    fun resetOrder(){
        _stateUI.value = DetailOrder()
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
//    private fun hitungHargaMakanan(
//        jumlah: Int = _stateUI.value.jumlah,
//    ): String {
//        val kalkulasiHarga = jumlah * HARGA_PER_BOX
//
//        return NumberFormat.getNumberInstance().format((kalkulasiHarga))
//    }
    fun setMakanan(pilih:String){
        _stateUI.update { currentState -> currentState.copy(makanan = pilih)
        }
    }
        fun setTransaksi(pilih:String){
            _stateUI.update { currentState -> currentState.copy(transaksi = pilih)
            }
        }


//    fun setMinuman(pilih2:String){
//        _stateUI.update { currentState -> currentState.copy(minuman = pilih2)
//        }
//    }

}

data class UIStateOrder(
    val detailOrder: DetailOrder = DetailOrder()
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

fun Order.toUiStateOrder(): UIStateOrder = UIStateOrder(
    detailOrder = this.toDetailOrder()
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