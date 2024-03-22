package net.philipheur.hshop.settingsservice.dataaccess.settings.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

import java.util.UUID

@Entity
@Table(name = "states", schema = "setting")
class StateEntity(
    @Id
    var id: UUID,

    @Column(nullable = false, length = 60)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "country_id")
    var country: CountryEntity

)