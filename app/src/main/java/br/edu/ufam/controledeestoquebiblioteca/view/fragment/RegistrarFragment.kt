package br.edu.ufam.controledeestoquebiblioteca.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.edu.ufam.controledeestoquebiblioteca.R
import br.edu.ufam.controledeestoquebiblioteca.model.banco.AppDatabase
import br.edu.ufam.controledeestoquebiblioteca.model.banco.DadosDao
import br.edu.ufam.controledeestoquebiblioteca.model.entidade.DadosLivro
import br.edu.ufam.controledeestoquebiblioteca.view.util.Ui
import br.edu.ufam.controledeestoquebiblioteca.view.util.paraInt
import kotlinx.android.synthetic.main.header_simple.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_registrar_fragment.*

class RegistrarFragment : Fragment() {
    private lateinit var userDao: DadosDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.instancia(requireContext())
        userDao = db.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_registrar_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onConfigLayout()
        onConfigBtn()
    }

    private fun onConfigBtn() {
        btn_salvar.setOnClickListener {
            if (verificaCampos()) {
                userDao.salvaDados(
                    DadosLivro(
                        nomeLivro = nome_field_livro.text.toString(),
                        ano = ano_field_livro.text!!.paraInt(),
                        quantidade = qtd_field_livro.text!!.paraInt(),
                        editor = editora_field_livro.text.toString(),
                        autor = autores_field_livro.text.toString(),
                        isbn = isbn_field_livro.text.toString()
                    )
                )
                findNavController().popBackStack()
                Ui.createModal(
                    requireContext(),
                    R.drawable.ic_sorriso,
                    getString(R.string.registrar),
                    "Com Sucesso",
                    ""
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_campo_vazio),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun verificaCampos(): Boolean {
        return !nome_field_livro.text.isNullOrEmpty() &&
                !ano_field_livro.text.isNullOrEmpty() &&
                !qtd_field_livro.text.isNullOrEmpty() &&
                !editora_field_livro.text.isNullOrEmpty() &&
                !autores_field_livro.text.isNullOrEmpty() &&
                !isbn_field_livro.text.isNullOrEmpty()
    }

    private fun onConfigLayout() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow)
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        title_toolbar.text = getString(R.string.registrar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.home, false)
        }
    }
}