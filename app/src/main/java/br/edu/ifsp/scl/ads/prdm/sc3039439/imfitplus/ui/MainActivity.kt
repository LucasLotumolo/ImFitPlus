package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        setSupportActionBar(amb.toolbarIn.toolbar)


        amb.comeceAquiBt.setOnClickListener {
            val intent = Intent(this, DadosPessoaisActivity::class.java)
            startActivity(intent)
        }
    }
}