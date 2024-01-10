package com.example.projectuas.repositori

import com.example.projectuas.data.Order
import com.example.projectuas.data.OrderDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositori (private val orderDao: OrderDao) : RepositoriOrder {
    override fun getAllOrderStream(): Flow<List<Order>> = orderDao.getAllOrder()

    override fun getOrderStream(id: Int): Flow<Order?> {
        return orderDao.getOrder(id)
    }

    override suspend fun insertOrder(order: Order) = orderDao.insert(order)

    override suspend fun deleteOrder(order: Order) = orderDao.delete(order)

    override suspend fun updateOrder(order: Order) {
        orderDao.update(order)
    }
}