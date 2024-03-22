package net.philipheur.hshop.common.utils.fileupload

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import net.philipheur.hshop.common.utils.upload.FileUtil
import org.junit.jupiter.api.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.mock.web.MockMultipartFile
import java.io.ByteArrayInputStream
import java.nio.file.Files

class FileUtilTest {

    @Test
    fun saveFile() {
        val file = mock(MockMultipartFile::class.java)
        val data = byteArrayOf(1, 2, 3, 4)
        val stream = ByteArrayInputStream(data)

        `when`(file.inputStream).thenReturn(stream)
        val fileSystem = Jimfs.newFileSystem(Configuration.forCurrentPlatform())

        val savedFilename = FileUtil.saveFile(fileSystem.getPath("/test"), "a.aa.jpg", file)

        kotlin.test.assertTrue {
            println(savedFilename)
            Files.exists(fileSystem.getPath("/test").resolve(savedFilename))
        }
    }
}