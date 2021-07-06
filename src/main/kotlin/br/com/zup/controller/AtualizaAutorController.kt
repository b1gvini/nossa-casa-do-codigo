package br.com.zup.controller

import br.com.zup.dto.DetalhesDoAutorResponse
import br.com.zup.entidade.Autor
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import java.util.*
import javax.transaction.Transactional


@Controller("/autores/{id}")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun atualiza(@PathVariable id: Long, descricao: String): HttpResponse<Any>{
        val possivelAutor: Optional<Autor> = autorRepository.findById(id)
        if(!possivelAutor.isPresent) return HttpResponse.notFound()

        val autor = possivelAutor.get()
        autor.descricao = descricao
        //autorRepository.update(autor);
        //Agora que tem transactional anotado, não precisamos discriminar já que o objeto está gerenciável e no fim do método
        // é encerrada a transacao e automaticamente é 'atualizado' conforme o estado atual.

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}
