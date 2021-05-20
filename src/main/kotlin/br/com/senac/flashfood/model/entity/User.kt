package br.com.senac.flashfood.model.entity

import lombok.AllArgsConstructor
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
@AllArgsConstructor
data class User (
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        var id          : UUID,
        var name        : String,
        @Column(unique = true)
        var email       : String,
        @Column(unique = true)
        var cpf         : String,
        var phoneNumber : String,
        var password    : String
)