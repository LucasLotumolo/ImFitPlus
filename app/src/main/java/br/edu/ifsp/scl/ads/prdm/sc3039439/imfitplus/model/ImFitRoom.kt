package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DadosPessoais::class, Calculo::class],
    version = 1
)
abstract class ImFitRoom : RoomDatabase() {
    abstract fun imFitDao(): ImFitDao
}
