package net.philipheur.hshop.settingsservice.domain.service.mapper

import net.philipheur.hshop.settingsservice.domain.core.entity.Currency
import net.philipheur.hshop.settingsservice.domain.core.entity.Setting
import net.philipheur.hshop.settingsservice.domain.core.valueobject.SettingsCategory
import net.philipheur.hshop.settingsservice.domain.service.dto.CurrencyDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SettingDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class SettingsDomainServiceMapper {

    fun transformSettingDomainToDto(
        setting: Setting
    ) = SettingDto(
        id = setting.id!!.toString(),
        key = setting.key,
        value = setting.value,
        category = setting.category.name
    )

    fun transformSettingDtoToDomain(
        settingDto: SettingDto
    ) = Setting(
        id = UUID.fromString(settingDto.id),
        key = settingDto.key,
        value = settingDto.value,
        category = SettingsCategory.valueOf(settingDto.category)
    )

//        return CategoryDto(
//            id = settings.id!!.value,
//            name = settings.name,
//            alias = settings.alias,
//            image = settings.image,
//            parentCategory = if (settings.parentCategory != null)
//                transformCategoryDomainToDto(settings.parentCategory!!) else null,
//            status = settings.status,
//            createdAt = settings.createdAt,
//            subCategories = settings.subCategories!!.map {
//                transformCategoryDomainToDto(it)
//            }
//        )
//    }
//
//    fun transformBrandDomainToDto(
//        brand: Currency
//    ) = CurrencyDto(
//        id = brand.id!!.value,
//        name = brand.name,
//        logo = brand.logo,
//        categories = brand.categories.map {
//            transformCategoryDomainToDto(it)
//        }
//    )
//
//    fun transformProductDomainToDto(
//        product: Setting
//    ) = SettingDto(
//        id = product.id!!.value,
//        name = product.name,
//        alias = product.alias,
//        shortDescription = product.shortDescription,
//        fullDescription = product.fullDescription,
//        status = product.status,
//        mainImage = product.mainImage,
//        discountPercent = product.discountPercent,
//        price = product.price.amount,
//        cost = product.cost.amount,
//        dimension = product.dimension,
//        brand = transformBrandDomainToDto(product.brand),
//        settings = transformCategoryDomainToDto(product.settings),
//        details = product.details,
//        detailImages = product.detailImages,
//        createdAt = product.createdAt,
//        updatedAt = product.updatedAt
//    )
}
