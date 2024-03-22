package net.philipheur.hshop.customerservice

import net.philipheur.hshop.customerservice.domain.core.CustomerDomainServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class BeanConfiguration {
    @Bean
    open fun customerDomainService() = CustomerDomainServiceImpl()
}