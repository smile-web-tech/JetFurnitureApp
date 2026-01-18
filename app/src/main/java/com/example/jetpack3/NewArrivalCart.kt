package com.example.jetpack3

import AnimatedAddButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpack3.ui.theme.goyyYashyl

@Composable
fun NewArrivalCart(
    product: NewArrivals,
    modifier: Modifier = Modifier
){
    val cardColor = Color(0xFFE9E5DE)
    Card(

            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Row(
            modifier = modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(id = product.imageRes), contentDescription = "Yellow Sofa",
                modifier = modifier.size(200.dp)
                )
            IconButton(onClick = {
            }) {
                Icon(painter = painterResource(R.drawable.favorite), contentDescription = "favorite_icon")
            }
        }

        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = product.title,
                fontFamily = everett
            )
            Spacer(modifier = modifier.size(16.dp))
            Text(
                text = "$"+product.price,
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                color = goyyYashyl

            )
        }
        AnimatedAddButton (){

        }

    }
}

@Composable
@Preview
fun NewArrivalCartPreview(){
    val sampleProduct = NewArrivals(
        title = "Preview Chair",
        price = "25.00",
        imageRes = R.drawable.yello_sofa // Use any image you have in res/drawable
    )

    // 2. Pass it to the card
    NewArrivalCart(product = sampleProduct)
}