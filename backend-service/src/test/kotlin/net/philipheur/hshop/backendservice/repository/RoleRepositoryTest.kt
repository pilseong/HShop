package net.philipheur.hshop.backendservice.repository

import net.philipheur.hshop.backendservice.entity.Role
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import kotlin.test.assertTrue

@DataJpaTest(showSql = true)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
class RoleRepositoryTest {
    @Autowired
    private lateinit var repository: RoleRepository

    @Test
    fun testCreateFirstRole() {
        val roleAdmin = Role(name = "Admin", description = "manage everything")
        val saved = repository.save(roleAdmin)
        assertTrue { saved.id!! > 0 }
    }

    @Test
    fun testCreateRestRoles() {
        val roleSalesperson = Role(name = "Salesperson",
            description = "manage product price, customers, shipping, others and sales report")
        val roleEditor = Role(name = "Editor",
            description = "manage categories, brands, products, articles and menus")
        val roleShipper = Role(name = "Shipper",
            description = "view products, view orders and update order status")
        val roleAssistant = Role(name = "Assistant",
            description = "manage questions and reviews")

        repository.saveAll(listOf(roleSalesperson, roleEditor, roleShipper ,roleAssistant))
    }
}