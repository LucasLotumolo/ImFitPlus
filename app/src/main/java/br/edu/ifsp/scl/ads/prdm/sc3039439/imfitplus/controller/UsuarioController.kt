package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.controller

import android.content.Context
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.DadosPessoais
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.UsuarioDao
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.UsuarioSqlite

class UsuarioController(context: Context) {

    private val usuarioDao: UsuarioDao = UsuarioSqlite(context)

    fun inserirUsuario(usuario: DadosPessoais) =
        usuarioDao.criarUsuario(usuario)

    fun buscarUsuarios() =
        usuarioDao.buscarUsuarios()
}
