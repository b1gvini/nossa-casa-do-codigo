package br.com.zup.controller

import br.com.zup.dto.DetalhesDoAutorResponse
import br.com.zup.entidade.Autor
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest{

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var  client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {
        autor = Autor("fernanda", "nanda@email.com", "meu amor")
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar os detalhes de um ator`(){

        val resposta =
            client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesDoAutorResponse::class.java)
        assertEquals(HttpStatus.OK,resposta.status)
        assertNotNull(resposta.body())
        assertEquals(autor.nome, resposta.body()!!.nome)
        assertEquals(autor.email, resposta.body()!!.email)
        //assertEquals(autor.descricao, resposta.body()!!.descricao)

    }

}