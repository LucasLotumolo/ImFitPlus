package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DadosPessoais(
    val id: Int? = null,
    val nome: String = "",
    val idade: Int? = null,
    val sexo: String = "",
    val altura: Double? = null,
    val peso: Double? = null,
    val nivel: String = "",
    var imc: Double? = null,
    var tmb: Double? = null,
    var gastoCalorico: Double? = null,
    var pesoIdeal: Double? = null,
    var categoriaImc: String? = null,
    var recomendacaoAgua: Double? = null
) : Parcelable
