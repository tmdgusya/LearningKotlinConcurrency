import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class Main {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            runBlocking {
                greetDelayed(1000);
            }
        }

        suspend fun greetDelayed(delayMills: Long) {
            delay(delayMills);
            println("Hello World");
        }
    }


}