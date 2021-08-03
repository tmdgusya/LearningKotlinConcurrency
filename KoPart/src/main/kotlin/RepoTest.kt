import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class RepoTest {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val client: ProfileServiceRepository = ProfileService()

            runBlocking {
                val profile = client.asyncFetchById(21).await();
                print(profile)
            }
        }

    }

}