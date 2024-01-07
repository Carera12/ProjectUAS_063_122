package com.example.projectuas

import android.app.Application
import com.example.projectuas.data.AppContainer
import com.example.projectuas.data.OrderContainer

class OrderAplikation: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = OrderContainer()
    }
}