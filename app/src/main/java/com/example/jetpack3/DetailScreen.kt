package com.example.jetpack3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jetpack3.ui.theme.BackgroundColor
import com.example.jetpack3.ui.theme.Black
import com.example.jetpack3.ui.theme.SolakColor
import com.example.jetpack3.ui.theme.dividerColor
import com.example.jetpack3.ui.theme.goyyYashyl

@Composable
fun DetailScreen(
    productId: String,
    navController: NavController,
    viewModel: ProductViewModel = viewModel()
){

    val firebaseProducts = viewModel.products.collectAsState().value

    // 2. Find the specific product from that downloaded list
    val product = firebaseProducts.find { it.id == productId }
    val bottomCardCol = Color(0xFFE1DCD3)
    val cardColor = Color(0xFFE9E5DE)


    if (product != null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BackgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp)
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
            }
//Shu yerden bashlayar

            Box(
                modifier = Modifier.fillMaxSize()
            ) {


                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = product.title,
                        fontFamily = Myfonts,
                        letterSpacing = 1.sp
                    )
                    AsyncImage(
                         model  = product.imageUrl,
                         contentDescription = product.title,
                         modifier = Modifier
                             .fillMaxWidth()
                             .height(300.dp)
                             .padding(16.dp),
                         contentScale = ContentScale.Fit
                     )
                    Text(
                        text = product.description,
                        fontFamily = everett,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = "Starmyra",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${product.rating}  |  ${product.reviews} Reviews",
                            fontFamily = everett,
                            modifier = Modifier.padding(top = 10.dp),
                            color = SolakColor
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColor)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Text(
                                text = "Offers",
                                fontFamily = everett,
                                fontSize = 18.sp
                            )
                            HorizontalDivider(
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                                    .height(5.dp),
                                color = dividerColor
                            )
                            Text(
                                text = "Citybank offers",
                                fontFamily = everett,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = product.offers,
                                fontFamily = everett,
                                fontSize = 14.sp,
                                color = Black

                            )
                        }


                    }
                }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // ✅ This locks it to the bottom
                            .fillMaxWidth()
                            .background(color = bottomCardCol) // Important: Background prevents seeing text behind it
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.cart),
                                contentDescription = "Card"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "$"+product.price,
                                fontFamily = everett,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = goyyYashyl
                            )
                        }

                    }
                Button(
                    onClick = { },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 24.dp, bottom = 10.dp)
                        .size(120.dp)
                        .shadow(15.dp, CircleShape)
                ) {
                    Text(
                        text = "Buy Now",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = everett
                    )
                }

            }


        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Product not found")
        }
    }
    }

@Composable
@Preview
fun DetailScreenPreview(){
    val context = LocalContext.current
    val fakeNavController = NavController(context)


    DetailScreen(
        productId = "1",
        navController = fakeNavController
    )
}