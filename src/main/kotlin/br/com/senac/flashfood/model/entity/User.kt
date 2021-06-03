package br.com.senac.flashfood.model.entity

import lombok.AllArgsConstructor
import org.hibernate.annotations.CreationTimestamp
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
        var id          : UUID? = null,

        @Column(nullable = false)
        var name        : String,

        @Column(nullable = false, unique = true)
        var email       : String,

        @Column(nullable = false, unique = true)
        var cpf         : String,

        @Column(nullable = false)
        var phoneNumber : String,

        @Column(nullable = false)
        var password    : String,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "users_roles",
                joinColumns = [JoinColumn(
                        name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(
                        name = "role_id", referencedColumnName = "id")])
        var roles       : Collection<Role>,


        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var restaurants : List<Restaurant>? = null,

        var dateAccess  : Date? = null,

        @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var purchaseList : List<Purchase>? = null
)