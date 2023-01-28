package br.edu.ufam.controledeestoquebiblioteca.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufam.controledeestoquebiblioteca.R
import br.edu.ufam.controledeestoquebiblioteca.model.banco.AppDatabase
import br.edu.ufam.controledeestoquebiblioteca.model.banco.DadosDao
import br.edu.ufam.controledeestoquebiblioteca.view.adapter.MenuSquareItemAdapter
import br.edu.ufam.controledeestoquebiblioteca.view.util.MenuSquareItem
import kotlinx.android.synthetic.main.header_simple.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_home_fragment.*

class HomeFragment : Fragment() {
    private lateinit var userDao: DadosDao
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: GridLayoutManager
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
        return inflater.inflate(R.layout.layout_home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        setHeader()
    }

    /**
     * Método criado para setar os itens da Header
     * @param view View
     */
    private fun setHeader() {
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        title_toolbar.text = getString(R.string.app_name)
    }

    private fun setupMenu() {
        val menuItens = ArrayList<MenuSquareItem>()
        menuItens.add(MenuSquareItem(getString(R.string.registrar), R.drawable.ic_registrar))
        menuItens.add(MenuSquareItem(getString(R.string.atualizar), R.drawable.ic_atualizar))
        menuItens.add(MenuSquareItem(getString(R.string.pesquisar), R.drawable.ic_pesquisar))
        menuItens.add(MenuSquareItem(getString(R.string.remover), R.drawable.ic_remover))
        viewManager = GridLayoutManager(context, 2)
        viewAdapter = MenuSquareItemAdapter(menuItens, this.menuSquareItemClickListener())
        recyclerView = requireActivity().findViewById<RecyclerView>(R.id.rv_menu).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    /**
     * Método criado para definir os itens do Menu
     * @return OnItemClickListener
     */
    private fun menuSquareItemClickListener(): MenuSquareItemAdapter.OnItemClickListener = object :
        MenuSquareItemAdapter.OnItemClickListener {

        /**
         * Método criado para as ações dos Itens do menu
         * @param item MenuSquareItem
         */
        override fun onItemClick(item: MenuSquareItem) {
            when (item.drawable) {
                R.drawable.ic_registrar -> {
                    findNavController().navigate(R.id.action_home_to_registrar)
                }
                R.drawable.ic_atualizar -> {
                    findNavController().navigate(R.id.action_home_to_atualizar)
                }
                R.drawable.ic_pesquisar -> {
                    Toast.makeText(requireContext(), "Cliquei em Pesquisar", Toast.LENGTH_SHORT)
                        .show()
                }
                R.drawable.ic_remover -> {
                    Toast.makeText(requireContext(), "Cliquei em Remover", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}