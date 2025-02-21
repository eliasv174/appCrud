/*package com.example.pcdapp.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pcdapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.example.pcdapp.presentation.model.Colaborador
import com.example.pcdapp.presentation.model.Empresa

@Composable
fun HomeScreen(db: FirebaseFirestore) {
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf(0) }
    var telefono by remember { mutableStateOf(0) }
    var correoElectronico by remember { mutableStateOf("") }
    var selectedEmpresas by remember { mutableStateOf<List<Empresa>>(emptyList()) }
    var empresasList by remember { mutableStateOf<List<Empresa>>(emptyList()) }

    // Obtener la lista de empresas desde Firestore
    LaunchedEffect(true) {
        getEmpresasFromFirestore(db) { empresas ->
            empresasList = empresas
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Campos del formulario
        Text("Nombres")
        TextField(
            value = nombres,
            onValueChange = { nombres = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Apellidos")
        TextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Edad")
        TextField(
            value = edad.toString(),
            onValueChange = { edad = it.toIntOrNull() ?: 0 },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Teléfono")
        TextField(
            value = telefono.toString(),
            onValueChange = { telefono = it.toIntOrNull() ?: 0 },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Correo Electrónico")
        TextField(
            value = correoElectronico,
            onValueChange = { correoElectronico = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Seleccionar Empresas")

        // Lista de empresas con checkboxes
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(empresasList) { empresa ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = selectedEmpresas.contains(empresa),
                        onCheckedChange = { isChecked ->
                            selectedEmpresas = if (isChecked) {
                                selectedEmpresas + empresa
                            } else {
                                selectedEmpresas - empresa
                            }
                        }
                    )
                    Text(empresa.razonSocial)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Crear el colaborador con las empresas seleccionadas
            val colaborador = Colaborador(
                nombres = nombres,
                apellidos = apellidos,
                edad = edad,
                telefono = telefono,
                correoElectronico = correoElectronico,
                empresas = selectedEmpresas
            )

            // Convertir colaborador a un mapa para agregar a Firestore
            val colaboradorMap = hashMapOf(
                "nombres" to colaborador.nombres,
                "apellidos" to colaborador.apellidos,
                "edad" to colaborador.edad,
                "telefono" to colaborador.telefono,
                "correoElectronico" to colaborador.correoElectronico,
                "empresas" to colaborador.empresas.map { empresa ->
                    mapOf("razonSocial" to empresa.razonSocial)
                }
            )

            // Agregar el colaborador a Firestore
            db.collection("colaboradores")
                .add(colaboradorMap)
                .addOnSuccessListener {
                    Log.i("Firebase", "Colaborador creado con éxito")
                }
                .addOnFailureListener {
                    Log.i("Firebase", "Error al crear colaborador")
                }
        }) {
            Text("Guardar Colaborador")
        }
    }
}

// Función para obtener las empresas desde Firestore
fun getEmpresasFromFirestore(db: FirebaseFirestore, onResult: (List<Empresa>) -> Unit) {
    db.collection("empresas")
        .get()
        .addOnSuccessListener { result ->
            val empresas = result.map { document ->
                val razonSocial = document.getString("razonSocial") ?: ""
                Empresa(razonSocial = razonSocial)
            }
            onResult(empresas)
        }
        .addOnFailureListener {
            Log.i("Firebase", "Error al obtener empresas")
            onResult(emptyList())
        }
}*/
package com.example.pcdapp.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pcdapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.example.pcdapp.presentation.model.Colaborador
import com.example.pcdapp.presentation.model.Empresa
import androidx.compose.foundation.layout.Box
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext



@Composable
fun HomeScreen(db: FirebaseFirestore,
               navigateBack: () -> Unit) {
    val context = LocalContext.current
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf(0) }
    var telefono by remember { mutableStateOf(0) }
    var correoElectronico by remember { mutableStateOf("") }
    var selectedEmpresas by remember { mutableStateOf<List<Empresa>>(emptyList()) }
    var empresasList by remember { mutableStateOf<List<Empresa>>(emptyList()) }

    // Obtener la lista de empresas desde Firestore
    LaunchedEffect(true) {
        getEmpresasFromFirestore(db) { empresas ->
            empresasList = empresas
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(modifier = Modifier.height(68.dp))
        IconButton(onClick = { navigateBack() }) {

            Icon(
                painter = painterResource(id = R.drawable.ic_back_24),
                contentDescription = "Volver",
                tint = White
            )
        }
        // Campos del formulario
        Text("Nombres", color = White, fontWeight = FontWeight.Bold, fontSize = 18.sp,modifier = Modifier.align(CenterHorizontally))
        TextField(
            value = nombres,
            onValueChange = { nombres = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Apellidos"
            , color = White
            , fontWeight = FontWeight.Bold
            , fontSize = 18.sp
            , modifier = Modifier.align(
            CenterHorizontally
        )
        )
        TextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Edad", color = White, fontWeight = FontWeight.Bold, fontSize = 18.sp,modifier = Modifier.align(CenterHorizontally)
        )
        TextField(
            value = edad.toString(),
            onValueChange = { edad = it.toIntOrNull() ?: 0 },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Teléfono", color = White, fontWeight = FontWeight.Bold, fontSize = 18.sp,modifier = Modifier.align(CenterHorizontally))
        TextField(
            value = telefono.toString(),
            onValueChange = { telefono = it.toIntOrNull() ?: 0 },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Correo Electrónico", color = White, fontWeight = FontWeight.Bold, fontSize = 18.sp,modifier = Modifier.align(CenterHorizontally)
        )
        TextField(
            value = correoElectronico,
            onValueChange = { correoElectronico = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Seleccionar Empresas", color = White, fontWeight = FontWeight.Bold, fontSize = 18.sp,modifier = Modifier.align(CenterHorizontally))

        // Lista de empresas con checkboxes
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(empresasList) { empresa ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = selectedEmpresas.contains(empresa),
                        onCheckedChange = { isChecked ->
                            selectedEmpresas = if (isChecked) {
                                selectedEmpresas + empresa
                            } else {
                                selectedEmpresas - empresa
                            }
                        }
                    )
                    Text(empresa.razonSocial)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                val colaborador = Colaborador(
                    nombres = nombres,
                    apellidos = apellidos,
                    edad = edad,
                    telefono = telefono,
                    correoElectronico = correoElectronico,
                    empresas = selectedEmpresas
                )

                val colaboradorMap = hashMapOf(
                    "nombres" to colaborador.nombres,
                    "apellidos" to colaborador.apellidos,
                    "edad" to colaborador.edad,
                    "telefono" to colaborador.telefono,
                    "correoElectronico" to colaborador.correoElectronico,
                    "empresa" to colaborador.empresas.map { empresa ->
                        mapOf("razonSocial" to empresa.razonSocial)
                    }
                )

                db.collection("colaboradores")
                    .add(colaboradorMap)
                    .addOnSuccessListener {
                        Log.i("Firebase", "Colaborador creado con éxito")
                        Toast.makeText(context, "Colaborador Guardado exitosamente", Toast.LENGTH_LONG).show()
                        nombres = ""
                        apellidos = ""
                        edad = 0
                        telefono = 0
                        correoElectronico = ""
                        selectedEmpresas = emptyList()
                    }
                    .addOnFailureListener {
                        Log.i("Firebase", "Error al crear colaborador")
                        Toast.makeText(context, "Error al guardar colaborador", Toast.LENGTH_LONG).show()

                    }
            }) {
                Text("Guardar Colaborador")
            }
        }

       /* Button(onClick = {

            val colaborador = Colaborador(
                nombres = nombres,
                apellidos = apellidos,
                edad = edad,
                telefono = telefono,
                correoElectronico = correoElectronico,
                empresas = selectedEmpresas // Asegúrate de que esto sea una lista de objetos de tipo Empresa
            )

            // Convertir el colaborador a un mapa para agregarlo a Firestore
            val colaboradorMap = hashMapOf(
                "nombres" to colaborador.nombres,
                "apellidos" to colaborador.apellidos,
                "edad" to colaborador.edad,
                "telefono" to colaborador.telefono,
                "correoElectronico" to colaborador.correoElectronico,
                "empresas" to colaborador.empresas.map { empresa ->
                    mapOf("razonSocial" to empresa.razonSocial)  // Aquí solo se agrega la razón social
                }
            )

            // Agregar el colaborador a Firestore
          /*  db.collection("colaboradores")
                .add(colaboradorMap)
                .addOnSuccessListener {
                    Log.i("Firebase", "Colaborador creado con éxito")
                }
                .addOnFailureListener {
                    Log.i("Firebase", "Error al crear colaborador")
                }*/
        }) {
            Text("Guardar Colaborador")
        }

    }*/
    }
}

// Función para obtener las empresas desde Firestore
fun getEmpresasFromFirestore(db: FirebaseFirestore, onResult: (List<Empresa>) -> Unit) {
    db.collection("empresas")
        .get()
        .addOnSuccessListener { result ->
            val empresas = result.map { document ->
                val razonSocial = document.getString("razonSocial") ?: ""
                Empresa(razonSocial = razonSocial)
            }
            onResult(empresas)
        }
        .addOnFailureListener {
            Log.i("Firebase", "Error al obtener empresas")
            onResult(emptyList())
        }
}
