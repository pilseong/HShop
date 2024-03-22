package net.philipheur.hshop.cartservice.dataaccess.repository

import net.philipheur.hshop.cartservice.dataaccess.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface CustomerJpaRepository: JpaRepository<CustomerEntity, UUID>