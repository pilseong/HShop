package net.philipheur.hshop.backendservice.rest

import net.philipheur.hshop.backendservice.service.user.UserService
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserRestController(
    private val userService: UserService
) {
    @PostMapping("/is_available_email")
    fun isAvailableEmail(@RequestParam("email") email: String): String{
        return if (userService.isAvailableEmail(email)) "OK" else "NotAvailable"
    }
}