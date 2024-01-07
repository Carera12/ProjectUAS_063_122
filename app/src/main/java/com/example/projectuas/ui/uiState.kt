package com.example.projectuas.ui

import com.example.projectuas.model.Order

data class UIStateOrder(
    val detailOrder: DetailOrder = DetailOrder()
)

data class DetailOrder(




    val id: String = "",
    val nama: String = "",
    val alamat: String = "",
    val noTelp: String = "",
    val jumlah: Int = 0,
    val minuman: String = "",
    val makanan: String = "",
    val harga: String = ""
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
    harga = harga
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
    harga = harga
)