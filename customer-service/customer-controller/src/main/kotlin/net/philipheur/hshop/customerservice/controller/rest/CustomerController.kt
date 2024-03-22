package net.philipheur.hshop.customerservice.controller.rest

import jakarta.validation.Valid
import net.philipheur.hshop.common.domain.dtos.settings.SearchResponse
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.customerservice.domain.service.dto.create.CreateCustomerCommand
import net.philipheur.hshop.customerservice.domain.service.dto.create.CreateCustomerResponseDto
import net.philipheur.hshop.customerservice.domain.service.dto.search.CustomerDto
import net.philipheur.hshop.customerservice.domain.service.ports.input.service.CustomerService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(
    value = ["/api/customers"],
//    produces = ["application/vnd.api.v1+json"]
)
class CustomerController(
    private val service: CustomerService
) {
    private val log by LoggerDelegator()

    @PostMapping
    fun createCreate(
        @RequestBody @Valid command: CreateCustomerCommand
    ): ResponseEntity<CreateCustomerResponseDto> {
        log.info(
            "Creating customer with: $command"
        )

        val createCustomerResponseDto = service.createCustomer(command)
        log.info(
            "Customer created with " +
                    "username: ${command.firstName}"
        )

        return ResponseEntity.ok(createCustomerResponseDto)
    }


    @PostMapping(value = ["/logout"])
    fun logout(): ResponseEntity<Map<String, String>> {
        log.info("Log out request")

        val jwt = ResponseCookie
            .from("jwt", "")
            .httpOnly(true)
            .maxAge(0)
            .path("/")
            .sameSite("None")
            .secure(true)
            .build().toString()

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, jwt)
            .body(
                mapOf("message" to "logged out successfully")
            )
    }

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
    ): ResponseEntity<SearchResponse<CustomerDto>> {

        log.info(
            "Searching all customers with keyword $query" +
                    "pageNo: $pageNo, pageSize: $pageSize, " +
                    "sortedBy: $sortBy, orderBy: $orderBy"
        )


        val searchResponse = service.findAllCustomers(
            query = query,
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
        )

        log.info("{} of Customers found", searchResponse.content.size)

        return ResponseEntity.ok(searchResponse)
    }
}