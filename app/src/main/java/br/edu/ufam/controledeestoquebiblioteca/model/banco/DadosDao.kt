package br.edu.ufam.controledeestoquebiblioteca.model.banco;

import androidx.room.*
import br.edu.ufam.controledeestoquebiblioteca.model.entidade.DadosLivro

@Dao
interface DadosDao {
    //    Dados User
    @Query("SELECT * FROM DadosLivro WHERE id = :id")
    fun buscarAdmin(id: Long): DadosLivro?

    @Query("SELECT * FROM DadosLivro")
    fun buscarDados(): List<DadosLivro>

    @Insert
    fun salvaDados(vararg dadosUser: DadosLivro)

    @Delete
    fun delete(vararg dadosUser: DadosLivro)

    @Update
    fun alterarDados(dadosUser: DadosLivro)

    @Query("SELECT * FROM DadosLivro WHERE isbn = :isbn")
    fun buscarListaEsporte(isbn: String): List<DadosLivro>

}