package br.com.senac.flashfood.model.entity

import br.com.senac.flashfood.annotations.NoArgs
import lombok.AllArgsConstructor
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.LazyToOne
import org.hibernate.annotations.LazyToOneOption
import org.hibernate.annotations.Proxy
import org.springframework.context.annotation.Lazy
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "restaurant")
@AllArgsConstructor
data class Restaurant (

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        var id          : UUID? = null,

        @Column(unique = true, nullable = false)
        var name        : String,

        @Column(unique = true, nullable = false)
        var phoneNumber  : String,

        var description  : String,

        @OneToOne(mappedBy = "restaurant", cascade = [CascadeType.ALL], optional = true, fetch = FetchType.LAZY)
        @LazyToOne(value = LazyToOneOption.NO_PROXY)
        var menu: Menu? = null,

        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "user_id", nullable = false)
        var user: User?,

        @OneToMany(mappedBy = "restaurant", cascade = [CascadeType.ALL])
        var purchase: List<Purchase>? = null
)