package com.example.projectuas.repositori

import android.content.Context
import com.example.projectuas.data.DatabaseOrder

interface ContainerApp {
    val repositoriSiswa : RepositoriOrder
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriSiswa: RepositoriOrder by lazy {
        OfflineRepositori(DatabaseOrder.getDatabase(context).OrderDao())
    }
}