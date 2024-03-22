package net.philipheur.hshop.userservice.controller.rest

import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.userservice.domain.service.dto.RoleDto
import net.philipheur.hshop.userservice.domain.service.dto.search.RoleSearchResponse
import net.philipheur.hshop.userservice.domain.service.ports.input.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/roles")
//@CrossOrigin(origins = ["http://localhost:3000"])
class RoleController(
    private val userService: UserService
) {
    private val log by LoggerDelegator()

    @GetMapping
    fun findAllRoles(): ResponseEntity<RoleSearchResponse> {
        return ResponseEntity.ok(userService.findAllRoles())
    }
}