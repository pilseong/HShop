package net.philipheur.hshop.userservice.dataaccess.role.adapter

import net.philipheur.hshop.common.domain.valueobject.RoleId
import net.philipheur.hshop.userservice.dataaccess.role.mapper.UserDataaccessMapper
import net.philipheur.hshop.userservice.dataaccess.user.repository.RoleJpaRepository
import net.philipheur.hshop.userservice.domain.core.entity.Role
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.RoleRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.util.*
import kotlin.test.Test

@DataJpaTest(showSql = true)
//@Rollback(value = false)
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(
    classes = [
        net.philipheur.hshop.userservice.UserDataaccessTester::class
    ]
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class RoleRepositoryAdapterTest {

    @Autowired
    lateinit var roleJpaRepository: RoleJpaRepository

    lateinit var repository: RoleRepository

    lateinit var mapper: UserDataaccessMapper

    @BeforeAll
    fun before() {
        mapper = UserDataaccessMapper()
        repository = RoleRepositoryAdapter(
            repository = roleJpaRepository,
            mapper = mapper
        )
    }


    @Test
    fun testCreateFirstRole() {
        val roleAdmin = Role(
            id = RoleId(UUID.randomUUID()),
            name = "Admin",
            description = "manage everything"
        )
        val saved = repository.save(roleAdmin)
        kotlin.test.assertTrue { roleAdmin.name == saved.name }
    }

    @Test
    fun testCreateRestRoles() {
        val roleSalesperson = Role(
            id = RoleId(UUID.randomUUID()),
            name = "Salesperson",
            description = "manage product price, customers, shipping, others and sales report",
        )
        val roleEditor = Role(
            id = RoleId(UUID.randomUUID()),
            name = "Editor",
            description = "manage categories, brands, products, articles and menus",
        )
        val roleShipper = Role(
            id = RoleId(UUID.randomUUID()),
            name = "Shipper",
            description = "view products, view orders and update order status",
        )
        val roleAssistant = Role(
            id = RoleId(UUID.randomUUID()),
            name = "Assistant",
            description = "manage questions and reviews",
        )

        repository.save(roleAssistant)
        repository.save(roleEditor)
        repository.save(roleShipper)
        repository.save(roleSalesperson)

    }
}