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
        val adminEmail = "admin"
        val adminPwHash = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918"
        save(UserData(adminEmail, adminPwHash, isAdmin = true))

        val bobEmail = "bob"
        val bobPwHash = "911266793c9ec8582f0b72c49e544ce8703216768f931f33cb9c34a1f57f6822"
        val bobPhone = "+49 1550 3173420"
        save(UserData(bobEmail, bobPwHash, bobPhone, hasMfa = false))
    }

    fun save(user: UserData) = userDb.save(user)

    fun get(email: String) = userDb.get(email)

    fun exists(email: String) = userDb.exists(email)

    fun isAdmin(email: String) = userDb.get(email).isAdmin
}
