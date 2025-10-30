package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityResultadoImcBinding


class ResultadoIMCActivity : AppCompatActivity() {
    private val arib: ActivityResultadoImcBinding by lazy {
        ActivityResultadoImcBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(arib.root)
    }
}