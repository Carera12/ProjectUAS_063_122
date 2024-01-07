package com.example.projectuas.data

import com.google.firebase.firestore.FirebaseFirestore


interface AppContainer{
    val orderRepository: OrderRepository
}
class OrderContainer : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val orderRepository: OrderRepository by lazy {
        OrderRepositoryImpl(firestore)
    }

}