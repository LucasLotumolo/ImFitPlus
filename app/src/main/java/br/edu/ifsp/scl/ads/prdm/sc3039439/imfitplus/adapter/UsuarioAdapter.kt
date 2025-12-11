package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.TileHistoricoBinding
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.DadosPessoais
import java.text.DecimalFormat

class UsuarioAdapter(
    context: Context,
    private val usuarioList: MutableList<DadosPessoais>
) : ArrayAdapter<DadosPessoais>(
    context,
    R.layout.tile_historico,
    usuarioList
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val usuario = usuarioList[position]
        var historicoTileView = convertView

        if (historicoTileView == null) {
            TileHistoricoBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).apply {
                historicoTileView = root

                val viewHolder = HistoricoViewHolder(
                    nomeTv,
                    sexoTv,
                    idadeTv,
                    alturaTv,
                    pesoTv,
                    nivelTv,
                    imcTv,
                    categoriaTv,
                    pesoIdealTv,
                    tmbTv,
                    recomendacaoTv
                )

                historicoTileView!!.tag = viewHolder
            }
        }

        val holder = historicoTileView!!.tag as HistoricoViewHolder
        val df = DecimalFormat("0.00")

        holder.nomeTv.text = "Nome: ${usuario.nome}"
        holder.sexoTv.text = "Sexo: ${usuario.sexo}"
        holder.idadeTv.text = "Idade: ${usuario.idade}"
        holder.alturaTv.text = "Altura: ${df.format(usuario.altura ?: 0.0)}"
        holder.pesoTv.text = "Peso: ${df.format(usuario.peso ?: 0.0)}"
        holder.nivelTv.text = "Nível: ${usuario.nivel}"
        holder.imcTv.text = "IMC: ${df.format(usuario.imc ?: 0.0)}"
        holder.categoriaTv.text = "Categoria: ${usuario.categoriaImc ?: "--"}"
        holder.pesoIdealTv.text = "Peso Ideal: ${df.format(usuario.pesoIdeal ?: 0.0)}"
        holder.tmbTv.text = "TMB/Gasto: ${df.format(usuario.tmb ?: 0.0)}"
        holder.recomendacaoTv.text = "Água: ${df.format(usuario.recomendacaoAgua ?: 0.0)} L"

        return historicoTileView!!
    }

    private data class HistoricoViewHolder(
        val nomeTv: TextView,
        val sexoTv: TextView,
        val idadeTv: TextView,
        val alturaTv: TextView,
        val pesoTv: TextView,
        val nivelTv: TextView,
        val imcTv: TextView,
        val categoriaTv: TextView,
        val pesoIdealTv: TextView,
        val tmbTv: TextView,
        val recomendacaoTv: TextView
    )
}
