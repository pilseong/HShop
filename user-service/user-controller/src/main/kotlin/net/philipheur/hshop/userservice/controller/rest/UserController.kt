package net.philipheur.hshop.userservice.controller.rest

import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.userservice.domain.service.dto.edit.UserEditCommand
import net.philipheur.hshop.userservice.domain.service.dto.edit.UserEditResponse
import net.philipheur.hshop.userservice.domain.service.dto.search.UserSearchCommand
import net.philipheur.hshop.userservice.domain.service.dto.search.UserSearchResponse
import net.philipheur.hshop.userservice.domain.service.ports.input.service.UserService
import net.philipheur.hshop.userservice.controller.exporter.CSVExporter
import net.philipheur.hshop.userservice.controller.rest.config.AppConstants.Companion.DEFAULT_ORDER_BY
import net.philipheur.hshop.userservice.controller.rest.config.AppConstants.Companion.DEFAULT_PAGE_NUMBER
import net.philipheur.hshop.userservice.controller.rest.config.AppConstants.Companion.DEFAULT_PAGE_SIZE
import net.philipheur.hshop.userservice.controller.rest.config.AppConstants.Companion.DEFAULT_SORT_BY
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

import java.util.UUID


@RestController
@RequestMapping(
    value = ["/api/users"],
)
@PreAuthorize("hasAnyRole('Admin')")
class UserController(
    private val userService: UserService,
    private val csvExporter: CSVExporter
) {
    private val log by LoggerDelegator()

    @PutMapping(
        value = ["/{id}"],
        consumes = [
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        ]
    )
    fun editUser(
        @PathVariable("id") id: UUID,
        @RequestPart("command") @Valid command: UserEditCommand,
        @RequestPart(
            name = "image",
            required = false
        ) image: MultipartFile?
    ): ResponseEntity<UserEditResponse> {
        log.info(
            "Editing user with id $id email: {}, role {}",
            command.email, command.roles
        )
        val userEditResponse = userService.editUser(command, image)
        log.info(
            "User edited with " +
                    "id: ${userEditResponse.id}"
        )

        return ResponseEntity.ok(userEditResponse)
    }

    @GetMapping
    fun findAllUsers(
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
            required = false
        ) keyword: String?,
    ): ResponseEntity<UserSearchResponse> {

        log.info("Searching all users with pageNo: $pageNo, pageSize: $pageSize, sortedBy: $sortBy, orderBy: $orderBy")

        val userSearchResponse = userService.findAllUsers(
            keyword = keyword,
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
        )

        log.info("{} of Users found", userSearchResponse.users.size)

        return ResponseEntity.ok(userSearchResponse)
    }

    @GetMapping("/{id}")
    fun findUserById(
        @PathVariable id: UUID
    ): ResponseEntity<UserSearchResponse> {
        log.info("Searching only one user with id $id")

        return ResponseEntity.ok(userService.findUserById(id))
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(
        @PathVariable id: UUID
    ): ResponseEntity<String> {

        userService.deleteUserById(id)

        return ResponseEntity.ok("User with id $id is deleted successfully")

    }

    @PostMapping("/export/csv")
    fun exportToCSV(
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
        @RequestBody command: UserSearchCommand,
        response: HttpServletResponse
    ) {

        log.info("export all users with pageNo: $pageNo, pageSize: $pageSize, sortedBy: $sortBy, orderBy: $orderBy")

        val userSearchResponse = userService.findAllUsers(
            keyword = command.keyword,
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
        )

        log.info("{} of Users found", userSearchResponse.users.size)

        csvExporter.export(userSearchResponse.users, response)
    }
}