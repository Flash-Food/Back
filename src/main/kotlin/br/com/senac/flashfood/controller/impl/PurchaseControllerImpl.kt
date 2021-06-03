package br.com.senac.flashfood.controller.impl

import br.com.senac.flashfood.controller.PurchaseController
import br.com.senac.flashfood.model.dto.purchase.PurchaseRequestDTO
import br.com.senac.flashfood.service.PurchaseService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/purchase"])
@Api(tags = ["/purchase"], description = "Endpoint for purchases")
class PurchaseControllerImpl: PurchaseController {

    @Autowired
    private lateinit var purchaseService: PurchaseService

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @PostMapping
    @ApiOperation(
            value = "",
            notes = "Responsible endpoint to purchase products of the restaurants",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override fun save(@RequestBody @Valid requestDTO: PurchaseRequestDTO) =
            ResponseEntity(purchaseService.save (requestDTO,
                            SecurityContextHolder.getContext().authentication.name), HttpStatus.OK)







}