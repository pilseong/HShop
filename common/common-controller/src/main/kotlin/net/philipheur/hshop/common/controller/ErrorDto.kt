package net.philipheur.hshop.common.controller

data class ErrorDto(
    val code: String,
    val message: String,
    val details: String?
)