package br.com.senac.flashfood.controller.impl

import br.com.senac.flashfood.controller.UserController
import br.com.senac.flashfood.model.dto.user.UserFindRequestDTO
import br.com.senac.flashfood.model.dto.user.UserFindResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.web.bind.annotation.*
import javax.swing.Spring
import javax.validation.Valid

@RestController()
@RequestMapping(value = ["/user"])
@Api(tags = ["/user"], description = "Endpoint for users")
class UserControllerImpl : UserController {

    @Autowired
    private lateinit var userService : UserService

    @PostMapping("/signup")
    @ApiOperation(
            value = "Signup",
            notes = "Responsible endpoint to signup user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override
    fun signUp(@RequestBody @Valid userDTO: UserSignUpRequestDTO) = ResponseEntity(userService.save(userDTO), HttpStatus.OK);

    @PostMapping("/login")
    @ApiOperation(
            value = "login",
            notes = "Responsible endpoint for return jwt token",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    override fun login() = ResponseEntity<Void>(HttpStatus.OK)


    @PreAuthorize(value = "hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/info")
    @ApiOperation(
            value = "find",
            notes = "Responsible endpoint for return infos of the user",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override fun findUser() =
            ResponseEntity(userService.findBy(
                    SecurityContextHolder.getContext().authentication.name),
                    HttpStatus.OK
            )


    override fun forgotItPassword(email: String): ResponseEntity<String> {
        TODO("Not yet implemented")
    }

    override fun alterPassword(email: String, OldPassword: String, NewPassword: String): ResponseEntity<String> {
        TODO("Not yet implemented")
    }

    override fun order(list: List<String>): ResponseEntity<String> {
        TODO("Not yet implemented")
    }
}