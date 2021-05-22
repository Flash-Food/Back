package br.com.senac.flashfood.model.entity

import br.com.senac.flashfood.annotations.NoArgs
import lombok.Getter
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table
@Getter
data class Role (

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id          : UUID? = null,

    var name        : String,

    @ManyToMany(mappedBy = "roles")
    var users       : Collection<User>? = null,

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = arrayOf(JoinColumn(
                    name = "role_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(
                    name = "privilege_id", referencedColumnName = "id")))
    var privileges  : Collection<Privilege>
)