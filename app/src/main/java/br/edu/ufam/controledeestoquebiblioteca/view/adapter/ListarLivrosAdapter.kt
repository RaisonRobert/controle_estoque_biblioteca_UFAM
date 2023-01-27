package br.edu.ufam.controledeestoquebiblioteca.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufam.controledeestoquebiblioteca.R
import br.edu.ufam.controledeestoquebiblioteca.model.entidade.DadosLivro

class ListarLivrosAdapter (private val onCopyClick:(codeCopy: DadosLivro)->Unit):
        RecyclerView.Adapter<ListarLivrosAdapter.LivrosViewHolder>() {
        private val list: MutableList<DadosLivro> = mutableListOf()

        inner class LivrosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(item: DadosLivro){
                item.let{code->
                    itemView.setOnClickListener{
                        onCopyClick(code)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivrosViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livro_card, parent, false)
            return LivrosViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: LivrosViewHolder, position: Int) {
            holder.bind(list[position])
        }

        @SuppressLint("NotifyDataSetChanged")
        fun insertItems(list: List<DadosLivro>) {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }