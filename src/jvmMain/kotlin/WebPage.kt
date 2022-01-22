import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

actual class WebPage(actual val link: String) {
    var header: String? = null
    var body: String? = null

    actual fun load(): Response {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder().GET().uri(URI.create(link)).version(HttpClient.Version.HTTP_2).build()
        val response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join()
        val body = response.body()
        val headers = response.headers().map()
        this.body = body
        this.header = headers.entries.joinToString { "${it.key}: ${it.value}" }
        return Response(-1, header!!, body, response.statusCode(), HttpVersion.valueOf(response.version().name))
    }

    override fun toString(): String {
        return "WebPage($link)\n${header ?: "<not loaded>"}\n${body ?: "not loaded"}"
    }
}