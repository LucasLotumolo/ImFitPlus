package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.DadosPessoais
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityResultadoImcBinding

class ResultadoIMCActivity : AppCompatActivity() {
    private val arib: ActivityResultadoImcBinding by lazy {
        ActivityResultadoImcBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(arib.root)

        val dados = intent.getParcelableExtra<DadosPessoais>("dados")
        val imc = intent.getDoubleExtra("imc", 0.0)

        if (dados != null) {
            arib.nomeResultadoTv.text = "Nome: ${dados.nome}"
            arib.imcResultadoTv.text = String.format("IMC: %.2f", imc)

            val categoria = when {
                imc < 18.5 -> "Abaixo do peso"
                imc < 25 -> "Normal"
                imc < 30 -> "Sobrepeso"
                else -> "Obesidade"
            }
            arib.categoriaTv.text = "Categoria: $categoria"

            arib.calcularGastoBt.setOnClickListener {
                val intent = Intent(this, GastoCaloricoActivity::class.java).apply {
                    putExtra("dados", dados)
                    putExtra("imc", imc)
                    putExtra("categoriaImc", categoria)
                }
                startActivity(intent)
            }
        }



        arib.voltarBt.setOnClickListener {
            finish()
        }
    }
}
