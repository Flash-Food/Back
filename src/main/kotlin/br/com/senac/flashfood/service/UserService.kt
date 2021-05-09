package br.com.senac.flashfood.service

import br.com.senac.flashfood.model.entity.User

interface UserService {

    fun save(user : User) : User

    fun update()

    fun updatePassword()

    fun createOrder()
}