package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model.DadosPessoais
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityResumoSaudeBinding

class ResumoSaudeActivity : AppCompatActivity() {
    private val arsb: ActivityResumoSaudeBinding by lazy {
        ActivityResumoSaudeBinding.inflate(layoutInflater)
    }

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(arsb.root)

        val dados = intent.getParcelableExtra<DadosPessoais>("dados")
        val imc = intent.getDoubleExtra("imc", 0.0)
        val pesoIdeal = intent.getDoubleExtra("pesoIdeal", 0.0)
        val gasto = intent.getDoubleExtra("gasto", 0.0)
        val tmb = intent.getDoubleExtra("tmb", 0.0)
        val categoria = intent.getStringExtra("categoriaImc")
        val dataNascimento = intent.getStringExtra("dataNascimento")

        if (dados != null) {
            arsb.nomeResumoTv.text = "Nome: ${dados.nome}"
            arsb.imcResumoTv.text = String.format("IMC: %.2f", imc)

            arsb.categoriaResumoTv.text = "Categoria: $categoria"
            arsb.pesoIdealResumoTv.text = String.format("Peso Ideal: %.2f kg", pesoIdeal)
            arsb.gastoCaloricoResumoTv.text = String.format("Gasto Diário: %.2f kcal", gasto)

            val peso = dados.peso ?: 0.0
            val recomendacaoAgua = peso * 35 / 1000

            arsb.recomendacaoAguaResumoTv.text = String.format("Recomendação de Água: %.1f litros", recomendacaoAgua)

            val idade = dados.idade ?: 0
            val frequenciaMax = 220 - idade

            val zonaLivre = frequenciaMax * 0.60
            val zonaQueima = frequenciaMax * 0.70
            val zonaAerobica = frequenciaMax * 0.80

            val frequenciaAtual = 130

            val zonaTreino = when {
                frequenciaAtual < zonaLivre -> "Zona Livre"
                frequenciaAtual < zonaQueima -> "Zona de Queima de Gordura"
                frequenciaAtual < zonaAerobica -> "Zona Aeróbica"
                else -> "Zona Anaeróbica"
            }

            arsb.frequenciaTv.text = String.format("Frequência Cardíaca Máxima: %d bpm", frequenciaMax)
            arsb.zonaTreinoTv.text = String.format("Zona de Treino: %s", zonaTreino)

            arsb.historicoBt.setOnClickListener {
                if (dados == null) return@setOnClickListener

                dados.imc = imc
                dados.pesoIdeal = pesoIdeal
                dados.gastoCalorico = gasto
                dados.recomendacaoAgua = recomendacaoAgua
                dados.categoriaImc = categoria
                dados.tmb = tmb
                dados.dataNascimento = dataNascimento.toString()

                val controller = br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.controller.UsuarioController(this)
                // Está quebrando ao ir para a tela de Histórico e inserir usuário. Acredito que por conta da dataNascimento
                val insertedId = controller.inserirUsuario(dados)

                android.util.Log.d("ResumoSaudeActivity", "Usuário inserido com id: $insertedId")
                val intent = Intent(this, HistoricoActivity::class.java)
                startActivity(intent)
            }

        }

        arsb.voltarBt.setOnClickListener { finish() }
    }
}