package br.com.senac.flashfood.model.entity

import br.com.senac.flashfood.annotations.NoArgs
import lombok.AllArgsConstructor
import org.hibernate.annotations.GenericGenerator
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
        var productList: List<Product>? = null,

        @OneToOne
        @JoinColumn(name = "restaurant_id", nullable = false)
        var restaurant: Restaurant
)