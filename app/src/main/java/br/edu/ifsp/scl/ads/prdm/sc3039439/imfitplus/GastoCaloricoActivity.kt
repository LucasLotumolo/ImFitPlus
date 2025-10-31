package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityGastoCaloricoBinding

class GastoCaloricoActivity : AppCompatActivity() {
    private val agcb: ActivityGastoCaloricoBinding by lazy {
        ActivityGastoCaloricoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(agcb.root)

        val sexo = intent.getStringExtra("sexo")
        val idade = intent.getIntExtra("idade", 0)
        val altura = intent.getDoubleExtra("altura", 0.0)
        val peso = intent.getDoubleExtra("peso", 0.0)
        val nivel = intent.getStringExtra("nivel")

        val tmb = if (sexo == "Masculino") {
            66 + (13.7 * peso) + (5 * altura * 100) - (6.8 * idade)
        } else {
            655 + (9.6 * peso) + (1.8 * altura * 100) - (4.7 * idade)
        }

        val fator = when (nivel) {
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
            intent.putExtras(getIntent())
            startActivity(intent)
        }

        agcb.voltarBt.setOnClickListener { finish() }
    }
}