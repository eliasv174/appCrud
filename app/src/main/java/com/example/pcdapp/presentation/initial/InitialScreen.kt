package com.example.pcdapp.presentation.initial

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pcdapp.R
import com.example.pcdapp.ui.theme.BackgroundButton
import com.example.pcdapp.ui.theme.Black
import com.example.pcdapp.ui.theme.Gray
import com.example.pcdapp.ui.theme.Green
import com.example.pcdapp.ui.theme.ShapeButton
import com.google.firebase.annotations.concurrent.Background


@Preview
@Composable
fun InitialScreen(navigateToLogin: () -> Unit = {}, navigateToSignUp: () -> Unit = {}){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Brush.verticalGradient(listOf(Black,Gray))),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "", modifier = Modifier.size(200.dp))
        Spacer(modifier = Modifier.height(48.dp))
        Text(text = "Registro de Colaboradores", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToSignUp()},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
        ) {
            Text(
                text = "Registrate Ahora",
                color = Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(Modifier.clickable {  },painter = painterResource(id = R.drawable.google), title = "Continuar con Google")
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Inicia Sesión",
            color = Color.White,
            modifier = Modifier.clickable { navigateToLogin() },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CustomButton(modifier: Modifier, painter: Painter, title:String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 32.dp)
            .border(2.dp, ShapeButton, CircleShape)
            .background(Gray, CircleShape)
        , contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painter, contentDescription = "",
            modifier = Modifier.padding(start = 16.dp).size(20.dp))
        Text(text = title, color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}