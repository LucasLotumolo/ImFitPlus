package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "calculos",
    foreignKeys = [
        ForeignKey(
            entity = DadosPessoais::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Calculo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val usuarioId: Long,
    val dataMillis: Long,
    val imc: Double,
    val categoria: String,
    val tmb: Double,
    val pesoIdeal: Double
)
