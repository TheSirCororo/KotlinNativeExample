fun main(args: Array<String>) {
    val times = mutableListOf<Long>()
    repeat(3) { i ->
        val start = System.currentTimeMillis()
        val page = WebPage(args.let { if (it.isEmpty()) "https://www.google.com/" else args[0] })
        println(page.apply { load() })
        val end = System.currentTimeMillis()
        val time = end - start
        println("${i + 1}) completed in ${time}ms")
        times.add(time)
    }
    println("All completed, average time is ${times.sum() / times.size}ms")
}