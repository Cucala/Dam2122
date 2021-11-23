package enriquez.lloret.damfirebase2122

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import enriquez.lloret.damfirebase2122.R
import enriquez.lloret.damfirebase2122.data.Item
import enriquez.lloret.damfirebase2122.ui.theme.DamFirebase2122Theme

class ListsActivity : ComponentActivity() {
    val itemList = listOf(
        Item(R.drawable.the_witcher_portada, "The Witcher", listOf("RPG", "Action", "Adult", "Fantasy", "Classic")),
        Item(R.drawable.the_witcher_portada, "The Witcher 2", listOf("RPG", "Action", "Adult", "Fantasy")),
        Item(R.drawable.the_witcher_portada, "The Witcher 2", listOf("RPG", "Action", "Adult", "Fantasy")),
        Item(R.drawable.the_witcher_portada, "The Witcher 2", listOf("RPG", "Action", "Adult", "Fantasy")),
        Item(R.drawable.the_witcher_portada, "The Witcher 2", listOf("RPG", "Action", "Adult", "Fantasy")),
        Item(R.drawable.the_witcher_portada, "The Witcher", listOf("RPG", "Action", "Adult", "Fantasy", "Open World"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DamFirebase2122Theme {
                ItemList(itemList)
            }
        }
    }

    @Composable
    fun ItemList(itemList: List<Item>){
        LazyColumn {
            items(itemList) {
                itemL -> ItemCard(item = itemL)
            }
        }
    }

    @Composable
    fun ItemCard(item: Item) {
        val image = painterResource(id = item.imageResource)
        Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp,
            modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                val imageModifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                Image(painter = image, contentDescription = "The witcher", modifier = imageModifier,
                    contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = item.title, style = typography.h6)
                for (category in item.categories){
                    Text(text = category, style = typography.body2)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DamFirebase2122Theme {
            ItemList(itemList)
        }
    }
}