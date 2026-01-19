package com.example.jetpack3

import androidx.annotation.ColorInt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack3.ui.theme.Black

    val CardColor = Color(0xFFD0BEA2)
@Composable
fun HotDealCart(modifier: Modifier = Modifier){
    var discounts = 75
    var discount_time = "16 MARCH"
    Card(modifier = modifier
        .fillMaxWidth()
        .height(160.dp),
        shape = RoundedCornerShape(24.dp),
        onClick = {},
        colors = CardDefaults.cardColors(containerColor = CardColor)
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Image(painter = painterResource(R.drawable.black_sofa),
                contentDescription = "Black Sofa",
                modifier = modifier.size(150.dp)
                )
            Column(modifier = modifier.fillMaxHeight().padding(start = 15.dp, top = 15.dp, bottom = 15.dp)) {
                Text(text = "UP TO $discounts%",
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Bold
                    )
                Text(text = "DISCOUNT",
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Normal,
                    color = Black
                )
                Text(text = "D.I.Y",
                    fontFamily = droid_regular,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Normal,
                    color = Black
                )

            }
                Text(text = "STARTS\nMIDNIGHT\n$discount_time",
                    modifier = modifier.align ( Alignment.Bottom ).padding(bottom = 16.dp),
                    fontSize = 10.sp,
                    fontFamily = Myfonts,
                    letterSpacing = 0.5.sp,
                    fontWeight = FontWeight.Normal,
                    color = Black
                )

        }
    }
}

@Composable
@Preview
fun HotDealCartPreview(){
    HotDealCart()
}