package com.example.projectuas

import android.app.Application
import com.example.projectuas.repositori.ContainerApp
import com.example.projectuas.repositori.ContainerDataApp

class AppOrder : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainya untuk mendapatkan dependensi
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}