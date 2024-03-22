package net.philipheur.hshop.backendservice.repository

import net.philipheur.hshop.backendservice.entity.Role
import net.philipheur.hshop.backendservice.entity.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import kotlin.test.assertTrue

@DataJpaTest(showSql = true)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Rollback(value = false)
class UserRepositoryTest {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @BeforeAll
    fun beforeAll() {
        userRepository.deleteAll()
        roleRepository.deleteAll()
        val roleAdmin = Role(name = "Admin", description = "manage everything")
        val roleSalesperson = Role(name = "Salesperson",
            description = "manage product price, customers, shipping, others and sales report")
        val roleEditor = Role(name = "Editor",
            description = "manage categories, brands, products, articles and menus")
        val roleShipper = Role(name = "Shipper",
            description = "view products, view orders and update order status")
        val roleAssistant = Role(name = "Assistant",
            description = "manage questions and reviews")
        roleRepository.saveAll(
            listOf(roleAdmin, roleSalesperson, roleEditor, roleShipper ,roleAssistant))
    }

    @Test
    fun testCreateFirstUser() {
        val user = User(
            email = "test@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
        )
        val roleAdmin = roleRepository.findByName("Admin")

        user.roles.add(roleAdmin!!)
        val saved = userRepository.save(user)
        assertTrue { saved.id!! > 0 }
    }

    @Test
    fun testCreateUserWithTwoRoles() {
        val user = User(
            email = "test@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
        )
        val roleAdmin = roleRepository.findByName("Admin")
        val roleSalesperson = roleRepository.findByName("Salesperson")

        user.roles.add(roleAdmin!!)
        user.roles.add(roleSalesperson!!)

        val saved = userRepository.save(user)
        assertTrue { saved.id!! > 0 }
    }

    @Test
    fun testListAllUsers() {
        val user1 = User(
            email = "test1@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
        )
        val user2 = User(
            email = "test2@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
        )


        val roleAdmin = roleRepository.findByName("Admin")
        val roleSalesperson = roleRepository.findByName("Salesperson")

        user1.roles.add(roleAdmin!!)
        user2.roles.add(roleSalesperson!!)

        userRepository.save(user1)
        userRepository.save(user2)

        val users = userRepository.findAll()

        assertTrue { users.size == 2 }
    }

    @Test
    fun testFindByEmail() {

        val user1 = saveUser()

        val fetchedUser1 = userRepository.findByEmail(user1.email)
        assertTrue { fetchedUser1!!.email ==  user1.email }
    }

    private fun saveUser(): User {
        val user1 = User(
            email = "test1@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
        )
        val roleAdmin = roleRepository.findByName("Admin")
        user1.roles.add(roleAdmin!!)
        return userRepository.save(user1)
    }
}