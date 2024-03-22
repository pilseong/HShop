package net.philipheur.hshop.settingsservice.dataaccess.settings.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "currencies", schema = "setting")
class CurrencyEntity(
    @Id
    var id: UUID,

    @Column(nullable = false, length = 64)
    var name: String,

    @Column(nullable = false, length = 3)
    var symbol: String,

    @Column(nullable = false, length = 4)
    var code: String,

    @Column(nullable = false, length = 10)
    var language: String,

    @Column(nullable = false, length = 5)
    var country: String
)