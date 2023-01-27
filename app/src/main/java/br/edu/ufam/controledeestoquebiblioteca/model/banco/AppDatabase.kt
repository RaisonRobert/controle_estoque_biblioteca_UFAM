package br.edu.ufam.controledeestoquebiblioteca.model.banco

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ufam.controledeestoquebiblioteca.model.entidade.DadosLivro

@Database(entities = [DadosLivro::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): DadosDao
    companion object {
        fun instancia(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "livro.db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration() // fallbackToDestructiveMigration destroi o banco anterior e constroi um novo e perde todos os dados anteriores
                .build()
        }
    }
}