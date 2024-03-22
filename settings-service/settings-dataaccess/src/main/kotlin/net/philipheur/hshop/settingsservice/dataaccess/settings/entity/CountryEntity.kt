package net.philipheur.hshop.settingsservice.dataaccess.settings.entity

import jakarta.persistence.*
import java.util.UUID
import javax.swing.plaf.nimbus.State

@Entity
@Table(name = "countries", schema = "setting")
class CountryEntity(
    @Id
    var id: UUID,

    @Column(nullable = false, length = 50)
    var name: String,

    @Column(nullable = false, length = 5)
    var code: String,

    @OneToMany(
        mappedBy = "country",
        fetch = FetchType.EAGER,
    )
    @OrderBy("name")
    var states: Set<StateEntity>
)