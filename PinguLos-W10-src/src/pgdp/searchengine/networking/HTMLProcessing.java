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
         * ilk parametre olarak, tokenize sonucunda olu�an token listesini alacak
         * List<HTMLToken> b�t�n tokenlar� i�eriyor.
         * ikinci parametre di�er s�n�flarda kulland���m�z host de�eri.
         * daha do�rusu i�eri�ini almak istedi�imiz sitenin adresi.
         */
    	/*- bir listeyi d�ng� kullanmadan stream ile ��yle dola�abiliyoruz:
    	 * tokens.stream();
    	 * stream in alt�nda �e�itli methodlar var. �devde de bahsediyor bunlardan.
    	 * mesela bu methodda filtreleme yapmal�y�z. bunun i�in b�y�k ihtimalle filter() kullanmam�z� istiyor.
    	 * filter'�n ilk de�erini de�i�ken ismi gibi d���n. 
    	 * ikinci k�s�m (oktan sonras� -> ) boolean sonu� veren bi expression.
    	 * bu k�s�mda filtreleme yaparken kullanman gerek ko�ulu yazman gerek.
    	 * mesela TokenType.TAG.equals(...) gibi...
    	 * 
    	 * sonu�ta �una benzer bir�ey ��kmal�. ko�ulu g�ncellemek gerekiyor. bende HTMLToken yaz�l� de�il.
    	 * oradaki methodlar� kullanarak filtreleme yapmak gerekiyor.
    	 */
    	tokens.stream().filter(element -> TokenType.TAG.equals(element.getTokenType()) && element.getContentAsString().startsWith("a "));
    	/*-
    	 * bunun sonucunu da ba�ka Stream<String> nesnesine atabiliriz san�r�m. onun i�inde de benzer �ekilde Stream fonksiyonlar�yla link olan k�s�mlar� almak gerek.
    	 * bir Stream'in sonucunu ba�ka stream'e alan �rnekler basit�e �urda var:
    	 * 
    	 * https://www.baeldung.com/java-8-streams-introduction
    	 * 
    	 * sonucunda da istedi�imiz de�erleri i�erecek �ekilde olu�an stream'i array'e veya list'e ��yle d�n��t�rebiliriz:
    	 * List<...> lst = stream()....... .collect(Collectors.toList());
    	 */
    	    	
    	return new String[0];
    }

    public static String filterText(List<HTMLToken> tokens) {
        /*-
         * kullan�lan parametre ve y�ntem yukar�daki ile ayn�.
         * sadece ko�ullar ve belki stream.'dan sorna �a�r�lan method farkl� olabilir.
         * burada da tokenType'� text olan Token'lar filtrelenmeli.
         * sonras�nda da san�r�m reduce() ile string birle�tirme yap�labilir.
         * onun sonucu zaten String olacak ve return edilecek.
         */
    	return null;
    }

    public static String filterTitle(List<HTMLToken> tokens) {
        /*-
         * bu methodda stream methodlar�n� kullanmay� zorunlu tutmuyor.
         * tek parametre yine tokenize sonucunda olu�an, b�t�n token'lar� i�eren liste.
         * e�er sayfan�n bir title'� varsa, o listenin i�inde "Tag: title" ve "Tag: /title" gibi iki tane token olmal�.
         * tokenType'� TokenType.TAG, content'i "title" ve "/title" olur bunlar�n.
         * yap�lacak i� �u:
         * basit�e bir d�ng� i�inde, "title" ile "/title" tagleri aras�ndaki TokenType.TEXT olan b�t�n tokenlar� string olarak birle�tirmek.
         * sonra bu stringi return etmek gerek.
         */
    	
    	return null;
    }

}
