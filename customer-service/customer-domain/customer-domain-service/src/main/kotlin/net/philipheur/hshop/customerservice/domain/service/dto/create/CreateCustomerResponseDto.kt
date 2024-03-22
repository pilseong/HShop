package net.philipheur.hshop.customerservice.domain.service.dto.create

import jakarta.validation.constraints.NotNull
import java.util.*

data class CreateCustomerResponseDto(
    @NotNull val customerId: UUID,
    @NotNull val message: String
)