import kotlinx.coroutines.*
import java.lang.RuntimeException

class ExceptionHandleTest {

    companion object {
        @JvmStatic fun main(args: Array<String>) = runBlocking {
            val dispatcher = newFixedThreadPoolContext(4, "roach")
            val handler = CoroutineExceptionHandler { context, throwable ->
                println("Error caputure in $context")
                println("Error msg : ${throwable.message}")
            }
            GlobalScope.launch(dispatcher + handler) {
                println(Thread.currentThread().name)
                throw RuntimeException("Error?!!?")
            }
            delay(500)
        }
    }

}