package br.com.zup.controller

import br.com.zup.client.EnderecoClient
import br.com.zup.dto.EnderecoResponse
import br.com.zup.dto.NovoAutorRequest
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastroAutorController(val autorRepository: AutorRepository,
                              val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any>{

        println("Request => $request")
        //val enderecoResponse: HttpResponse<EnderecoResponse> = enderecoClient.consulta(request.cep)
        val autor = request.paraAutor()

        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}")
                            .expand(mutableMapOf(Pair("id",autor.id)))
        println("Entidade => ${autor.nome}")

        return HttpResponse.created(uri)
    }
}