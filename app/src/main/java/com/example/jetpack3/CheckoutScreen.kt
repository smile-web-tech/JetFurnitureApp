package com.example.jetpack3

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpack3.ui.theme.BackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    viewModel: ProductViewModel = viewModel()
) {
    val context = LocalContext.current


    val cartQuantities by viewModel.cartQuantities.collectAsState()
    val products by viewModel.products.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }

    val cartItems = products.filter { cartQuantities.containsKey(it.id) }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(R.drawable.arrow_back), contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {

                    if (name.isBlank() || address.isBlank() || phone.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        isSubmitting = true
                        viewModel.submitOrder(
                            name = name,
                            address = address,
                            phone = phone,
                            onSuccess = {
                                isSubmitting = false
                                Toast.makeText(context, "Order Placed Successfully!", Toast.LENGTH_LONG).show()
                                // Go back to Home and clear the back stack so user can't go back to checkout
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                            onError = { error ->
                                isSubmitting = false
                                Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                enabled = !isSubmitting // Disable button while loading
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Place Order - $${String.format("%.2f", totalPrice)}", fontSize = 18.sp)
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {

            item {
                Text("Order Summary", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 10.dp))
            }

            items(cartItems) { product ->
                val qty = cartQuantities[product.id] ?: 0
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${qty}x  ${product.title}", modifier = Modifier.weight(1f))
                    Text("$${product.price}", fontWeight = FontWeight.Bold)
                }
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("$${String.format("%.2f", totalPrice)}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                }
            }


            item {
                Text("Shipping Details", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}