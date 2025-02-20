package com.example.pcdapp.presentation.model

data class Colaborador(
    val nombres: String,
    val apellidos: String,
    val edad: Int,
    val telefono: Int,
    val correoElectronico: String,
    val empresas: List<Empresa>
)
