package br.com.zup.dto

import br.com.zup.entidade.Autor

class DetalhesDoAutorResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
