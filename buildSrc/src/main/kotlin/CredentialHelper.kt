import java.io.File
import java.io.FileInputStream
import java.util.*

object CredentialHelper {

    const val KEY_USER_AGENT = "USER_AGENT"

    const val KEY_OAUTH_TOKEN = "OAUTH_TOKEN"

    private val properties by lazy {
        Properties().apply {
            load(FileInputStream(File("credential.properties")))
        }
    }

    fun getValue(key: String): String {
        return properties.getProperty(key)
    }
}