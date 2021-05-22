package br.com.senac.flashfood.constant



enum class JWTConstants(value: String) {
    HEADER_NAME("authorization"),
    PREFIX("Bearer"),
    AUTHORITIES("authorities");


    private val value = value

    fun getValue() = value
}