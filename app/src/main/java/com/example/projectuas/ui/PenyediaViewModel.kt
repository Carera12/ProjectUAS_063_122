package com.example.projectuas.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectuas.OrderAplikation
import com.example.projectuas.ui.menu.viewmodel.MenuViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            MenuViewModel(aplikasiKontak().container.orderRepository)
        }
    }
}

/**
 * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari
 * [AplikasiSiswa]
 */
fun CreationExtras.aplikasiKontak(): OrderAplikation =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as OrderAplikation)
