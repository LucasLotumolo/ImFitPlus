package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.adapter.UsuarioAdapter
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityHistoricoBinding
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.UsuarioSqlite

class HistoricoActivity : AppCompatActivity() {
    private val ahb: ActivityHistoricoBinding by lazy {
        ActivityHistoricoBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: UsuarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ahb.root)

        adapter = UsuarioAdapter(this, mutableListOf())
        ahb.historicoLv.adapter = adapter

        ahb.voltarBt.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        val dao = UsuarioSqlite(this)
        val usuarios = dao.buscarUsuarios()
        adapter.clear()
        adapter.addAll(usuarios)
        adapter.notifyDataSetChanged()
    }
}

