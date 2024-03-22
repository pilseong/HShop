package net.philipheur.hshop.catalogservice.domain.service.mapper

import net.philipheur.hshop.catalogservice.domain.core.entity.Brand
import net.philipheur.hshop.catalogservice.domain.core.entity.Category
import net.philipheur.hshop.catalogservice.domain.core.entity.Product
import net.philipheur.hshop.catalogservice.domain.service.dto.BrandDto
import net.philipheur.hshop.catalogservice.domain.service.dto.CategoryDto
import net.philipheur.hshop.catalogservice.domain.service.dto.InventoryDto
import net.philipheur.hshop.catalogservice.domain.service.dto.ProductDto
import net.philipheur.hshop.common.domain.dtos.settings.SettingDto
import org.springframework.stereotype.Component
import java.text.NumberFormat
import java.util.*

@Component
class CatalogDomainServiceMapper {

    fun transformCategoryDomainToDto(
        category: Category
    ): CategoryDto {
        return CategoryDto(
            id = category.id!!.value,
            name = category.name,
            alias = category.alias,
            image = category.image,
            parentCategory = if (category.parentCategory != null)
                transformCategoryDomainToDto(category.parentCategory!!) else null,
            status = category.status,
            createdAt = category.createdAt,
            subCategories = category.subCategories!!.map {
                transformCategoryDomainToDto(it)
            }
        )
    }


    fun transformBrandDomainToDto(
        brand: Brand
    ) = BrandDto(
        id = brand.id!!.value,
        name = brand.name,
        logo = brand.logo,
        categories = brand.categories.map {
            transformCategoryDomainToDto(it)
        }
    )

    fun transformProductDomainToDto(
        product: Product,
        settings: List<SettingDto>
    ):ProductDto {

        val currency = settings
            .first { it.key == "CURRENCY" }.value
            .split(',')
        val formatter = NumberFormat
            .getCurrencyInstance(Locale(currency[0], currency[1]))

        return ProductDto(
            id = product.id!!.value,
            name = product.name,
            shortName = product.shortName,
            alias = product.alias,
            shortDescription = product.shortDescription,
            fullDescription = product.fullDescription,
            status = product.status,
            mainImage = product.mainImage,
            discountPercent = product.discountPercent,
            price = product.price.amount,
            cost = product.cost.amount,
            dimension = product.dimension,
            brand = transformBrandDomainToDto(product.brand),
            category = transformCategoryDomainToDto(product.category),
            details = product.details,
            detailImages = product.detailImages,
            inventory = InventoryDto(
                id = product.inventory!!.id!!.value,
                productId = product.id!!.value,
                amount = product.inventory!!.amount

            ),
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
            displayPrice = formatter.format(product.price.amount),
            discountPrice = formatter.format(
                product.discountPrice.amount
            )
        )
    }
}
