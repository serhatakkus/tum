package pgdp.searchengine.pagerepository;

public class DocumentListElement {
    private Document document;
    private DocumentListElement pre;
    private DocumentListElement next;
    
    public DocumentListElement(Document document) {
        this.document = document;
        this.pre = null;
        this.next = null;
    }
    
    public DocumentListElement(Document document, DocumentListElement pre) {
        this.document = document;
        this.pre = pre;
        this.next = null;
    }
    
    public DocumentListElement(Document document, DocumentListElement pre, DocumentListElement next) {
        this.document = document;
        this.pre = pre;
        this.next = next;
    }
    
    public int getDocumentId() {
        return document.getDocumentId();
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public DocumentListElement getPre() {
        return pre;
    }

    public void setPre(DocumentListElement pre) {
        this.pre = pre;
    }

    public DocumentListElement getNext() {
        return next;
    }

    public void setNext(DocumentListElement next) {
        this.next = next;
    }
}