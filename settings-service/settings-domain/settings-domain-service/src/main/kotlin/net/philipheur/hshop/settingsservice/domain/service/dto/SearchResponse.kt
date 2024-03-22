package net.philipheur.hshop.settingsservice.domain.service.dto

data class SearchResponse<T> (
    val totalElements: Long,
    val totalPages: Int,
    val pageNo: Int,
    val pageSize: Int,
    val last: Boolean,
    val content: List<T>
)