package net.philipheur.hshop.common.domain.dtos.settings

class PageResponse<T> (
    val content: List<T>,
    val pageNo: Int,
    val pageSize: Int,
    val totalElements: Long,
    val totalPages: Int,
    val last: Boolean,
)