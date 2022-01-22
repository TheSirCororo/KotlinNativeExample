import curl.*
import kotlinx.cinterop.*
import platform.posix.size_t
import platform.posix.strcat

actual data class WebPage(actual val link: String) {

    actual fun load(): Response = memScoped {
        val readBuffer = allocArray<ByteVar>(64 * 1024)
        val headerBuffer = allocArray<ByteVar>(16 * 1024)
        val curl = curl_easy_init()
        curl_easy_setopt(curl, CURLOPT_URL, link)
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, staticCFunction(::writeCallback))
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, readBuffer)
        curl_easy_setopt(curl, CURLOPT_HEADERDATA, headerBuffer)
        curl_easy_setopt(curl, CURLOPT_HEADERFUNCTION, staticCFunction(::writeCallback))
        curl_easy_setopt(curl, CURLOPT_USE_SSL, 1)
        curl_easy_setopt(curl, CURLOPT_CAINFO, "cacert.pem")
        val curlResponse = curl_easy_perform(curl)
        curl_easy_cleanup(curl)
        val header = headerBuffer.toKString()
        val body = readBuffer.toKString()
        val headerSplittedLines = header.split("\n")
        val versionAndCode = headerSplittedLines[0].split(" ")
        val version = versionAndCode[0]
        val code = versionAndCode[1].toInt()
        Response(curlResponse.toInt(), header, body, code, HttpVersion.fromString(version) ?: HttpVersion.HTTP_1_1)
    }
}

fun CPointer<ByteVar>.toKString(length: Int): String {
    val bytes = this.readBytes(length)
    return bytes.decodeToString()
}

fun writeCallback(buffer: CPointer<ByteVar>?, size: size_t, ntimes: size_t, userdata: COpaquePointer?): size_t {
    val realsize = size * ntimes
    if (userdata != null && buffer != null) {
        strcat(userdata.reinterpret(), buffer.toKString((size * ntimes).toInt()))
    }
    return realsize
}