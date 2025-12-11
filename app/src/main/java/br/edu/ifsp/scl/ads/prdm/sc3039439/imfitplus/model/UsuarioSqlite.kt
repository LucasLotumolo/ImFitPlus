package br.edu.ifsp.scl.ads.prdm.sc3039439.imfitplus.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class UsuarioSqlite(context: Context) : UsuarioDao {

    companion object {
        private const val DATABASE_FILE = "usuarioDatabase"
        private const val TABLE = "usuario"
        private const val ID = "id"
        private const val NOME = "nome"
        private const val IDADE = "idade"
        private const val SEXO = "sexo"
        private const val ALTURA = "altura"
        private const val PESO = "peso"
        private const val NIVEL = "nivel"
        private const val IMC = "imc"
        private const val TMB = "tmb"
        private const val GASTO = "gastoCalorico"
        private const val PESO_IDEAL = "pesoIdeal"
        private const val CATEGORIA_IMC = "categoriaImc"
        private const val AGUA = "recomendacaoAgua"

        const val CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS $TABLE (
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $NOME TEXT NOT NULL,
                $IDADE INTEGER,
                $SEXO TEXT NOT NULL,
                $ALTURA REAL,
                $PESO REAL,
                $NIVEL TEXT,
                $IMC REAL,
                $TMB REAL,
                $GASTO REAL,
                $PESO_IDEAL REAL,
                $CATEGORIA_IMC TEXT,
                $AGUA REAL
            );
        """
    }

    private val database: SQLiteDatabase = context.openOrCreateDatabase(
        DATABASE_FILE,
        MODE_PRIVATE,
        null
    )

    init {
        try {
            database.execSQL(CREATE_TABLE)
        } catch (e: Exception) {
            Log.e("UsuarioSqlite", e.toString())
        }
    }

    override fun criarUsuario(usuario: DadosPessoais): Long {
        return database.insert(TABLE, null, usuario.toContentValues())
    }

    override fun buscarUsuarios(): MutableList<DadosPessoais> {
        val lista = mutableListOf<DadosPessoais>()

        val cursor = database.rawQuery(
            "SELECT * FROM $TABLE ORDER BY $ID DESC;",
            null
        )

        while (cursor.moveToNext()) {
            lista.add(cursor.toUsuario())
        }

        cursor.close()
        return lista
    }

    private fun DadosPessoais.toContentValues() = ContentValues().apply {
        put(NOME, nome)
        put(IDADE, idade)
        put(SEXO, sexo)
        put(ALTURA, altura)
        put(PESO, peso)
        put(NIVEL, nivel)
        put(IMC, imc)
        put(TMB, tmb)
        put(GASTO, gastoCalorico)
        put(PESO_IDEAL, pesoIdeal)
        put(CATEGORIA_IMC, categoriaImc)
        put(AGUA, recomendacaoAgua)
    }

    private fun Cursor.toUsuario() = DadosPessoais(
        id = getInt(getColumnIndexOrThrow(ID)),
        nome = getString(getColumnIndexOrThrow(NOME)),
        idade = getIntOrNull(IDADE),
        sexo = getString(getColumnIndexOrThrow(SEXO)),
        altura = getDoubleOrNull(ALTURA),
        peso = getDoubleOrNull(PESO),
        nivel = getString(getColumnIndexOrThrow(NIVEL)),
        imc = getDoubleOrNull(IMC),
        tmb = getDoubleOrNull(TMB),
        gastoCalorico = getDoubleOrNull(GASTO),
        pesoIdeal = getDoubleOrNull(PESO_IDEAL),
        categoriaImc = getStringOrNull(CATEGORIA_IMC),
        recomendacaoAgua = getDoubleOrNull(AGUA)
    )

    private fun Cursor.getIntOrNull(column: String): Int? =
        if (isNull(getColumnIndexOrThrow(column))) null else getInt(getColumnIndexOrThrow(column))

    private fun Cursor.getDoubleOrNull(column: String): Double? =
        if (isNull(getColumnIndexOrThrow(column))) null else getDouble(getColumnIndexOrThrow(column))

    private fun Cursor.getStringOrNull(column: String): String? =
        if (isNull(getColumnIndexOrThrow(column))) null else getString(getColumnIndexOrThrow(column))
}
