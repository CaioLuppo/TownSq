private val SABORES: Map<Int, String> = mapOf(
    0 to "Flocos",
    1 to "Chocolate",
    2 to "Morango",
    3 to "Creme",
    4 to "Napolitano"
)

fun main() {
    while (true) {
        val listaVotos: IntArray = lerVotos() ?: continue
        val votosPorCodigo: MutableMap<Int, Int> = votosToMap(listaVotos)
        val codigoMaisVotado = calculaMaisVotado(votosPorCodigo)
        val saborMaisVotado: String? = SABORES[codigoMaisVotado]
        printaResultado(saborMaisVotado, codigoMaisVotado)
    }
}

private fun printaResultado(saborMaisVotado: String?, codigoMaisVotado: Int) {
    if (saborMaisVotado != null) {
        println("O sabor mais votado foi: nº $codigoMaisVotado - ${SABORES[codigoMaisVotado]}")
    } else {
        println("O sabor mais votado (código: $codigoMaisVotado) não está disponível na lista...")
    }
}

private fun votosToMap(listaVotos: IntArray): MutableMap<Int, Int> {
    val votosPorCodigo = mutableMapOf<Int, Int>()
    for (codigo in listaVotos) {
        votosPorCodigo[codigo] = votosPorCodigo[codigo]?.plus(1) ?: 1
    }
    return votosPorCodigo
}

private fun calculaMaisVotado(votosPorCodigo: MutableMap<Int, Int>): Int {
    var maisVotado = 0
    var maiorVoto = 0
    for (codigoVoto in votosPorCodigo) {
        val voto: Int = codigoVoto.value
        val codigo: Int = codigoVoto.key
        if (voto > maiorVoto) {
            maiorVoto = voto
            maisVotado = codigo
        } else if (voto == maiorVoto && maisVotado > codigo) {
            maisVotado = codigo
        }
    }
    return maisVotado
}

private fun lerVotos(): IntArray? {
    print("Digite o array com votos (Ex: [1, 2, 3]): ")
    val input: String = readln().replace(Regex("[\\[\\] ]"), "")
    val strList: List<String> = input.split(",")
    return try {
        strList.map { voto -> voto.toInt() }.toIntArray()
    } catch (e: NumberFormatException) {
        if (input.isEmpty()) println("Nenhum voto foi informado, tente novamente...")
        else println("Formato errado, tente novamente...")
        null
    }
}