package net.philipheur.hshop.userservice.dataaccess.user.adapter

import net.philipheur.hshop.common.utils.upload.FileUtil
import net.philipheur.hshop.userservice.dataaccess.role.mapper.UserDataaccessMapper
import net.philipheur.hshop.userservice.dataaccess.user.repository.UserJpaRepository
import net.philipheur.hshop.userservice.domain.core.entity.User
import net.philipheur.hshop.userservice.domain.core.exception.UserNotFoundException
import net.philipheur.hshop.userservice.domain.service.dto.search.PageResponse
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.*

@Service
class UserRepositoryAdapter(
    private val mapper: UserDataaccessMapper,
    private val repository: UserJpaRepository
) : UserRepository {
    override fun findByEmail(email: String): User? {
        val user = repository.findByEmail(email)

        return if (user != null) {
            mapper.transformUserEntityToDomain(user)
        } else null
    }

    override fun findById(id: UUID): User? {
        val userOptional = repository.findById(id)
        return if (userOptional.isPresent) {
            val user = userOptional.get()
            mapper.transformUserEntityToDomain(user)
        } else null
    }

    override fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String
    ): PageResponse<User> {
        val users = repository.findAllWithKeyword(
            keyword = keyword,
            pageable = pageRequest(
                pageNo = pageNo,
                pageSize = pageSize,
                sortBy = sortBy,
                orderBy = orderBy,
            )
        )

        return PageResponse<User>(
            content = users.map {
                mapper.transformUserEntityToDomain(it)
            }.toList(),
            pageNo = users.number,
            pageSize = users.size,
            totalElements = users.totalElements,
            totalPages = users.totalPages,
            last = users.isLast
        )
    }

    override fun deleteById(id: UUID) {
        val userOptional = repository.findById(id)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            repository.delete(user)
        } else {
            throw UserNotFoundException("User with id $id not found")
        }
    }

    override fun save(user: User): User {
        val entity = repository.save(mapper.transformUserDomainToEntity(user))

        return mapper.transformUserEntityToDomain(entity)
    }

    override fun savePhotoFile(path: Path, filename: String, image: MultipartFile): String {
        return FileUtil.saveFile(
            path = path,
            filename = filename,
            file = image
        )
    }

    override fun cleanPhotoFile(path: Path) {
        FileUtil.cleanFile(path)
    }

    private fun pageRequest(
        pageNo: Int,
        pageSize: Int,
        orderBy: String,
        sortBy: String
    ) = PageRequest.of(
        pageNo,
        pageSize,
        if (orderBy.equals(
                Sort.Direction.ASC.name, true
            )
        ) Sort.by(sortBy).ascending()
        else Sort.by(sortBy).descending()
    )
}