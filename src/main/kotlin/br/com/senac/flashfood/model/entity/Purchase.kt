package br.com.senac.flashfood.model.entity

import br.com.senac.flashfood.constant.PaymentTypeConstants
import lombok.AllArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.GenerationTime
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.annotation.Generated
import javax.persistence.*

@Entity
@Table(name = "purchase")
@AllArgsConstructor
data class Purchase (

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        var id          :   UUID? = null,

        var codPurchase          :   UUID? = UUID.randomUUID(),

        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "user_id", nullable = false)
        var client      :   User,

        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "restaurant_id", nullable = false)
        var restaurant  :   Restaurant,

        @Temporal(TemporalType.TIMESTAMP)
        @org.hibernate.annotations.Generated(GenerationTime.INSERT)
        @ColumnDefault(value = "CURRENT_TIMESTAMP")
        var purchaseDate:   Date? = null,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var paymentType: PaymentTypeConstants,

        var totalValue:     Double,

        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinTable(
                name = "purchase_products",
                joinColumns = [JoinColumn(name = "purchase_id")],
                inverseJoinColumns = [JoinColumn(name = "product_id")]
        )
        var products:       List<Product>
)