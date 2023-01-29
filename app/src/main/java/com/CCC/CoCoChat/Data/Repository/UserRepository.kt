package com.CCC.CoCoChat.Data.Repository

import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun insertUser(user: User) {
        userDao.insertUser(user)
    }
}