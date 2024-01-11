package com.example.projectuas.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectuas.AppOrder

object PenyediaViewModel {
    val Factory = viewModelFactory {


        initializer {
            RiwayatViewModel(
                createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa
            )
        }
    }
}

/**
 * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari
 * [AplikasiSiswa]
 */
fun CreationExtras.aplikasiSiswa(): AppOrder =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppOrder)
