package pgdp.searchengine.pagerepository;

import pgdp.searchengine.util.Date;
import pgdp.searchengine.util.WordCount;

public class LinkedDocument extends AbstractLinkedDocument {  
    private final LinkedDocumentCollection outgoingLinks;

    public LinkedDocument(String title, String description, String content, Date releaseDate, Author author, String address, int collectionSize) {
        super(title, description, content, releaseDate, author, address, collectionSize);
        outgoingLinks = new LinkedDocumentCollection(collectionSize);
    }
    
    public String[] getOutgoingAddresses() {
        String[] words = this.getContent().split(" ");
        String[] addresses = new String[words.length];        
        int size = 0;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith("link::") && words[i].length() > 6) {
                addresses[size++] = words[i].substring(6);
            }
        }
        
        if (size != words.length) {
            String[] tmp = new String[size];
            for(int i = 0; i < tmp.length; i++) {
                tmp[i] = addresses[i];
            }
            addresses = tmp;
        }
        return addresses;
    }
    
    public boolean addOutgoingLink(AbstractLinkedDocument linkedDocument) {
        if (linkedDocument == null) {
            return false;
        }
        if (linkedDocument.getAddress().equals(this.getAddress())) {
            return false;
        }
        if (outgoingLinks.contains(linkedDocument) && !(outgoingLinks.find(linkedDocument.getAddress()) instanceof DummyLinkedDocument)) {
            return false;
        }
        if (outgoingLinks.contains(linkedDocument) && outgoingLinks.find(linkedDocument.getAddress()) instanceof DummyLinkedDocument dld) {
            outgoingLinks.removeDummy(dld);
        }
        outgoingLinks.add(linkedDocument);
        return true;
    }   
    
    @Override
    public WordCount[] getWordCountArray() {
        WordCount[] returnedWC = super.getWordCountArray();
        int size = returnedWC.length;
        for (int i = 0; i < returnedWC.length; i++) {
            if (returnedWC[i].getWord().startsWith("link::")) {
                returnedWC[i] = null;
                size--;
            }
        }
        
        WordCount[] withoutLinks = new WordCount[size];
        size = 0;
        for (int i = 0; i < returnedWC.length; i++) {
            if (returnedWC[i] != null) {
                withoutLinks[size] = returnedWC[i];
                size++;
            }
        }
        
        return withoutLinks;
    }

    public LinkedDocumentCollection getOutgoingLinks() {
        return outgoingLinks;
    }    
}