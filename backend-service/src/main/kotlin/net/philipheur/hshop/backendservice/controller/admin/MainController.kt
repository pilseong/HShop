package net.philipheur.hshop.backendservice.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping
    fun viewHomePage(): String {
        return "index"
    }
}