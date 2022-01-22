/**
 * HTTP version class
 * @param headerName version name in HTTP headers
 */
enum class HttpVersion(val headerName: String) {
    /**
     * HTTP/2
     */
    HTTP_2("HTTP/2"),

    /**
     * HTTP/1.1
     */
    HTTP_1_1("HTTP/1.1");

    companion object {
        /**
         * Get HttpVersion by header name
         */
        fun fromString(s: String) = values().find { it.headerName == s }
    }
}