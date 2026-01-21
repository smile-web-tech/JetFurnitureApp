package com.example.jetpack3

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import androidx.annotation.Keep

class ProductViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<NewArrivals>>(emptyList())
    val products: StateFlow<List<NewArrivals>> = _products

    private val _cartQuantities = MutableStateFlow<Map<String, Int>>(emptyMap())
    val cartQuantities: StateFlow<Map<String, Int>> = _cartQuantities


    val totalPrice: StateFlow<Double> = combine(_products, _cartQuantities) { products, quantities -> var total = 0.0
        quantities.forEach { (id, qty) ->
            val product = products.find { it.id == id }
            if (product != null) {

                val cleanPrice = product.price
                    .replace("$", "")
                    .replace(",", "")
                    .toDoubleOrNull() ?: 0.0

                total += cleanPrice * qty
            }
        }
        total
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    private val db = FirebaseFirestore.getInstance()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        db.collection("jet_list")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("Firebase", "Error: ${error.message}")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val list = snapshot.toObjects(NewArrivals::class.java)
                    _products.value = list
                }
            }
    }

// ... inside ProductViewModel class ...

    fun submitOrder(
        name: String,
        address: String,
        phone: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val currentCart = _cartQuantities.value
        val currentProducts = _products.value
        val total = totalPrice.value

        // 1. Convert Cart Map to OrderItem List
        val orderItems = currentCart.mapNotNull { (id, quantity) ->
            val product = currentProducts.find { it.id == id }
            if (product != null) {
                OrderItem(
                    productId = id,
                    title = product.title,
                    price = product.price,
                    quantity = quantity
                )
            } else null
        }

        if (orderItems.isEmpty()) {
            onError("Cart is empty")
            return
        }

        // 2. Create Order Object
        val newOrderRef = db.collection("orders").document() // Generate unique ID
        val order = Order(
            id = newOrderRef.id,
            customerName = name,
            customerAddress = address,
            customerPhone = phone,
            items = orderItems,
            totalPrice = total,
            status = "Pending"
        )

        // 3. Save to Firebase
        newOrderRef.set(order)
            .addOnSuccessListener {
                // Clear the cart locally after success
                _cartQuantities.value = emptyMap()
                onSuccess()
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Unknown error")
            }
    }
    fun addToCart(productId: String) {
        val current = _cartQuantities.value.toMutableMap()
        current[productId] = (current[productId] ?: 0) + 1
        _cartQuantities.value = current
    }

    fun removeFromCart(productId: String) {
        val current = _cartQuantities.value.toMutableMap()
        val currentQty = current[productId] ?: 0
        if (currentQty > 0) {
            current[productId] = currentQty - 1
            if (current[productId] == 0) {
                current.remove(productId)
            }
        }
        _cartQuantities.value = current
    }
}