package net.philipheur.hshop.catalogservice.rest

import feign.Response
import io.micrometer.core.instrument.search.Search
import net.philipheur.hshop.catalogservice.domain.service.dto.CatalogSearchQuery
import net.philipheur.hshop.catalogservice.domain.service.dto.CreateProductCommand
import net.philipheur.hshop.catalogservice.domain.service.dto.ProductDto
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.ProductService
import net.philipheur.hshop.catalogservice.exchange.SettingsServiceClient
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class ProductController(
    private val productService: ProductService,
    private val settingsServiceClient: SettingsServiceClient,
) {
    private val log by LoggerDelegator()

    @PostMapping("/api/products")
    fun createProduct(
        @RequestBody command: CreateProductCommand
    ): ResponseEntity<String> {

        log.info("command is $command")

        productService.createProduct(command)

        return ResponseEntity.ok("successfully saved")
    }

    @GetMapping("/api/products")
    fun findAllProducts(
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
            required = false
        ) pageNo: Int,
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_PAGE_SIZE,
            required = false
        ) pageSize: Int,
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_SORT_BY,
            required = false
        ) sortBy: String,
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_ORDER_BY,
            required = false
        ) orderBy: String,
        @RequestParam(
            defaultValue = "false",
            required = false
        ) fullTextSearch: Boolean,
        @RequestParam(
            defaultValue = "",
            required = false
        ) query: String,
    ): ResponseEntity<SearchResponse<ProductDto>> {

        log.info(
            "Searching all products with keyword $query" +
                    "pageNo: $pageNo, pageSize: $pageSize, " +
                    "sortedBy: $sortBy, orderBy: $orderBy, " +
                    "fulltextsearch: $fullTextSearch"
        )

        val settings = settingsServiceClient.findAllSettings()

        println("settings from setting service $settings")

        val searchResponse = if (fullTextSearch) {
            productService.findAllProductsByKeywordFullText(
                query = query,
                pageNo = pageNo,
                pageSize = pageSize,
                sortBy = sortBy,
                orderBy = orderBy,
                settings.content
            )
        } else {
            productService.findAllProductsByKeyword(
                query = query,
                pageNo = pageNo,
                pageSize = pageSize,
                sortBy = sortBy,
                orderBy = orderBy,
                settings.content
            )
        }

        log.info("{} of Products found", searchResponse.content.size)

        return ResponseEntity.ok(searchResponse)
    }

    @GetMapping("/api/products/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<ProductDto> {

        log.info("searching for an product with id or alias $id")

        val settings = settingsServiceClient.findAllSettings()
        log.info("settings from setting service $settings")
        val product = productService.findById(
            UUID.fromString(id),
            settings.content
        )

        return ResponseEntity.ok(product)
    }

    @GetMapping("/api/categories/{categoryId}/products")
    fun findByCategoryId(
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
            required = false
        ) pageNo: Int,
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_PAGE_SIZE,
            required = false
        ) pageSize: Int,
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_SORT_BY,
            required = false
        ) sortBy: String,
        @RequestParam(
            defaultValue = AppConstants.DEFAULT_ORDER_BY,
            required = false
        ) orderBy: String,
        @PathVariable("categoryId") categoryId: UUID,
    ): ResponseEntity<SearchResponse<ProductDto>> {
        log.info("finding products with category id with $categoryId")

        val settings = settingsServiceClient.findAllSettings()

        println("settings from setting service $settings")

        val searchResponse = productService.findByCategoryId(
            categoryId = categoryId,
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            settings = settings.content
        )

        return ResponseEntity.ok(searchResponse)
    }
}