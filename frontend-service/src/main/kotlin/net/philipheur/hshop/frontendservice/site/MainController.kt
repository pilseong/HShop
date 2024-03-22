package net.philipheur.hshop.frontendservice.site

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping
    fun viewHomePage(): String {
        return "index"
    }
}