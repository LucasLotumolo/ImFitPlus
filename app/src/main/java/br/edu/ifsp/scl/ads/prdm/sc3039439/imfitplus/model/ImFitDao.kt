package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

import androidx.lifecycle.LiveData
import androidx.room.*
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.Calculo
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.DadosPessoais

@Dao
interface ImFitDao {

    // ------------------- Usuários ------------------- //

    @Insert
    fun inserirUsuario(usuario: DadosPessoais): Long

    @Update
    fun atualizarUsuario(usuario: DadosPessoais)

    @Query("SELECT * FROM usuarios WHERE id = :id")
    fun buscarUsuario(id: Long): DadosPessoais?

    @Query("SELECT * FROM usuarios")
    fun listarUsuarios(): MutableList<DadosPessoais>

    // ------------------- Cálculos ------------------- //

    @Insert
    fun inserirCalculo(calculo: Calculo): Long

    @Query("SELECT * FROM calculos WHERE usuarioId = :id ORDER BY dataMillis DESC")
    fun historicoPorUsuario(id: Long): LiveData<List<Calculo>>

    @Query("DELETE FROM calculos WHERE usuarioId = :id")
    fun limparHistorico(id: Long)
}
