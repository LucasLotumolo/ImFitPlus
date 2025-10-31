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

        val nome = intent.getStringExtra("nome")
        val imc = intent.getDoubleExtra("imc", 0.0)

        arib.nomeResultadoTv.text = String.format("Nome: %s", nome)
        arib.imcResultadoTv.text = String.format("IMC: %.2f", imc)

        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }
        arib.categoriaTv.text = String.format("Categoria: %s", categoria)

        arib.calcularGastoBt.setOnClickListener {
            val intent = Intent(this, GastoCaloricoActivity::class.java)
            intent.putExtras(getIntent())
            startActivity(intent)
        }

        arib.voltarBt.setOnClickListener { finish() }
    }
}