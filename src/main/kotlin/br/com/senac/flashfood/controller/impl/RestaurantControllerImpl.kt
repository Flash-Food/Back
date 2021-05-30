package br.com.senac.flashfood.controller.impl

import br.com.senac.flashfood.controller.RestaurantController
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import br.com.senac.flashfood.service.RestaurantService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/restaurant"])
@Api(tags = ["/restaurant"], description = "Endpoint for restaurants")
class RestaurantControllerImpl : RestaurantController {

    @Autowired
    private lateinit var restaurantService: RestaurantService


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/signup")
    @ApiOperation(
            value = "/signup",
            notes = "Responsible endpoint to signup restaurants account",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override fun signup(@RequestBody @Valid userDTO: UserSignUpRequestDTO): ResponseEntity<UserSignUpResponseDTO> =
            ResponseEntity(restaurantService.save(userDTO), HttpStatus.OK)

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_RESTAURANT', 'ROLE_ADMIN')")
    @GetMapping
    @ApiOperation(
            value = "",
            notes = "Responsible endpoint to list all restaurants",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override fun getAll() = ResponseEntity(restaurantService.getAll(), HttpStatus.OK)


    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_RESTAURANT', 'ROLE_ADMIN')")
    @GetMapping("/{ID}")
    @ApiOperation(
            value = "/{ID}",
            notes = "Responsible endpoint to get restaurant by id and your products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override fun getById(@PathVariable ID: UUID) = ResponseEntity(restaurantService.getById(ID), HttpStatus.OK)


}