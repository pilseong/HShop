package net.philipheur.hshop.customerservice.domain.core.valueobject


import java.util.*

class CustomerAddress (
    var id: UUID,
    var address1: String,
    var address2: String,
    var city: String,
    var state: CustomerState,
    var country: CustomerCountry,
    var postalCode: String,
)