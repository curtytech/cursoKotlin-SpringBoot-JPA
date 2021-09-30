package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.repository.PromocaoRepository
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl(val promocaoRepository: PromocaoRepository): PromocaoService {

    override fun create(promocao: Promocao) {
        this.promocaoRepository.save(promocao)
    }

    override fun getAll(start: Int, size: Int): List<Promocao> {
        val pages:Pageable = PageRequest.of(start, size, Sort.by("local").ascending())
        return this.promocaoRepository.findAll(pages).toList()
    }

    override fun getById(id: Long): Promocao? {
        return promocaoRepository.findById(id).orElseGet(null)
    }

    override fun delete(id: Long) {
        this.promocaoRepository.delete(Promocao(id= id))
    }

    override fun update(id: Long, promocao: Promocao) {
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
        listOf()

    override fun count(): Long =
        this.promocaoRepository.count()

    override fun getAllSortedByLocal(): List<Promocao> =
        this.promocaoRepository.findAll(Sort.by("local").descending()).toList()

    override fun findByPrecoMenorQue9000(): List<Promocao> {
        return this.promocaoRepository.findByPrecoMenorQue9000()
    }

}