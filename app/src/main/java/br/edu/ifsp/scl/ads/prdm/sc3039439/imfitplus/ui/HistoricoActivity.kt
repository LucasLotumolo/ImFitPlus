package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityHistoricoBinding

class HistoricoActivity : AppCompatActivity() {
    private val ahb: ActivityHistoricoBinding by lazy {
        ActivityHistoricoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(ahb.root)
    }
}