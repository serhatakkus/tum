package pgdp.searchengine.networking;

import java.util.*;

import pgdp.searchengine.networking.HTMLToken.TokenType;

public final class HTMLProcessing {

    // Useless constructor for SCA
    private HTMLProcessing() {

    }

    public static List<HTMLToken> tokenize(String rawHTML) {
        return null;
    }

    public static String[] filterLinks(List<HTMLToken> tokens, String host) {
        /*-
         * bu method 
         * ilk parametre olarak, tokenize sonucunda oluþan token listesini alacak
         * List<HTMLToken> bütün tokenlarý içeriyor.
         * ikinci parametre diðer sýnýflarda kullandýðýmýz host deðeri.
         * daha doðrusu içeriðini almak istediðimiz sitenin adresi.
         */
    	/*- bir listeyi döngü kullanmadan stream ile þöyle dolaþabiliyoruz:
    	 * tokens.stream();
    	 * stream in altýnda çeþitli methodlar var. ödevde de bahsediyor bunlardan.
    	 * mesela bu methodda filtreleme yapmalýyýz. bunun için büyük ihtimalle filter() kullanmamýzý istiyor.
    	 * filter'ýn ilk deðerini deðiþken ismi gibi düþün. 
    	 * ikinci kýsým (oktan sonrasý -> ) boolean sonuç veren bi expression.
    	 * bu kýsýmda filtreleme yaparken kullanman gerek koþulu yazman gerek.
    	 * mesela TokenType.TAG.equals(...) gibi...
    	 * 
    	 * sonuçta þuna benzer birþey çýkmalý. koþulu güncellemek gerekiyor. bende HTMLToken yazýlý deðil.
    	 * oradaki methodlarý kullanarak filtreleme yapmak gerekiyor.
    	 */
    	tokens.stream().filter(element -> TokenType.TAG.equals(element.getTokenType()) && element.getContentAsString().startsWith("a "));
    	/*-
    	 * bunun sonucunu da baþka Stream<String> nesnesine atabiliriz sanýrým. onun içinde de benzer þekilde Stream fonksiyonlarýyla link olan kýsýmlarý almak gerek.
    	 * bir Stream'in sonucunu baþka stream'e alan örnekler basitçe þurda var:
    	 * 
    	 * https://www.baeldung.com/java-8-streams-introduction
    	 * 
    	 * sonucunda da istediðimiz deðerleri içerecek þekilde oluþan stream'i array'e veya list'e þöyle dönüþtürebiliriz:
    	 * List<...> lst = stream()....... .collect(Collectors.toList());
    	 */
    	    	
    	return new String[0];
    }

    public static String filterText(List<HTMLToken> tokens) {
        /*-
         * kullanýlan parametre ve yöntem yukarýdaki ile ayný.
         * sadece koþullar ve belki stream.'dan sorna çaðrýlan method farklý olabilir.
         * burada da tokenType'ý text olan Token'lar filtrelenmeli.
         * sonrasýnda da sanýrým reduce() ile string birleþtirme yapýlabilir.
         * onun sonucu zaten String olacak ve return edilecek.
         */
    	return null;
    }

    public static String filterTitle(List<HTMLToken> tokens) {
        /*-
         * bu methodda stream methodlarýný kullanmayý zorunlu tutmuyor.
         * tek parametre yine tokenize sonucunda oluþan, bütün token'larý içeren liste.
         * eðer sayfanýn bir title'ý varsa, o listenin içinde "Tag: title" ve "Tag: /title" gibi iki tane token olmalý.
         * tokenType'ý TokenType.TAG, content'i "title" ve "/title" olur bunlarýn.
         * yapýlacak iþ þu:
         * basitçe bir döngü içinde, "title" ile "/title" tagleri arasýndaki TokenType.TEXT olan bütün tokenlarý string olarak birleþtirmek.
         * sonra bu stringi return etmek gerek.
         */
    	
    	return null;
    }

}
