package br.com.senac.flashfood.loader

import br.com.senac.flashfood.model.entity.Category
import br.com.senac.flashfood.model.entity.Menu
import br.com.senac.flashfood.model.entity.Product
import br.com.senac.flashfood.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductsDataLoader {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun createProductsForBrotinos(menu: Menu)  = saveProducts (
        arrayListOf(
                Product(
                        name = "x-salada",
                        description = "Pão, hamburguer, queijo e salada",
                        category = Category.SALTY,
                        price = 10.00,
                        menu = menu
                ),
                Product(
                        name = "Brigadeiro",
                        description = "Brigadeiro artesanal",
                        category = Category.CANDY,
                        price = 2.00,
                        menu = menu
                ),
                Product(
                        name = "Coca Cola",
                        description = "Refrigerante de 200ml",
                        category = Category.DRINK,
                        price = 5.00,
                        menu = menu
                )
        )
    )


    private fun saveProducts(products: List<Product>) = products.forEach{
        productRepository.save(it)
    }

}