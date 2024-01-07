package com.example.projectuas.data

import android.content.ContentValues
import android.util.Log
import com.example.projectuas.model.Order
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface OrderRepository {
    fun getAll(): Flow<List<Order>>
    suspend fun save(Order: Order): String
    suspend fun update(Order: Order)
    suspend fun delete(OrderId: String)
    fun getOrderById(OrderId: String): Flow<Order>
}

class OrderRepositoryImpl(private val firestore: FirebaseFirestore): OrderRepository{
    override fun getAll(): Flow<List<Order>> = flow {
        val snapshot = firestore.collection("Order")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val Order = snapshot.toObjects(Order::class.java)
        emit(Order)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(Order: Order): String {
        return try {
            val documentReference = firestore.collection("Order")
                .add(Order)
                .await()

            firestore.collection("Order")
                .document(documentReference.id)
                .set(Order.copy(id= documentReference.id))
            "Berhasil + ${documentReference.id}"
        }catch (e: Exception){
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(Order: Order) {
        firestore.collection("Order")
            .document(Order.id)
            .set(Order)
            .await()
    }

    override suspend fun delete(OrderId: String) {
        firestore.collection("Order")
            .document(OrderId)
            .delete()
            .await()
    }

    override fun getOrderById(OrderId: String): Flow<Order> {
        return flow{
            val snapshot = firestore.collection("Order")
                .document(OrderId)
                .get()
                .await()
            val Order = snapshot.toObject(Order::class.java)
            emit(Order!!)
        }.flowOn(Dispatchers.IO)
    }

}