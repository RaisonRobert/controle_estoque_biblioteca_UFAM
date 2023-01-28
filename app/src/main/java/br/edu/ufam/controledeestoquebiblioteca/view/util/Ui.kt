package br.edu.ufam.controledeestoquebiblioteca.view.util

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import br.edu.ufam.controledeestoquebiblioteca.R
import br.edu.ufam.controledeestoquebiblioteca.model.entidade.DadosLivro
import kotlinx.android.synthetic.main.dialog_modal.view.*

object Ui {
    lateinit var dados: DadosLivro
    /**
     *
     * Sobrecarga do método que cria modal padrão do load
     * @param context Contexto
     * @param show boolean
     * @return AlertDialog
     *
     * */
    fun createLoadDialog(context: Context, show: Boolean): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.dialog_load)
        builder.setCancelable(false)
        val modal = builder.create()
        modal.setCanceledOnTouchOutside(false)
        if (show) modal.show()
        return modal
    }
    /**
     *
     * Sobrecarga do método que cria modal padrão do aplicativo recebendo uma ação
     * @param context Contexto
     * @param icon Ícone
     * @param title Título do modal
     * @param subtitle Subtítulo do modal
     * @param description Descrição do modal
     * @return AlertDialog
     *
     * */
    fun createModal(
        context: Context,
        icon: Int,
        title: String,
        subtitle: String,
        description: String?
    ): AlertDialog? {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_modal, null)
        mDialogView.iconDialog.setImageResource(icon)
        mDialogView.titleDialog.text = title
        mDialogView.subtitleDialog.text = subtitle
        mDialogView.descriptionDialog.text = description

        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.buttonOK.setOnClickListener{
            mAlertDialog.dismiss()
        }
        return mAlertDialog
    }
}