package com.example.pcdapp.presentation.signup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pcdapp.R
import com.example.pcdapp.presentation.login.SelectedField
import com.example.pcdapp.presentation.login.UnselectedField
import com.example.pcdapp.ui.theme.Black
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpScreen(auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    fun showSuccessDialog() {
        showDialog = true
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Tus datos están correctos") },
            text = { Text("¿Deseas continuar?") },
            confirmButton = {
                Button(onClick = {
                    email = ""
                    password = ""
                    showDialog = false
                    //navController.popBackStack()
                    Log.i("Login", "Usuario aceptó")
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    Log.i("Login", "Usuario canceló")
                }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 32.dp),

        ) {

        Spacer(Modifier.height(100.dp))

        Icon(painter = painterResource(id = R.drawable.ic_back_24), contentDescription = "", tint = White)

        Text(
            "Registrar",
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
        Text("Correo Electrónico", color = White, fontWeight = FontWeight.Bold, fontSize = 28.sp,modifier = Modifier.align(
            CenterHorizontally
        ))
        TextField(value = email, onValueChange = {email = it }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(58.dp))
        Text("Contraseña", color = White, fontWeight = FontWeight.Bold, fontSize = 28.sp,modifier = Modifier.align(
            CenterHorizontally
        ))
        TextField(value = password, onValueChange = {password = it }, modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(28.dp))

        Button(onClick = {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    showSuccessDialog()
                }else{
                    Log.i("Registro de Usuario", "Incorrecto")
                }
            }
        }){
            Text("Registrar", color = Black, fontWeight = FontWeight.Bold, fontSize = 28.sp)
        }
    }

}