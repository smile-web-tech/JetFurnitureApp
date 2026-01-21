package com.example.jetpack3

import com.google.firebase.Timestamp
import androidx.annotation.Keep
@Keep
data class Order(
    val id: String = "",
    val customerName: String = "",
    val customerAddress: String = "",
    val customerPhone: String = "",
    val items: List<OrderItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val status: String = "Pending", // Pending, Shipped, Delivered
    val timestamp: Timestamp = Timestamp.now()
)

@Keep
data class OrderItem(
    val productId: String = "",
    val title: String = "",
    val price: String = "",
    val quantity: Int = 0
)