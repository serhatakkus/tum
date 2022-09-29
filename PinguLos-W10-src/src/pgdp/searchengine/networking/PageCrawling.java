package pgdp.searchengine.networking;

import pgdp.searchengine.pagerepository.LinkedDocumentCollection;

public final class PageCrawling {

    // Another useless constructor for SCA
    private PageCrawling() {

    }

    public static void crawlPages(LinkedDocumentCollection collection, int number) {

    }

    public static void crawlPages(LinkedDocumentCollection collection, int number, String startingAddress) {

    }

    public static boolean crawlPage(LinkedDocumentCollection collection, String address) {
        return false;
    }

    // -------- main() zum Testen -------- //

    public static void main(String... args) {
        String host = "man7.pgdp.sse.in.tum.de";
        String path = "iso_8859_1.7.html";

        LinkedDocumentCollection ldc = new LinkedDocumentCollection(1000);
        crawlPages(ldc, 5, host + "/" + path);
    }

}
