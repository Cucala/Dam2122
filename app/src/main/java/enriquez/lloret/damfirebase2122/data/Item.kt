package enriquez.lloret.damfirebase2122.data

import androidx.annotation.DrawableRes

data class Item(
    @DrawableRes val imageResource: Int,
    val title: String,
    val categories: List<String>
)
