package com.example.projectuas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblSiswa")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val noTelp: String = "",
    val jumlah: Int = 0,
    val minuman: String = "",
    val makanan: String = "",
    val harga: String = ""
)


