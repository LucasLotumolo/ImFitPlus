package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DadosPessoais(
    val nome: String,
    val idade: Int,
    val altura: Double,
    val peso: Double,
    val sexo: String,
    val nivel: String
) : Parcelable
