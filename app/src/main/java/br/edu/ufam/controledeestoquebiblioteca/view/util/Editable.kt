package br.edu.ufam.controledeestoquebiblioteca.view.util

import android.text.Editable

fun Editable.paraInt() : Int{
    return this.toString().toInt()
}