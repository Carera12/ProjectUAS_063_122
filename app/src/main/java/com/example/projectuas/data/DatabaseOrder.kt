package com.example.projectuas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Order::class], version = 1, exportSchema = false)
abstract class DatabaseOrder : RoomDatabase(){
    abstract fun OrderDao() : OrderDao

    companion object{
        @Volatile
        private var Instanse: DatabaseOrder? = null

        fun getDatabase(context: Context): DatabaseOrder{
            return (Instanse?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabaseOrder::class.java,
                    "siswa_database")
                    .build().also { Instanse=it }
            })
        }
    }
}
