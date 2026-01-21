package com.example.jetpack3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpack3.ui.theme.BackgroundColor
import com.example.jetpack3.ui.theme.goyyYashyl

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: ProductViewModel = viewModel()
){
    val cartQuantities by viewModel.cartQuantities.collectAsState()
    val products by viewModel.products.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()
    val cartItems = products.filter { cartQuantities.containsKey(it.id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, bottom = 10.dp)
        ) {

            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Text(
                text = "My Cart",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Your Cart is Empty", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cartItems) { product ->
                    val qty = cartQuantities[product.id] ?: 0

                    CartItemRow(
                        product = product,
                        quantity = qty,
                        onAdd = { viewModel.addToCart(product.id) },
                        onRemove = { viewModel.removeFromCart(product.id) },
                        onItemClick = { navController.navigate("details/${product.id}") }
                    )
                }
            item{
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total amount ", fontFamily = montserrat, fontWeight = FontWeight.Bold,)
                    Text(text = "$$totalPrice", fontFamily = montserrat, fontWeight = FontWeight.Bold, color = goyyYashyl)
                }

            }
                // Add Checkout Button at the bottom of the list
                item {
                    Button(
                        onClick = { navController.navigate("checkout") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 40.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text("Proceed to Checkout")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CartScreenPreview(){
    val context = LocalContext.current
    val fakeNavController = NavController(context)
    CartScreen(
        navController = fakeNavController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemRow(
    product: NewArrivals,
    quantity: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    onItemClick: () -> Unit
) {
    Card(
        onClick = onItemClick, // This makes the whole row clickable
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp) // Fixed height for consistency
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coil.compose.AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    color = Color.Black
                )
                Text(
                    text = "$${product.price}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                ) {
                    IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = if (quantity == 1) androidx.compose.material.icons.Icons.Default.Delete else androidx.compose.material.icons.Icons.Default.Remove,
                            contentDescription = "Remove",
                            modifier = Modifier.size(16.dp),
                            tint = if (quantity == 1) Color.Red else Color.Black
                        )
                    }


                    Text(
                        text = "$quantity",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )


                    IconButton(onClick = onAdd, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Black
                        )
                    }
                }

            }

        }
    }
}