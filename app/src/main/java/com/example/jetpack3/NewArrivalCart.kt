package com.example.jetpack3

import AnimatedAddButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack3.ui.theme.goyyYashyl
import org.intellij.lang.annotations.JdkConstants
import androidx.compose.ui.unit.times
@Composable
fun NewArrivalCart(
    product: NewArrivals,
    modifier: Modifier = Modifier
){

    var isFavorite by remember { mutableStateOf(false) }
    val cardColor = Color(0xFFE9E5DE)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth * 0.45f
    Card(
        onClick = {},
            modifier = modifier.width(cardWidth),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        IconButton(onClick = {
            isFavorite = !isFavorite
        },  modifier = Modifier.align(Alignment.End)
            ) {
                Icon(painter = painterResource(if (isFavorite)R.drawable.favorite_filled else R.drawable.favorite), contentDescription = "favorite_icon",
                    modifier = Modifier.size(32.dp),
                    tint = if (isFavorite) Color.Unspecified else Color.Black
                        )

        }
        Row(    
            modifier = modifier.padding(16.dp)
        ) {
            Image(painter = painterResource(id = product.imageRes), contentDescription = "Yellow Sofa",
                modifier = modifier.size(200.dp),
                )
        }

        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = product.title,
                fontFamily = everett
            )
        }
        Row(
        ) {
            Spacer(modifier = modifier.size(16.dp))
            Text(
                text = "$"+product.price,
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                color = goyyYashyl,
                fontSize = 14.sp

            )
            Spacer(modifier = Modifier.weight(1f))
                AnimatedAddButton(
                    modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
                ){
                }
        }

    }
}

@Composable
@Preview
fun NewArrivalCartPreview(){
    val sampleProduct = NewArrivals(
        id = 1,
        title = "Preview Chair",
        price = "25.00",
        imageRes = R.drawable.yello_sofa,
        description = "Gaty gowyyy",
        rating = 4.5,
        reviews = 250,
        offers = "Dostawka mugt"

    )

    // 2. Pass it to the card
    NewArrivalCart(product = sampleProduct)
}