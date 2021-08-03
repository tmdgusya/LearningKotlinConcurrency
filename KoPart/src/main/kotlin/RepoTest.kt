import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class RepoTest {

    companion object {
        @JvmStatic fun main(args: Array<String>) = runBlocking {
            val client: ProfileServiceRepository = ProfileService()
            val profile = client.findById(21);
            print(profile)
        }
    }

}