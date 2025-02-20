package com.example.pcdapp.presentation.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pcdapp.R
import com.example.pcdapp.ui.theme.Black
import com.google.firebase.auth.FirebaseAuth
import android.util.Log


val UnselectedField = Color.Gray
val SelectedField = Color.Gray


@Composable
fun LoginScreen(auth: FirebaseAuth, navigateToHome: () -> Unit) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black)
                .padding(horizontal = 32.dp),

            ) {

            Spacer(Modifier.height(100.dp))

            Icon(painter = painterResource(id = R.drawable.ic_back_24), contentDescription = "", tint = White)

            Text(
                "Inicia Sesión ",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.align(CenterHorizontally).size(200.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text("Correo Electrónico", color = White, fontWeight = FontWeight.Bold, fontSize = 28.sp,modifier = Modifier.align(CenterHorizontally))
            TextField(value = email, onValueChange = {email = it }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(58.dp))
            Text("Contraseña", color = White, fontWeight = FontWeight.Bold, fontSize = 28.sp,modifier = Modifier.align(CenterHorizontally))
            TextField(value = password, onValueChange = {password = it }, modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
               focusedContainerColor = SelectedField
            )
            )
            Spacer(Modifier.height(28.dp))

            Button(onClick = {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    navigateToHome()
                    Log.i("Inicio de Sesión","Correcto")
                }else{
                    Log.i("Inicio de Sesión", "Incorrecto")
                }
            }
        }){
            Text("Iniciar Sesión", color = Black, fontWeight = FontWeight.Bold, fontSize = 28.sp)
        }
    }
}
