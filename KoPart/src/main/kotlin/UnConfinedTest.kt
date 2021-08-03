import kotlinx.coroutines.*

class UnConfinedTest {

    companion object {
        @JvmStatic fun main(args: Array<String>) = runBlocking {
            GlobalScope.launch(Dispatchers.Unconfined) {
                println(Thread.currentThread().name)
                delay(500)
                println(Thread.currentThread().name)
                delay(500)
                println(Thread.currentThread().name)
            }.join()
        }
    }

}