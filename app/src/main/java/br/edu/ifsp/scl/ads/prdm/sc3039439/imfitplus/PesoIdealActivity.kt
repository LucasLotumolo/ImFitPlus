package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus

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

        val altura = intent.getDoubleExtra("altura", 0.0)
        val peso = intent.getDoubleExtra("peso", 0.0)

        val pesoIdeal = 22 * (altura * altura)
        val diferenca = peso - pesoIdeal

        apib.pesoIdealTv.text = String.format("Peso Ideal: %.2f kg", pesoIdeal)
        apib.diferencaPesoTv.text = String.format("Diferença: %.2f kg", diferenca)

        val recomendacao = when {
            diferenca > 3 -> "Tente reduzir seu peso com exercícios e alimentação equilibrada."
            diferenca < -3 -> "Você pode ganhar um pouco de massa com treinos e boa alimentação."
            else -> "Parabéns! Seu peso está dentro do ideal."
        }

        apib.recomendacaoTv.text = recomendacao

        apib.voltarBt.setOnClickListener { finish() }
    }
}