package pgdp.searchengine.pagerepository;
import pgdp.searchengine.util.Date;

public abstract class AbstractLinkedDocument extends Document {
    private final String address;
    private final LinkedDocumentCollection incomingLinks;

    public AbstractLinkedDocument(String title, String description, String content, Date releaseDate, Author author, String address, int collectionSize) {
        super(title, description, content, releaseDate, author);
        this.address = address;
        incomingLinks = new LinkedDocumentCollection(collectionSize);
    }
    
    public boolean addIncomingLink(AbstractLinkedDocument linkedDocument) {
        if (linkedDocument == null) {
            return false;
        }
        if (linkedDocument.getAddress().equals(this.getAddress())) {
            return false;
        }
        if (incomingLinks.contains(linkedDocument)) {
            return false;
        }
        return incomingLinks.add(linkedDocument);
    }
    
    public String getAddress() {
        return address;
    }

    public LinkedDocumentCollection getIncomingLinks() {
        return incomingLinks;
    }

}