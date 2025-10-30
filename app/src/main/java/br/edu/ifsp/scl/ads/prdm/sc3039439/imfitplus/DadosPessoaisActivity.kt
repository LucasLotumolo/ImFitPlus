package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityDadosPessoaisBinding


class DadosPessoaisActivity : AppCompatActivity() {
    private val adpb: ActivityDadosPessoaisBinding by lazy {
        ActivityDadosPessoaisBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(adpb.root)

        adpb.calcularImcBt.setOnClickListener {
            val nome = adpb.nomeEt.text.toString()
            val idadeStr = adpb.idadeEt.text.toString()
            val alturaStr = adpb.alturaEt.text.toString()
            val pesoStr = adpb.pesoEt.text.toString()

            if (nome.isEmpty() || idadeStr.isEmpty() || alturaStr.isEmpty() || pesoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idade = idadeStr.toInt()
            val altura = alturaStr.toDouble()
            val peso = pesoStr.toDouble()

            val sexo = when (adpb.sexoRg.checkedRadioButtonId) {
                adpb.masculinoRb.id -> "Masculino"
                adpb.femininoRb.id -> "Feminino"
                else -> "Não informado"
            }

            val nivel = adpb.nivelSp.selectedItem.toString()

            val imc = peso / (altura * altura)

            val intent = Intent(this, ResultadoIMCActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("idade", idade)
            intent.putExtra("altura", altura)
            intent.putExtra("peso", peso)
            intent.putExtra("sexo", sexo)
            intent.putExtra("nivel", nivel)
            intent.putExtra("imc", imc)
            startActivity(intent)
        }
    }
}