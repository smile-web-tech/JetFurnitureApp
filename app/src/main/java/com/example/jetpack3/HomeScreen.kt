package com.example.jetpack3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*;
import com.example.jetpack3.ui.theme.BackgroundColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope =rememberCoroutineScope ()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Furniture App")
                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {Text("Profile")},
                        selected = false,
                        onClick = {}
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
                        IconButton(onClick = {}) {
                            Icon(painter = painterResource(id = R.drawable.cart), contentDescription = "Cart")
                        }
                    }
                )
            }
        ){ innerPadding ->
            // WE USE THE PADDING FROM SCAFFOLD HERE
            FurnitureContent(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun FurnitureContent(modifier: Modifier) {

    val newArrivalProducts = listOf(
        NewArrivals("Leatherette Sofa","15.8", R.drawable.yello_sofa),
        NewArrivals("Ork Stool","24.8", R.drawable.some_chair),
        NewArrivals("Royal palm sofa","99.9", R.drawable.black_sofa),

    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(16.dp)
    ) {
        Text(text = "Hot deals", fontFamily = Myfonts, letterSpacing = 1.sp )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
        ) {
            item{
                HotDealCart(
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(top=15.dp)
        ) {
            item {
                Text(text = "New Arrivals", fontFamily = Myfonts, letterSpacing = 1.sp,)
            }
            item {
                LazyRow(
                    modifier = Modifier.padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                   items(items = newArrivalProducts){
                       item -> NewArrivalCart(product = item)
                   }
                }
            }

        }
    }
}


data class NewArrivals(
    val title: String,
    val price: String,
    val imageRes: Int
)


@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen()
}