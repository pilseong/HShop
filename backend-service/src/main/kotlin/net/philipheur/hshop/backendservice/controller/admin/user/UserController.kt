package net.philipheur.hshop.backendservice.controller.admin.user

import net.philipheur.hshop.backendservice.dto.user.UserDto
import net.philipheur.hshop.backendservice.entity.User
import net.philipheur.hshop.backendservice.exception.user.UserNotFoundException
import net.philipheur.hshop.backendservice.service.role.RoleService
import net.philipheur.hshop.backendservice.service.user.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val roleService: RoleService,
) {
    @GetMapping
    fun listAll(model: Model): String {
        val users = userService.findAll()
        for (user in users) {
            println("model $user")
        }
        model.addAttribute("users", users)
        return "users"
    }

    @GetMapping("/new")
    fun createUser(model: Model): String {

        val roles = roleService.findAll()
        val user = UserDto()

        user.enabled = true

        model.addAttribute("user", user)
        model.addAttribute("roles", roles)

        return "create_user_form"
    }

    @PostMapping("/save")
    fun saveUser(user: UserDto, redirectAttributes: RedirectAttributes): String {
        println(user)

        userService.save(user)

        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully")

        return "redirect:/users"
    }

    @GetMapping("/edit/{id}")
    fun editUser(
        @PathVariable id: Long,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            val user = userService.findById(id)
            val roles = roleService.findAll()

            model.addAttribute("user", user)
            model.addAttribute("roles", roles)
            return "edit_user_form"

        } catch (e: UserNotFoundException) {
            redirectAttributes.addFlashAttribute(
                "message", "cannot find the user with id:$id"
            )
            return "redirect:/users"
        }
    }
}