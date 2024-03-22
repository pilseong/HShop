package net.philipheur.hshop.cartservice

import net.philipheur.hshop.cartservice.domain.core.CartDomainServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class BeanConfiguration {
    @Bean
    open fun cartDomainService() = CartDomainServiceImpl()
}