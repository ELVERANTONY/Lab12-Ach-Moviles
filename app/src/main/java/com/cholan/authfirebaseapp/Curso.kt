package com.cholan.authfirebaseapp

data class Curso(
    val id: String = "",
    val nombre: String = "",
    val codigo: String = "",
    val creditos: Int = 0,
    val userId: String = "",
    val fechaCreacion: Long = System.currentTimeMillis()
)
