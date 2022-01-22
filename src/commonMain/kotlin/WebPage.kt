/**
 * Web page class
 */
expect class WebPage {
    /**
     * Link that will be used in `load` to get header and body
     */
    val link: String

    /**
     * HTTP headers
     */
    var header: String?
        private set

    /**
     * HTTP body
     */
    var body: String?
        private set

    /**
     * Load web page function
     * This function set header and body to not-null if loading is successful
     * @return HTTP response
     */
    fun load(): Response
}