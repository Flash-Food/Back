package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.model.dto.restaurant.ProductResponseDTO
import br.com.senac.flashfood.repository.ProductRepository
import br.com.senac.flashfood.service.ProductService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl: ProductService {


    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var mapper: ModelMapper

    override fun findByName(name: String): List<ProductResponseDTO> = productRepository
            .findByNameContainingIgnoreCase(name).map { mapper.map(it, ProductResponseDTO::class.java) }


}