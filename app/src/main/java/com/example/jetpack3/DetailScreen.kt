package com.example.jetpack3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpack3.ui.theme.BackgroundColor
import com.example.jetpack3.ui.theme.Black
import com.example.jetpack3.ui.theme.SolakColor
import com.example.jetpack3.ui.theme.dividerColor

@Composable
fun DetailScreen(
    productId: Int,
    navController: NavController
){
    val product = newArrivalProducts.find{it.id == productId}
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

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
            Text(text = product.title,
            fontFamily = Myfonts,
                letterSpacing = 1.sp
            )
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
                Text(text = product.description,
                    fontFamily = everett,
                    modifier = Modifier.padding(top = 10.dp)
                    )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(painter = painterResource(R.drawable.star), contentDescription = "Starmyra",
                        modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "${product.rating}  |  ${product.reviews} Reviews",
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
                        Text(text = "Offers",
                            fontFamily = everett,
                            fontSize = 18.sp
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                                .height(5.dp),
                            color = dividerColor
                        )
                        Text(text = "Citybank offers",
                            fontFamily = everett,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = product.offers,
                            fontFamily = everett,
                            fontSize = 14.sp,
                            color = Black

                        )
                    }

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
        productId = 1,
        navController = fakeNavController
    )
}