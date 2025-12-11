package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.DadosPessoais
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityGastoCaloricoBinding

class GastoCaloricoActivity : AppCompatActivity() {
    private val agcb: ActivityGastoCaloricoBinding by lazy {
        ActivityGastoCaloricoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(agcb.root)

        val dados = intent.getParcelableExtra<DadosPessoais>("dados")
        val imc = intent.getDoubleExtra("imc", 0.0)
        val categoria = intent.getStringExtra("categoriaImc")

        if (dados == null) {
            finish()
            return
        }

        val tmb = if (dados.sexo == "Masculino") {
            66 + (13.7 * dados.peso!!) +
                    (5 * dados.altura!! * 100) -
                    (6.8 * dados.idade!!)
        } else {
            655 + (9.6 * dados.peso!!) +
                    (1.8 * dados.altura!! * 100) -
                    (4.7 * dados.idade!!)
        }

        val fator = when (dados.nivel) {
            "Sedentário" -> 1.2
            "Leve" -> 1.375
            "Moderado" -> 1.55
            "Intenso" -> 1.725
            else -> 1.2
        }

        val gasto = tmb * fator

        agcb.resultadoTmbTv.text = String.format("TMB: %.2f", tmb)
        agcb.gastoDiarioTv.text = String.format("Gasto Diário: %.2f kcal", gasto)

        agcb.pesoIdealBt.setOnClickListener {
            val intent = Intent(this, PesoIdealActivity::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("gasto", gasto)
            intent.putExtra("tmb", tmb)
            intent.putExtra("imc", imc)
            intent.putExtra("categoriaImc", categoria)
            startActivity(intent)
        }

        agcb.voltarBt.setOnClickListener { finish() }
    }
}

