package net.philipheur.hshop.catalogservice.rest

import net.philipheur.hshop.catalogservice.domain.service.dto.*
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.BrandService
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.CategoryService
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.ProductService
import net.philipheur.hshop.catalogservice.security.JwtVerificationFilter
import net.philipheur.hshop.common.domain.valueobject.CategoryStatus
import net.philipheur.hshop.common.domain.valueobject.Dimension
import net.philipheur.hshop.common.domain.valueobject.ProductStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.util.*
import kotlin.math.log

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(
    classes = [
        net.philipheur.hshop.catalogservice.CatalogControllerTestApplication::class
    ]
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BrandControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var brandService: BrandService

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var categoryService: CategoryService

    @BeforeAll
    fun init() {
        Mockito.`when`(brandService.findAllBrands("test", 1, 1, "", ""))
            .thenReturn(SearchResponse(
                totalElements = 1,
                totalPages = 1,
                pageNo = 0,
                pageSize = 1,
                last = true,
                content = listOf(
                    BrandDto(
                    id = UUID.randomUUID(),
                    name = "test",
                    logo = "test",
                    categories = emptyList()
                ))
            ))

        Mockito.`when`(productService
            .findAllProductsByKeyword("test", 1, 1, "", "", emptyList()))
            .thenReturn(SearchResponse(
                totalElements = 1,
                totalPages = 1,
                pageNo = 0,
                pageSize = 1,
                last = true,
                content = listOf(
                    ProductDto(
                        id = UUID.randomUUID(),
                        name = "test",
                        alias = "test",
                        shortDescription = "test",
                        fullDescription = "Test",
                        status = ProductStatus.ACTIVE,
                        mainImage = "test",
                        discountPercent = BigDecimal(0),
                        price = BigDecimal(0),
                        cost = BigDecimal(0),
                        dimension = Dimension(0.0, 0.0,0.0,0.0,),
                        brand = BrandDto(
                            id = UUID.randomUUID(),
                            name = "test",
                            logo = "test",
                            categories = emptyList()),
                        category = CategoryDto(
                            id = UUID.randomUUID(),
                            name = "test",
                            alias = "test",
                            image = "Test",
                            parentCategory = null,
                            status = CategoryStatus.ACTIVE,
                            createdAt = null,
                            subCategories = emptyList()
                        ),
                        details = emptyList(),
                        detailImages = emptyList(),
                        createdAt = null,
                        updatedAt = null,
                        displayPrice = "",
                        discountPrice = "",
                        inventory = InventoryDto(
                            UUID.randomUUID(),
                            UUID.randomUUID(),
                            0
                        ),
                        shortName = ""
                    )
                )
            ))

        Mockito.`when`(categoryService.findAllCategories("test", 1, 1, "", "", "ACTIVE", true))
            .thenReturn(SearchResponse(
                totalElements = 1,
                totalPages = 1,
                pageNo = 0,
                pageSize = 1,
                last = true,
                content = listOf(
                    CategoryDto(
                        id = UUID.randomUUID(),
                        name = "test",
                        alias = "test",
                        image = "Test",
                        parentCategory = null,
                        status = CategoryStatus.ACTIVE,
                        createdAt = null,
                        subCategories = emptyList()
                    ))
            ))

    }

    @Test
    fun testListBrands() {
        val url = "/api/brands"
        mockMvc.perform(get(url)).andExpect(status().isOk())
    }
}