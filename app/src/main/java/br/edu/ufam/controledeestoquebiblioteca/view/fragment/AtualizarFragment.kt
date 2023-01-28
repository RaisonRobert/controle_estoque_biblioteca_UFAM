package br.edu.ufam.controledeestoquebiblioteca.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ufam.controledeestoquebiblioteca.R
import br.edu.ufam.controledeestoquebiblioteca.model.banco.AppDatabase
import br.edu.ufam.controledeestoquebiblioteca.model.banco.DadosDao
import br.edu.ufam.controledeestoquebiblioteca.model.entidade.DadosLivro
import br.edu.ufam.controledeestoquebiblioteca.view.adapter.ListarLivrosAdapter
import br.edu.ufam.controledeestoquebiblioteca.view.util.Ui
import kotlinx.android.synthetic.main.header_simple.*
import kotlinx.android.synthetic.main.layout_atualizar_fragment.*
import kotlinx.android.synthetic.main.layout_header.*

class AtualizarFragment: Fragment() {
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
        return inflater.inflate(R.layout.layout_atualizar_fragment, container, false)
    }
    private lateinit var adapterLivro:ListarLivrosAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterLivro = ListarLivrosAdapter(::onClick)
        onConfigLayout()
        onConfigBtn()
        addVoucher()
        setupRecyclerView()
    }
    private fun addVoucher() {
            adapterLivro.insertItems(userDao.buscarDados())
    }

    private fun setupRecyclerView() {
        rv_livro.layoutManager = LinearLayoutManager(requireContext())
        rv_livro.adapter = adapterLivro
    }
    private fun onClick(dadosLivro: DadosLivro) {
        Ui.dados = dadosLivro
        findNavController().navigate(R.id.action_atualizar_to_atualizar_dados)
    }

    private fun onConfigBtn() {

    }

    private fun onConfigLayout() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow)
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        title_toolbar.text = getString(R.string.atualizar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.home, false)
        }
    }
}