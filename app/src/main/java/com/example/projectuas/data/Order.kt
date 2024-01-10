package com.example.projectuas.data

data class Order(
    val id: String = "",
    val nama: String = "",
    val alamat: String = "",
    val noTelp: String = "",
    val jumlah: Int = 0,
    val minuman: String = "",
    val makanan: String = "",
    val harga: String = ""
){
    constructor(): this("","","","",0,"","","")
}


