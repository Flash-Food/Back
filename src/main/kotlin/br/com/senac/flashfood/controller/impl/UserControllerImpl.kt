package br.com.senac.flashfood.controller.impl

import br.com.senac.flashfood.controller.UserController
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController()
@RequestMapping(value = ["/v1/user"])
@Api(tags = ["/v1/user"], description = "Endpoint for users")
class UserControllerImpl : UserController {

    @Autowired
    lateinit var mapper : ModelMapper

    @Autowired
    lateinit var userService : UserService

    @PostMapping()
    @ApiOperation(
            value = "Signup",
            notes = "Responsible endpoint to signup user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    override fun signUp(@RequestBody @Valid userDTO: UserSignUpRequestDTO)
            : ResponseEntity<UserSignUpResponseDTO> {

            mapper.createTypeMap(User::class.java, UserSignUpResponseDTO::class.java)
            if(mapper.getTypeMap(User::class.java, UserSignUpResponseDTO::class.java) == null)
                println("Teste")
            val user = mapper.map(userDTO, User::class.java)
            var teste = userService.save(user)
            var responseDTO = mapper.map(teste, UserSignUpResponseDTO::class.java)
            return ResponseEntity(responseDTO, HttpStatus.OK);
    }

    override fun login(user: Objects): ResponseEntity<String> {
        TODO("Not yet implemented")
    }

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