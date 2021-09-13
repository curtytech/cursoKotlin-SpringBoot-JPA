package com.acme.tour.service

import com.acme.tour.model.Promocao

interface PromocaoService {
    fun create(promocao: Promocao)
    fun getAll(start: Int, size:Int ): List<Promocao>
    fun getById(id: Long ): Promocao?
    fun delete(id: Long )
    fun update(id: Long, promocao:Promocao)
    fun searchByLocal(local: String): List<Promocao>
    fun count(): Long
    fun getAllSortedByLocal(): List<Promocao>
}