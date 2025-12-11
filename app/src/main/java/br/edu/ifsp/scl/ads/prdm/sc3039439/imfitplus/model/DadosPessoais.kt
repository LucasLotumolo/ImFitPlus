package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DadosPessoais(
    val id: Int? = -1,
    val nome: String,
    val idade: Int,
    val altura: Double,
    val peso: Double,
    val sexo: String,
    val nivel: String
) : Parcelable