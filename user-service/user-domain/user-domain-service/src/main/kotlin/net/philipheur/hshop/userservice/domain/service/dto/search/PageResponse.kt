package net.philipheur.hshop.userservice.domain.service.dto.search

class PageResponse<T> (
    val content: List<T>,
    val pageNo: Int,
    val pageSize: Int,
    val totalElements: Long,
    val totalPages: Int,
    val last: Boolean,
)