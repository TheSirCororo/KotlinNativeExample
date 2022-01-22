data class Response(
    val curlCode: Int,
    val header: String,
    val body: String,
    val httpCode: Int,
    val httpVersion: HttpVersion
)