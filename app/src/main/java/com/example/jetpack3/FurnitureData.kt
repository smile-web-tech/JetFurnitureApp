package com.example.jetpack3


//val newArrivalProducts = listOf(
//    NewArrivals("Leatherette Sofa","15.18",
//        R.drawable.yello_sofa, 1,"Gaty yumshak dalihana diwan", 4.8, 150, "1ini alana ikinji mugt dal" ),
//    NewArrivals("Ork Stool","24.18",
//        R.drawable.some_chair,2, "Kacestwanny stol",4.3, 260, "20% skidka eger 2 den kop alsanyz"),
//    NewArrivals("Royal palm sofa","99.99",
//        R.drawable.black_sofa, 3, "Very soft comfortable sofa", 4.0, 499, "Dostawka we gurnamak mugt"),
//
//    )

data class NewArrivals(
    val id: String = "",
    val title: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val rating: String = "0.0",
    val reviews: String = "",
    val offers: String = ""
)