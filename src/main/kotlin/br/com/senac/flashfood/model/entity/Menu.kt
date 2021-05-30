package br.com.senac.flashfood.model.entity

import br.com.senac.flashfood.annotations.NoArgs
import lombok.AllArgsConstructor
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.LazyToOne
import org.hibernate.annotations.LazyToOneOption
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "menu")
@AllArgsConstructor
data class Menu (

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        var id          : UUID? = null,

        @OneToMany(mappedBy = "menu", cascade = [CascadeType.ALL])
        var productsList: List<Product>? = null,


        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "restaurant_id", nullable = false)
        var restaurant: Restaurant
)