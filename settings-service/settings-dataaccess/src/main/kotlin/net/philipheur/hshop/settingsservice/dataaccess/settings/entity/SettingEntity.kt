package net.philipheur.hshop.settingsservice.dataaccess.settings.entity

import jakarta.persistence.*
import net.philipheur.hshop.settingsservice.domain.core.valueobject.SettingsCategory
import java.util.UUID

@Table(name = "settings", schema = "setting")
@Entity
class SettingEntity (
    @Id
    var id: UUID,

    @Column(nullable = false, length = 128)
    var key: String,

    @Column(nullable = false, length = 1024)
    var value: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    var category: SettingsCategory
)