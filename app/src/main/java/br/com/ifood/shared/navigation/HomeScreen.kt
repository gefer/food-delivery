package br.com.ifood.shared.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.ifood.R

@Composable
fun HomeScreen(navController: NavController) {
    val transition = rememberInfiniteTransition()
    val scale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val backgroundColor = MaterialTheme.colors.background
    val primaryButtonColor = Color(0xFFEF5350)
    val secondaryButtonColor = Color(0xFF4CAF50)
    val buttonBorderColor = Color(0xFFB71C1C)
    val textStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.pizza_logo),
            contentDescription = "Avatar de Destaque",
            modifier = Modifier
                .sizeIn(minWidth = 100.dp, minHeight = 100.dp)
                .clip(CircleShape)
                .border(3.dp, Color.White, CircleShape)
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Bem-vindo ao Food Delivery!",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { navController.navigate(Destinations.Products.route) },
            modifier = Modifier
                .scale(scale)
                .padding(8.dp)
                .border(2.dp, buttonBorderColor, RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = secondaryButtonColor),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Produtos",
                style = textStyle,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Destinations.Orders.route) },
            modifier = Modifier
                .scale(scale)
                .padding(8.dp)
                .border(2.dp, buttonBorderColor, RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = primaryButtonColor),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Pedidos",
                style = textStyle,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Destinations.Delivery.route) },
            modifier = Modifier
                .scale(scale)
                .padding(8.dp)
                .border(2.dp, Color(0xFFF57C00), RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF57C00)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Entrega",
                style = textStyle,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate(Destinations.Cart.route) },
            modifier = Modifier
                .scale(scale)
                .padding(8.dp)
                .border(2.dp, Color(0xFF9C27B0), RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9C27B0)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Carrinho",
                style = textStyle,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
