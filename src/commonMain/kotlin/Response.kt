/**
 * Class that contains response after request
 * @param curlCode returned curl code (-1 on jvm target)
 * @param header http headers
 * @param body http body
 * @param httpCode HTTP code
 * @param httpVersion (HTTP/2 always now)
*/
data class Response(
    val curlCode: Int,
    val header: String,
    val body: String,
    val httpCode: Int,
    val httpVersion: HttpVersion
)