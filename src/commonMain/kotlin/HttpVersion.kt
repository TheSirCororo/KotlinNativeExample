enum class HttpVersion(val headerName: String) {
    HTTP_2("HTTP/2"),
    HTTP_1_1("HTTP/1.1");

    companion object {
        fun fromString(s: String) = values().find { it.headerName == s }
    }
}