package com.nexenio.user.service

import com.nexenio.user.data.UserData
import com.nexenio.user.data.UserDb
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class UserService {
    @Autowired
    private lateinit var userDb: UserDb

    @PostConstruct
    fun setupAdmin() {
        val adminUserName = "admin"
        val adminPwSalt = "3ba1e2ff0dcd"
        val adminPwHash = "1030adead8106e4b380095ae7d2bf231fc07eeafbf643c79a862ad1979e8da87"
        save(UserData(adminUserName, adminPwSalt, adminPwHash, hasMfa = false, isAdmin = true))

        val bobUserName = "bob"
        val bobPwSalt = "4a9ef75b1cf4"
        val bobPwHash = "b02af20b4a9ce3657d62caffed7d23c0edad7e8bc583fc13d7b788840f682227"
        val bobPhone = "+49 1550 3173420"
        save(UserData(bobUserName, bobPwSalt, bobPwHash, bobPhone, hasMfa = true))
    }

    fun save(user: UserData) = userDb.save(user)

    fun get(email: String) = userDb.get(email)

    fun exists(email: String) = userDb.exists(email)

    fun isAdmin(email: String) = userDb.get(email).isAdmin
}
