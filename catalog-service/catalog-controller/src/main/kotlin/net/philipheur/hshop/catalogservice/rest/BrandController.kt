package net.philipheur.hshop.catalogservice.rest

import net.philipheur.hshop.catalogservice.domain.service.dto.BrandDto
import net.philipheur.hshop.catalogservice.domain.service.dto.CatalogSearchQuery
import net.philipheur.hshop.catalogservice.domain.service.dto.CategoryDto
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.BrandService
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/brands"])
class BrandController(
    private val brandService: BrandService
) {

    private val log by LoggerDelegator()

    @GetMapping
    fun findAllBrands(
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
            defaultValue = "",
            required = false
        ) query: String,
    ): ResponseEntity<SearchResponse<BrandDto>> {

        log.info("Searching all brands with keyword ${query}" +
                "pageNo: $pageNo, pageSize: $pageSize, " +
                "sortedBy: $sortBy, orderBy: $orderBy")

        val searchResponse = brandService.findAllBrands(
            query = query,
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
        )

        log.info("{} of Brands found", searchResponse.content.size)

        return ResponseEntity.ok(searchResponse)
    }
}