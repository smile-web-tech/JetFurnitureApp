package com.example.jetpack3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ProductViewModel = viewModel()
){
    val products by viewModel.products.collectAsState()
    val cartQuantities by viewModel.cartQuantities.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()

    val totalItems = cartQuantities.values.sum()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val uriHandler = androidx.compose.ui.platform.LocalUriHandler.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("There could be your Buisness ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                    NavigationDrawerItem(
                        label = {Text("By: Ysmayyl. M")},
                        selected = false,
                        onClick = {},

                    )
                    NavigationDrawerItem(
                        label = {Text("Visit Admin Panel")},
                        selected = false,
                        icon = {
                            // Optional: Add a link icon
                            Icon(androidx.compose.material.icons.Icons.Default.Link, contentDescription = null)
                        },
                        onClick = {
                            uriHandler.openUri("https://iospo.org/jetAdmin")
                        },

                        )
                }
            }
        }
    ) {
        Scaffold(
            containerColor = BackgroundColor,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    title = {Text("")},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(painter = painterResource(id = R.drawable.menu_drawer), contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("cart")
                        }) {
                            BadgedBox(
                                badge = {
                                    if (totalItems > 0) {
                                        Badge { Text(totalItems.toString()) }
                                    }
                                }
                            ) {
                                Icon(painter = painterResource(id = R.drawable.cart), contentDescription = "Cart")
                            }
                        }
                    }
                )
            },
            bottomBar = {
                if (totalItems > 0) {
                    CartSummaryBar(
                        totalPrice = totalPrice,
                        itemCount = totalItems,
                        onCheckoutClick = {
                            navController.navigate("cart")
                        }
                    )
                }
            }
        ){ innerPadding ->

            FurnitureContent(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                products = products,
                cartQuantities = cartQuantities,
                onAddToCart = { productId ->
                    viewModel.addToCart(productId)
                },
                onRemoveFromCart = { productId ->
                    viewModel.removeFromCart(productId)
                }
            )
        }
    }
}

@Composable
fun FurnitureContent(
    modifier: Modifier,
    navController: NavController,
    products: List<NewArrivals>,
    cartQuantities: Map<String, Int>,
    onAddToCart: (String) -> Unit,
    onRemoveFromCart: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(16.dp)
    ) {
        item {
            Text(text = "Hot deals", letterSpacing = 1.sp, fontFamily = Myfonts)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            if (products.isNotEmpty()) {
                HotDealCart(product = products[0])
            } else {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                    Text("Loading Best Deal...", color = Color.Gray)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "New Arrivals", letterSpacing = 1.sp, fontFamily = Myfonts)
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            if (products.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    items(products){ product ->
                        val qty = cartQuantities[product.id] ?: 0
                        NewArrivalCart(
                            quantity = qty,
                            product = product,
                            modifier = Modifier.clickable {
                                navController.navigate("details/${product.id}")
                            },
                            onAddClick = {
                                onAddToCart(product.id)
                            },
                            onRemoveClick = {
                                onRemoveFromCart(product.id)
                            }
                        )
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                    Text("Loading items...", color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun CartSummaryBar(
    totalPrice: Double,
    itemCount: Int,
    onCheckoutClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 15.dp, shape = RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Total ($itemCount items)",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "$${String.format("%.2f", totalPrice)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Button(
                onClick = onCheckoutClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Checkout", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val context = LocalContext.current
    val fakeNavController = NavController(context)

    val fakeProducts = listOf(
        NewArrivals(
            id = "1",
            title = "Modern Yellow Sofa",
            price = "250.00",
            imageUrl = "", // Empty image for preview is fine
            description = "A nice sofa",
            rating = "4.8",
            reviews = "120",
            offers = "Free Shipping"
        ),
        NewArrivals(
            id = "2",
            title = "Minimalist Chair",
            price = "150.00",
            imageUrl = "",
            description = "Comfy chair",
            rating = "4.5",
            reviews = "85",
            offers = "Discount"
        )
    )

    val fakeCart = mapOf("1" to 2)

    FurnitureContent(
        modifier = Modifier.fillMaxSize(),
        navController = fakeNavController,
        products = fakeProducts,
        cartQuantities = fakeCart,
        onAddToCart = {},
        onRemoveFromCart = {}
    )
}