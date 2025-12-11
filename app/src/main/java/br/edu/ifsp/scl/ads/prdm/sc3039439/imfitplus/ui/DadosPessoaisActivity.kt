package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.DadosPessoais
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityDadosPessoaisBinding

class DadosPessoaisActivity : AppCompatActivity() {
    private val adpb: ActivityDadosPessoaisBinding by lazy {
        ActivityDadosPessoaisBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(adpb.root)

        adpb.calcularImcBt.setOnClickListener {
            val dados = validarDadosPessoais() ?: return@setOnClickListener

            val peso = dados.peso!!
            val altura = dados.altura!!

            val imc = peso / (altura * altura)

            val intent = Intent(this, ResultadoIMCActivity::class.java).apply {
                putExtra("dados", dados)
                putExtra("imc", imc)
            }
            startActivity(intent)
        }
    }

    private fun validarDadosPessoais(): DadosPessoais? {
        val nome = adpb.nomeEt.text.toString().trim()
        val idadeStr = adpb.idadeEt.text.toString().trim()
        val alturaStr = adpb.alturaEt.text.toString().trim()
        val pesoStr = adpb.pesoEt.text.toString().trim()

        if (nome.isEmpty() || idadeStr.isEmpty() || alturaStr.isEmpty() || pesoStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return null
        }

        val idade = idadeStr.toIntOrNull()
        val altura = alturaStr.toDoubleOrNull()
        val peso = pesoStr.toDoubleOrNull()

        if (idade == null || altura == null || peso == null) {
            Toast.makeText(this, "Insira valores numéricos válidos!", Toast.LENGTH_SHORT).show()
            return null
        }

        if (idade <= 0 || idade > 120 || altura <= 0.5 || altura > 2.5 || peso <= 0 || peso > 400) {
            Toast.makeText(this, "Verifique os valores inseridos!", Toast.LENGTH_SHORT).show()
            return null
        }

        if (adpb.sexoRg.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Selecione o sexo!", Toast.LENGTH_SHORT).show()
            return null
        }

        val sexo = when (adpb.sexoRg.checkedRadioButtonId) {
            adpb.masculinoRb.id -> "Masculino"
            adpb.femininoRb.id -> "Feminino"
            else -> "Não informado"
        }

        val nivel = adpb.nivelSp.selectedItem.toString()
        if (nivel == "Selecione" || nivel.isBlank()) {
            Toast.makeText(this, "Selecione o nível de atividade!", Toast.LENGTH_SHORT).show()
            return null
        }

        return DadosPessoais(
            id = null,
            nome = nome,
            idade = idade,
            altura = altura,
            peso = peso,
            sexo = sexo,
            nivel = nivel
        )
    }
}
