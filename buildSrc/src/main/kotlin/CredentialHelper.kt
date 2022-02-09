import java.io.File
import java.io.FileInputStream
import java.util.*

object CredentialHelper {

    const val KEY_USER_AGENT = "USER_AGENT"

    const val KEY_OAUTH_TOKEN = "OAUTH_TOKEN"

    fun getCredentialProperties(rootDir: String) = Properties().apply {
        load(FileInputStream(File("$rootDir/credential.properties")))
    }
}