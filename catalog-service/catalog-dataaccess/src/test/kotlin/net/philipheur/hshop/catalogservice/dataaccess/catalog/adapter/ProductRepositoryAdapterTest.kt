package net.philipheur.hshop.catalogservice.dataaccess.catalog.adapter

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.*
import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.BrandDataaccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.CategoryDataaccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.ProductDataAccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.BrandJpaRepository
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.CategoryJpaRepository
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.ProductJpaRepository
import net.philipheur.hshop.common.domain.valueobject.CategoryStatus
import net.philipheur.hshop.common.domain.valueobject.ProductStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.*
import kotlin.test.Test

@DataJpaTest(showSql = true)
//@Rollback(value = false)
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(
    classes = [
        net.philipheur.hshop.catalogservice.DataaccessTester::class
    ]
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRepositoryAdapterTest {

    @Autowired
    lateinit var productJpaRepository: ProductJpaRepository

    @Autowired
    lateinit var brandJpaRepository: BrandJpaRepository

    @Autowired
    lateinit var categoryJpaRepository: CategoryJpaRepository


    lateinit var mapper: ProductDataAccessMapper

    lateinit var adapter: ProductRepositoryAdapter

    @BeforeAll
    fun before() {
        val categoryDataaccessMapper = CategoryDataaccessMapper()
        val brandDataaccessMapper = BrandDataaccessMapper(categoryDataaccessMapper)

        mapper = ProductDataAccessMapper(
            brandDataaccessMapper = brandDataaccessMapper,
            categoryDataaccessMapper = categoryDataaccessMapper
        )
        adapter = ProductRepositoryAdapter(
            productRepository = productJpaRepository,
            categoryRepository = categoryJpaRepository,
            brandDataaccessMapper = brandDataaccessMapper,
            categoryDataaccessMapper = categoryDataaccessMapper,
            productDataAccessMapper = mapper
        )
    }

    @Test
    fun testCreateFirstProduct() {

        val catalogId = UUID.randomUUID()
        val categoryEntity = categoryJpaRepository.save(
            CategoryEntity(
                id = catalogId,
                name = "cat1",
                alias = "cat1",
                image = "",
                parent = null,
                status = CategoryStatus.ACTIVE,
                subCategories = mutableSetOf(),
                createdAt = null,
            )
        )


        val brandId = UUID.randomUUID()
        val brandEntity = brandJpaRepository.save(
            BrandEntity(
                id = brandId,
                name = "brand1",
                logo = "",
                categories = mutableSetOf(categoryEntity),
                createdAt = null,
            )
        )

        val id = UUID.randomUUID()

        var productEntity = ProductEntity(
            id = id,
            name = "test",
            alias = "Test",
            shortDescription = "test",
            fullDescription = "test",
            status = ProductStatus.INACTIVE,
            mainImage = "",
            discountPercent = BigDecimal(0.0),
            price = BigDecimal(0.0),
            cost = BigDecimal(0.0),
            length = 0.0,
            width = 0.0,
            height = 0.0,
            weight = 0.0,
            brand = brandEntity,
            category = categoryEntity,
            createdAt = ZonedDateTime.now()
        )

        val inventoryEntity = InventoryEntity(
            id = UUID.randomUUID(),
            product = productEntity,
            amount = 0
        )

        productJpaRepository.save(productEntity)

        val option = productJpaRepository.findById(id)
        val entity = option.get()

        println("created time ${entity.createdAt}")
        println("updated time ${entity.updatedAt} ${entity.name}")

        val productEntityOption = productJpaRepository.findById(id)
        productEntity = productEntityOption.get()

        productEntity.name = "testupdated"
        productEntity.updatedAt = ZonedDateTime.now()

        productJpaRepository.save(productEntity)

        println("created time ${entity.createdAt}")
        println("updated time ${entity.updatedAt} ${entity.name}")
//        val roleAdmin = Role(
//            id = RoleId(UUID.randomUUID()),
//            name = "Admin",
//            description = "manage everything"
//        )
//        val saved = repository.save(roleAdmin)
//        kotlin.test.assertTrue { roleAdmin.name == saved.name }
    }

}