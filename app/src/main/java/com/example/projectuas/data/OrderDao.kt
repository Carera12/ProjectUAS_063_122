package com.example.projectuas.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(order: Order)

    @Update
    suspend fun update(order: Order)

    @Delete
    suspend fun delete(order: Order)

    @Query("SELECT * from tblOrder WHERE id = :id")
    fun getOrder(id: Int): Flow<Order>

    @Query("SELECT * from tblOrder ORDER BY nama ASC")
    fun getAllOrder(): Flow<List<Order>>
}