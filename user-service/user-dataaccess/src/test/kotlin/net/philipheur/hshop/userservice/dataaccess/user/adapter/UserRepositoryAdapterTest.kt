package net.philipheur.hshop.userservice.dataaccess.user.adapter

import net.philipheur.hshop.common.domain.valueobject.RoleId
import net.philipheur.hshop.common.domain.valueobject.UserId
import net.philipheur.hshop.common.domain.valueobject.UserStatus
import net.philipheur.hshop.userservice.dataaccess.role.adapter.RoleRepositoryAdapter
import net.philipheur.hshop.userservice.dataaccess.role.mapper.UserDataaccessMapper
import net.philipheur.hshop.userservice.dataaccess.user.entity.UserEntity
import net.philipheur.hshop.userservice.dataaccess.user.repository.RoleJpaRepository
import net.philipheur.hshop.userservice.dataaccess.user.repository.UserJpaRepository
import net.philipheur.hshop.userservice.domain.core.entity.Role
import net.philipheur.hshop.userservice.domain.core.entity.User
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.RoleRepository
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.UserRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import kotlin.test.assertTrue
import java.util.*;


@DataJpaTest(showSql = true)
@Rollback(value = false)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(
    classes = [
        net.philipheur.hshop.userservice.UserDataaccessTester::class
    ]
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryAdapterTest {

    @Autowired
    lateinit var userJpaRepository: UserJpaRepository

    @Autowired
    lateinit var roleJpaRepository: RoleJpaRepository

    lateinit var roleRepository: RoleRepository

    lateinit var userRepository: UserRepository

    lateinit var mapper: UserDataaccessMapper

    lateinit var passwordEncoder: PasswordEncoder

    @BeforeAll
    fun beforeAll() {

        passwordEncoder = BCryptPasswordEncoder()

        mapper = UserDataaccessMapper()
        userRepository = UserRepositoryAdapter(
            repository = userJpaRepository,
            mapper = mapper
        )
        roleRepository = RoleRepositoryAdapter(
            repository = roleJpaRepository,
            mapper = mapper
        )
//
//        val roleAdmin = Role(
//            id = RoleId(UUID.randomUUID()),
//            name = "Admin",
//            description = "manage everything",
//
//            )
//        val roleSalesperson = Role(
//            id = RoleId(UUID.randomUUID()),
//            name = "Salesperson",
//            description = "manage product price, customers, shipping, others and sales report",
//
//            )
//        val roleEditor = Role(
//            id = RoleId(UUID.randomUUID()),
//            name = "Editor",
//            description = "manage categories, brands, products, articles and menus",
//
//            )
//        val roleShipper = Role(
//            id = RoleId(UUID.randomUUID()),
//            name = "Shipper",
//            description = "view products, view orders and update order status",
//
//            )
//        val roleAssistant = Role(
//            id = RoleId(UUID.randomUUID()),
//            name = "Assistant",
//            description = "manage questions and reviews",
//
//            )
//
//        roleJpaRepository.saveAll(
//            listOf(roleAdmin, roleSalesperson, roleEditor, roleShipper, roleAssistant).map {
//                mapper.transformRoleDomainToEntity(it)
//            }
//        )
    }

    @Test
    fun testCreateFirstUser() {
        val role = roleRepository.findByName("ADMIN") ?: return

        val user = User(
            id = UserId(UUID.randomUUID()),
            email = "heops79@gmail.com",
            password = passwordEncoder.encode("qwe123"),
            firstName = "Pilseong",
            lastName = "Heo",
            status = UserStatus.ACTIVE,
            roles = mutableListOf(role)
        )


        println(passwordEncoder.encode("qwe123"))

        val saved = userRepository.save(user)
        assertTrue { saved.email == user.email }
    }

    @Test
    fun testCreateUserWithTwoRoles() {
        val user = User(
            id = UserId(UUID.randomUUID()),
            email = "heops79@gmail.com",
            password = passwordEncoder.encode("qwe123"),
            firstName = "Pilseong",
            lastName = "Heo",
            status = UserStatus.ACTIVE,
            roles = mutableListOf()
        )

        val roleAdmin = roleRepository.findByName("Admin")
        val roleSalesperson = roleRepository.findByName("Salesperson")

        user.roles.add(roleAdmin!!)
        user.roles.add(roleSalesperson!!)

        val saved = userRepository.save(user)
        assertTrue { saved.roles.size == 2 }
    }

    @Test
    fun testListAllUsers() {
        val user1 = User(
            id = UserId(UUID.randomUUID()),
            email = "test1@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
            roles = mutableListOf()
        )
        val user2 = User(
            id = UserId(UUID.randomUUID()),
            email = "test2@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
            roles = mutableListOf(),
        )

        val roleAdmin = roleRepository.findByName("Admin")
        val roleSalesperson = roleRepository.findByName("Salesperson")

        user1.roles.add(roleAdmin!!)
        user2.roles.add(roleSalesperson!!)

        userRepository.save(user1)
        userRepository.save(user2)

        val result = userRepository.findAll(
            pageNo = 0,
            pageSize = 10,
            sortBy = "createdAt",
            orderBy = "desc",
            keyword = ""
        )

        assertTrue { result.content.size == 2 }
    }

    @Test
    fun testFindByEmail() {

        val user1 = saveUser()

        val fetchedUser1 = userRepository.findByEmail(user1.email)
        assertTrue { fetchedUser1!!.email == user1.email }
    }

    //    @Test
    private fun saveUser(): UserEntity {
        val user1 = UserEntity(
            id = UUID.randomUUID(),
            email = "test1@test.com",
            password = "qwe123",
            firstName = "first",
            lastName = "last",
            status = UserStatus.INACTIVE,
            roles = mutableSetOf()
        )

        val roleAdmin = roleJpaRepository.findByName("Admin")
        user1.roles.add(roleAdmin!!)

        val entity = userJpaRepository.save(user1)

        assertTrue {
            entity.id != null
        }

        return entity
    }
}