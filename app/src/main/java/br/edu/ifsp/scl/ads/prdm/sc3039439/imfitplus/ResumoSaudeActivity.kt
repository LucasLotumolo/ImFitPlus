package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityResumoSaudeBinding

class ResumoSaudeActivity : AppCompatActivity() {
    private val arsb: ActivityResumoSaudeBinding by lazy {
        ActivityResumoSaudeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(arsb.root)

        val dados = intent.getParcelableExtra<DadosPessoais>("dados")
        val imc = intent.getDoubleExtra("imc", 0.0)
        val pesoIdeal = intent.getDoubleExtra("pesoIdeal", 0.0)
        val gasto = intent.getDoubleExtra("gasto", 0.0)

        if (dados != null) {
            arsb.nomeResumoTv.text = "Nome: ${dados.nome}"
            arsb.imcResumoTv.text = String.format("IMC: %.2f", imc)

            val categoria = when {
                imc < 18.5 -> "Abaixo do peso"
                imc < 25 -> "Normal"
                imc < 30 -> "Sobrepeso"
                else -> "Obesidade"
            }

            arsb.categoriaResumoTv.text = "Categoria: $categoria"
            arsb.pesoIdealResumoTv.text = String.format("Peso Ideal: %.2f kg", pesoIdeal)
            arsb.gastoCaloricoResumoTv.text = String.format("Gasto Diário: %.2f kcal", gasto)

            val recomendacaoAgua = dados.peso * 350

            arsb.recomendacaoAguaResumoTv.text = String.format("Recomendação de Água: %.1f litros", recomendacaoAgua/1000)
        }

        arsb.voltarBt.setOnClickListener { finish() }
    }
}