import kotlinx.coroutines.*

class ThreadPoolTest {

    companion object {
        @JvmStatic fun main(args: Array<String>) = runBlocking {
            val dispatcher = newFixedThreadPoolContext(4, "roach")
            GlobalScope.launch(dispatcher) {
                println(Thread.currentThread().name)
                delay(500)
                println(Thread.currentThread().name)
                delay(500)
                println(Thread.currentThread().name)
            }.join()
        }
    }

}