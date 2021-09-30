package com.acme.tour.controller


import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.ErrorMessage
import com.acme.tour.model.Promocao
import com.acme.tour.model.RespostaJSON
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap


@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService
    @GetMapping("/menorQue9000")
    fun getAllMenores() =
        this.promocaoService.findByPrecoMenorQue9000()


    @GetMapping("/{id}")
    fun getGetById(@PathVariable id: Long): ResponseEntity<Any> {
        var promocao = this.promocaoService.getById(id)
        return if(promocao != null)
            return ResponseEntity(promocao,HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Promocao Nao Localizada", "promocao ${id} nao localizada"),
                HttpStatus.NOT_FOUND)
    }

    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJSON>{
        this.promocaoService.create(promocao)
        val respostaJSON = RespostaJSON("OK", Date())
        return ResponseEntity(respostaJSON,HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(this.promocaoService.getById(id) != null){
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao:Promocao): ResponseEntity<Unit>{
        this.promocaoService.update(id, promocao)
        var status = HttpStatus.NOT_FOUND
        if(this.promocaoService.getById(id) != null){
            this.promocaoService.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "3") size: Int ): ResponseEntity<List<Promocao>> {

        val list = this.promocaoService.getAll(start, size)
        val status = if(list.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(list, status)
    }

    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String,Long>> =
        ResponseEntity.ok().body(mapOf("count" to this.promocaoService.count()))

    @GetMapping("/ordenados")
    fun ordenados() = this.promocaoService.getAllSortedByLocal()

}
