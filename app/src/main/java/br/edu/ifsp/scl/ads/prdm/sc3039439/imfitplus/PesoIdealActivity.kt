package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.databinding.ActivityPesoIdealBinding

class PesoIdealActivity : AppCompatActivity() {
    private val apib: ActivityPesoIdealBinding by lazy {
        ActivityPesoIdealBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apib.root)

        val dados = intent.getParcelableExtra<DadosPessoais>("dados")
        val imc = intent.getDoubleExtra("imc", 0.0)
        val gasto = intent.getDoubleExtra("gasto", 0.0)

        if (dados != null) {
            val pesoIdeal = 22 * (dados.altura * dados.altura)
            val diferenca = dados.peso - pesoIdeal

            apib.pesoIdealTv.text = String.format("Peso Ideal: %.2f kg", pesoIdeal)
            apib.diferencaPesoTv.text = String.format("Diferença: %.2f kg", diferenca)

            val recomendacao = when {
                diferenca > 3 -> "Tente reduzir seu peso com exercícios e alimentação equilibrada."
                diferenca < -3 -> "Você pode ganhar um pouco de massa com treinos e boa alimentação."
                else -> "Parabéns! Seu peso está dentro do ideal."
            }

            apib.recomendacaoTv.text = recomendacao

            apib.calcularRecomendacaoAguaBt.setOnClickListener {
                val intent = Intent(this, ResumoSaudeActivity::class.java)
                intent.putExtra("dados", dados)
                intent.putExtra("pesoIdeal", pesoIdeal)
                intent.putExtra("imc", imc)
                intent.putExtra("gasto",gasto)
                startActivity(intent)
            }
        }
        apib.voltarBt.setOnClickListener { finish() }
    }
}
