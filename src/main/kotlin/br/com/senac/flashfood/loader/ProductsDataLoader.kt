package br.com.senac.flashfood.loader

import br.com.senac.flashfood.constant.CategoryProduct
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
                        category = CategoryProduct.SALTY,
                        price = 10.00,
                        menu = menu
                ),
                Product(
                        name = "Brigadeiro",
                        description = "Brigadeiro artesanal",
                        category = CategoryProduct.CANDY,
                        price = 2.00,
                        menu = menu
                ),
                Product(
                        name = "Coca Cola",
                        description = "Refrigerante de 200ml",
                        category = CategoryProduct.DRINK,
                        price = 5.00,
                        menu = menu
                )
        )
    )


    fun createProductsForVictorinos(menu: Menu)  = saveProducts (
            arrayListOf(
                    Product(
                            name = "Empadinha",
                            description = "Massa artesanal, com recheio de frango",
                            category = CategoryProduct.SALTY,
                            price = 3.00,
                            menu = menu
                    ),
                    Product(
                            name = "Pão de mel",
                            description = "Pão de mel com recheio de doce de leite",
                            category = CategoryProduct.CANDY,
                            price = 3.00,
                            menu = menu
                    ),
                    Product(
                            name = "Água",
                            description = "Água de 200ml",
                            category = CategoryProduct.DRINK,
                            price = 3.00,
                            menu = menu
                    )
            )
    )

    fun createProductsForDogaoHouse(menu: Menu)  = saveProducts (
            arrayListOf(
                    Product(
                            name = "Dogao trad",
                            description = "Pão de hotdog, salsicha e batata palha.",
                            category = CategoryProduct.SALTY,
                            price = 5.00,
                            menu = menu
                    ),
                    Product(
                            name = "Dogao Moster",
                            description = "Pão de hotdog, três salsichas, pure, bacon," +
                                    "presunto, queijo e batata palha.",
                            category = CategoryProduct.SALTY,
                            price = 12.00,
                            menu = menu
                    ),
                    Product(
                            name = "Canudos de chocolate",
                            description = "5 canudos de chocolate belga",
                            category = CategoryProduct.CANDY,
                            price = 5.00,
                            menu = menu
                    ),
                    Product(
                            name = "Chá",
                            description = "Chá de 200ml",
                            category = CategoryProduct.DRINK,
                            price = 3.00,
                            menu = menu
                    )
            )
    )



    private fun saveProducts(products: List<Product>) = products.forEach{
        productRepository.save(it)
    }

}