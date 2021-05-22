package br.com.senac.flashfood.model.entity

import br.com.senac.flashfood.constant.CategoryProduct
import lombok.AllArgsConstructor
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
@AllArgsConstructor
data class Product (

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        var id          : UUID? = null,

        @Column(nullable = false)
        var name        : String,

        var description : String,

        @Column(nullable = false)
        var price       : Double,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var category: CategoryProduct,

        @ManyToOne
        @JoinColumn(name = "menu_id", nullable = false)
        var menu: Menu
)