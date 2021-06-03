package br.com.senac.flashfood.controller.impl

import br.com.senac.flashfood.controller.ProductController
import br.com.senac.flashfood.model.dto.restaurant.ProductResponseDTO
import br.com.senac.flashfood.service.ProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/product"])
@Api(tags = ["/product"], description = "Endpoint for product")
class ProductControllerImpl: ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_RESTAURANT','ROLE_ADMIN')")
    @GetMapping
    @ApiOperation(
            value = "",
            notes = "Responsible endpoint to return list of products filtering by name",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override fun findByName(@RequestParam(name = "name") name: String) = ResponseEntity(
            productService.findByName(name),
            HttpStatus.OK)
}