package br.edu.ufam.controledeestoquebiblioteca.model.entidade

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DadosLivro(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nomeLivro: String,
    val ano:Int,
    val quantidade: Int,
    val autor: String,
    val editor: String,
    val isbn: String

)
