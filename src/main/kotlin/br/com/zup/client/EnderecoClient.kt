package br.com.zup.client

import br.com.zup.dto.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:8081/cep/")
interface EnderecoClient {

    @Get("{cep}")
    fun consulta(cep: String): HttpResponse<EnderecoResponse>
}