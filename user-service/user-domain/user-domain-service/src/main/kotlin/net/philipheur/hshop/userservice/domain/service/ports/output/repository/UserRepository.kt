package net.philipheur.hshop.userservice.domain.service.ports.output.repository

import net.philipheur.hshop.userservice.domain.core.entity.User
import net.philipheur.hshop.userservice.domain.service.dto.search.PageResponse
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.UUID

interface UserRepository {
    fun findByEmail(email: String): User?
    fun findById(id: UUID): User?
    fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String,
    ): PageResponse<User>
    fun deleteById(id: UUID)
    fun save(user: User): User
    fun savePhotoFile(path: Path, filename: String, image: MultipartFile): String
    fun cleanPhotoFile(path: Path)
}