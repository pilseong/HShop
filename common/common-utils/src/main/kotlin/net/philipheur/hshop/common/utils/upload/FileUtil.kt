package net.philipheur.hshop.common.utils.upload

import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.util.DigestUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

object FileUtil {
    private val log by LoggerDelegator()
    fun saveFile(path: Path, filename: String, file: MultipartFile): String {

        if (!Files.exists(path)) {
            Files.createDirectories(path)
        }

        val fileTokens = filename.split('.')

        val inputStream: InputStream = file.inputStream
        var savedFilename = DigestUtils.md5DigestAsHex(inputStream)
        savedFilename += if (fileTokens.size > 1)
            ".${fileTokens[fileTokens.size - 1]}" else ""

        val filePath = path.resolve(savedFilename)

        // 이런 식으로 하지 않으면 저장되지 않음
        file.inputStream.use {
            Files.copy(it, filePath, StandardCopyOption.REPLACE_EXISTING)
        }

        return savedFilename
    }

    fun cleanFile(path: Path) {
        try {
            Files.list(path).forEach {
                if (!Files.isDirectory(it)) {
                    Files.delete(it)
                }
            }
        } catch (e: IOException) {
            log.error("error occurred while cleaning up user photo folder")
//            throw RuntimeException("error occurred while cleaning up user photo folder", e)
        }
    }
}