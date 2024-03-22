package net.philipheur.hshop.common.domain.entity

abstract class AggregateRoot<ID>(
    id: ID?
) : BaseEntity<ID>(id)
