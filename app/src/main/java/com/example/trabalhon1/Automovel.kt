package com.example.trabalhon1

class Automovel {
    var id: Int = 0
    var marca: String? = null
    var modelo: String? = null
    var ano: Int = 0
    var cor: String? = null

    constructor()

    constructor(marca: String?, modelo: String?, ano: Int, cor: String?) {
        this.marca = marca
        this.modelo = modelo
        this.ano = ano
        this.cor = cor
    }

    constructor(id: Int, marca: String?, modelo: String?, ano: Int, cor: String?) {
        this.id = id
        this.marca = marca
        this.modelo = modelo
        this.ano = ano
        this.cor = cor
    }
    override fun toString(): String {
        return "Marca: $marca\nModelo: $modelo\nAno: $ano\nCor: $cor\n"
    }
}