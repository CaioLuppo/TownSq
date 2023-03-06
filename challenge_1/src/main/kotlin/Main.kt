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
        val codigoEscolhido = calculaMaisVotado(votosPorCodigo)
        printaResultado(codigoEscolhido)
    }
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

private fun votosToMap(listaVotos: IntArray): MutableMap<Int, Int> {
    val votosPorCodigo = mutableMapOf<Int, Int>()
    for (codigo in listaVotos) {
        votosPorCodigo[codigo] = votosPorCodigo[codigo]?.plus(1) ?: 1
    }
    return votosPorCodigo
}

private fun calculaMaisVotado(votosPorCodigo: MutableMap<Int, Int>): Int {
    var codigoMaiorVoto = 0
    var maiorVoto = 0
    for (linha in votosPorCodigo) {
        val votosLinha: Int = linha.value
        val codigoLinha: Int = linha.key
        if (votosLinha > maiorVoto) {
            maiorVoto = votosLinha
            codigoMaiorVoto = codigoLinha
        } else if (votosLinha == maiorVoto && codigoMaiorVoto > codigoLinha) {
            codigoMaiorVoto = codigoLinha
        }
    }
    return codigoMaiorVoto
}

private fun printaResultado(codigoEscolhido: Int) {
    val sabor: String? = SABORES[codigoEscolhido]
    if (sabor != null) {
        println("O sabor mais votado foi: nº $codigoEscolhido - ${SABORES[codigoEscolhido]}")
    } else {
        println("O sabor mais votado (código: $codigoEscolhido) não está disponível na lista...")
    }
}
