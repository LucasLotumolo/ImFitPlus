package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

interface UsuarioDao {
    fun criarUsuario(usuario: DadosPessoais): Long
    fun buscarUsuarios(): MutableList<DadosPessoais>
}