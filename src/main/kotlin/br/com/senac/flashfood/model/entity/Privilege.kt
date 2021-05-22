package br.com.senac.flashfood.model.entity

import lombok.AllArgsConstructor
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table
@AllArgsConstructor
data class Privilege (

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        var id          : UUID? = null,

        var name        : String,

        @ManyToMany(mappedBy = "privileges")
        var roles : Collection<Role>? = null
)