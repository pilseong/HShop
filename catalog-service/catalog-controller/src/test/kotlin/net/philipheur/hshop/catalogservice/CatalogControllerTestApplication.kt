package net.philipheur.hshop.catalogservice

import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.BrandService
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.CategoryService
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.ProductService
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.BrandRepository
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.CategoryRepository
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.ProductRepository
import net.philipheur.hshop.catalogservice.exchange.SettingsServiceClient
import org.mockito.Mockito
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(
    scanBasePackages = [
        "net.philipheur.hshop"
    ]
)
open class CatalogControllerTestApplication {
    @Bean
    open fun brandService(): BrandService {
        return Mockito.mock(BrandService::class.java)
    }

    @Bean
    open fun categoryService(): CategoryService {
        return Mockito.mock(CategoryService::class.java)
    }

    @Bean
    open fun productService(): ProductService {
        return Mockito.mock(ProductService::class.java)
    }

    @Bean
    open fun settingsServiceClient(): SettingsServiceClient {
        return Mockito.mock(SettingsServiceClient::class.java)
    }

    @Bean
    open fun brandRepository(): BrandRepository {
        return Mockito.mock(BrandRepository::class.java)
    }

    @Bean
    open fun productRepository(): ProductRepository {
        return Mockito.mock(ProductRepository::class.java)
    }

    @Bean
    open fun categoryRepository(): CategoryRepository {
        return Mockito.mock(CategoryRepository::class.java)
    }
}

fun main() {
    runApplication<SecurityProperties.User>()
}