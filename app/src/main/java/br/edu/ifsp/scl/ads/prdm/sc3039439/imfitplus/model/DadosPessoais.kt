package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("usuarios")
data class DadosPessoais(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nome: String,
    val idade: Int,
    val altura: Double,
    val peso: Double,
    val sexo: String,
    val nivel: String
) : Parcelable