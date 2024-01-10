package com.example.projectuas.repositori

import com.example.projectuas.data.Order
import kotlinx.coroutines.flow.Flow

interface RepositoriOrder {
    fun getAllOrderStream(): Flow<List<Order>>

    fun getOrderStream(id: Int): Flow<Order?>

    suspend fun insertOrder(order: Order)

    suspend fun deleteOrder(order: Order)

    suspend fun updateOrder(order: Order)
}