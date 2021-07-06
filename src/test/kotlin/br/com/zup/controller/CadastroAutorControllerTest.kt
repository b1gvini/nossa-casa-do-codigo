package br.com.zup.controller

import br.com.zup.dto.NovoAutorRequest
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class CadastroAutorControllerTest{

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    internal fun `deve cadastrar um novo autor`() {

        //cenario
        val novoAutorRequest = NovoAutorRequest("Fernandinha","nandinha@email.com","sou linda")
        val request = HttpRequest.POST("/autores", novoAutorRequest)

        //acao
        val resposta = client.toBlocking().exchange(request,Any::class.java)

        //corretude
        Assertions.assertEquals(HttpStatus.CREATED, resposta.status)
        Assertions.assertTrue(resposta.headers.contains("Location"))
        Assertions.assertTrue(resposta.header("Location")!!.matches("/autores/\\d".toRegex()))
    }
}