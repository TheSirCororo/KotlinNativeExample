import platform.posix.*

fun main(args: Array<String>) {
    val times = mutableListOf<Double>()
    repeat(3) { i ->
        val start = clock()
        val page = WebPage(args.let { if (it.isEmpty()) "https://www.google.com/" else args[0] })
        println(page.apply { load() })
        val end = clock()
        val time = (end - start).toDouble() / CLOCKS_PER_SEC
        println("${i + 1}) completed in ${time}ms")
        times.add(time)
    }
    println("All completed, average time is ${times.sum() / times.size}ms")
}