package net.philipheur.hshop.customerservice.domain.service.dto.create

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class CreateCustomerCommand(
    @field:NotEmpty
    val email: String,
    @field:NotEmpty
    val firstName: String,
    @field:NotNull
    val lastName: String,
    @field:NotEmpty
    val password: String,
    val phoneNumber: String?,

)