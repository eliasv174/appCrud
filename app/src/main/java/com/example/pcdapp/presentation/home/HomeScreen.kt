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
}
