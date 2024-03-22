package net.philipheur.hshop.catalogservice.rest

import net.philipheur.hshop.catalogservice.domain.service.dto.CatalogSearchQuery
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.catalogservice.domain.service.dto.CategoryDto
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.CategoryService
import net.philipheur.hshop.catalogservice.rest.AppConstants.Companion.DEFAULT_ORDER_BY
import net.philipheur.hshop.catalogservice.rest.AppConstants.Companion.DEFAULT_PAGE_NUMBER
import net.philipheur.hshop.catalogservice.rest.AppConstants.Companion.DEFAULT_PAGE_SIZE
import net.philipheur.hshop.catalogservice.rest.AppConstants.Companion.DEFAULT_SORT_BY
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.util.UUID

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    private val log by LoggerDelegator()
    @GetMapping
    fun findAllCategories(
        @RequestParam(
            defaultValue = DEFAULT_PAGE_NUMBER,
            required = false
        ) pageNo: Int,
        @RequestParam(
            defaultValue = DEFAULT_PAGE_SIZE,
            required = false
        ) pageSize: Int,
        @RequestParam(
            defaultValue = DEFAULT_SORT_BY,
            required = false
        ) sortBy: String,
        @RequestParam(
            defaultValue = DEFAULT_ORDER_BY,
            required = false
        ) orderBy: String,
        @RequestParam(
            defaultValue = "ALL",
            required = false
        ) enabled: String,
        @RequestParam(
            defaultValue = "false",
            required = false,
        ) isLeaf: Boolean,
        @RequestParam(
            defaultValue = "",
            required = false
        ) query: String,
    ): ResponseEntity<SearchResponse<CategoryDto>> {

        log.info("Searching all categories with keyword ${query}" +
                "pageNo: $pageNo, pageSize: $pageSize, " +
                "sortedBy: $sortBy, orderBy: $orderBy, " +
                "isLeaf: $isLeaf, enabled: $enabled")

        val searchResponse = categoryService.findAllCategories(
            query = query,
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            enabled = enabled,
            isLeaf = isLeaf
        )

        log.info("{} of Categories found", searchResponse.content.size)

        return ResponseEntity.ok(searchResponse)
    }

    @GetMapping("/{key}")
    fun findByIdOrAlias(
        @PathVariable("key") key: String
    ): ResponseEntity<SearchResponse<CategoryDto>> {

        log.info("findByAlias is called key: $key")

        val result = if (key.split("-").size != 5) {
            categoryService.findByAlias(key)
        } else {
            categoryService.findById(UUID.fromString(key))
        }

        return ResponseEntity.ok(result)
    }
}