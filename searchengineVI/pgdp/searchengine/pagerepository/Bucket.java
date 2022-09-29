package pgdp.searchengine.pagerepository;

import pgdp.searchengine.util.WordCount;

public class Bucket {
    private DocumentListElement head;
    private DocumentListElement tail;
    private int size;
    
    public Bucket() {
        this.head = null;
        this.tail = null;
    }
    
    public boolean add(Document document) {
        if (document == null || document.getDocumentId() < 0) {
            return false;
        }
        
        if (this.head == null) {
            this.head = new DocumentListElement(document);
            this.tail = this.head;
            size++;
            return true;
        }
        else if (this.head.getDocumentId() > document.getDocumentId()) {
            DocumentListElement newListElement = new DocumentListElement(document);
            newListElement.setNext(this.head);
            this.head.setPre(newListElement);
            this.head = newListElement;
            size++;
            return true;
        }
        else if (this.head.getDocumentId() == document.getDocumentId()) {
            return false;
        }
        else {
            DocumentListElement currentListElement = this.head;
            while(currentListElement.getNext() != null && currentListElement.getNext().getDocumentId() < document.getDocumentId()) {
                if (currentListElement.getNext().getDocumentId() == document.getDocumentId()) {
                    return false;
                }
                currentListElement = currentListElement.getNext();
            }
            
            if (currentListElement.getNext() != null && currentListElement.getNext().getDocumentId() == document.getDocumentId()) {
                return false;
            }
            
            DocumentListElement newListElement = new DocumentListElement(document, currentListElement, currentListElement.getNext());
            
            if (currentListElement.getNext() == null) {
                this.tail = newListElement;
            }
            else {
                currentListElement.getNext().setPre(newListElement);
            }
            currentListElement.setNext(newListElement);
            size++;
            return true;
        }
    }
    
    public boolean remove(DocumentListElement listElementToRemove) {
        if (listElementToRemove == null) {
            return false;
        }
        
        if (listElementToRemove.getPre() == null) {
            this.head = listElementToRemove.getNext();
        }
        else {
            listElementToRemove.getPre().setNext(listElementToRemove.getNext());
        }
        
        if (listElementToRemove.getNext() == null) {
            this.tail = listElementToRemove.getPre();
        }
        else {
            listElementToRemove.getNext().setPre(listElementToRemove.getPre());
        }      
        
        size--;
        return true;
    }
    
    public DocumentListElement find(int documentId) {
        if (documentId < 0) {
            return null;
        }
                
        if (this.head == null) {
            return null;
        }
        
        DocumentListElement currentListElement = this.head;
        while (currentListElement.getDocumentId() != documentId) {
            if (currentListElement.getNext() == null) {
                return null;
            }
            currentListElement = currentListElement.getNext();
        }
        return currentListElement;
        
    }
    
    public boolean swapListElements(DocumentListElement first, DocumentListElement second) {
        if (first == null || second == null) {
            return false;
        }
        Document tmp = first.getDocument();
        first.setDocument(second.getDocument());
        second.setDocument(tmp);
        
        WordCount[] tmpWordCount = first.getWordCountArray();
        first.setWordCountArray(second.getWordCountArray());
        second.setWordCountArray(tmpWordCount);
        
        double tmpSim = first.getSimilarity();
        first.setSimilarity(second.getSimilarity());
        second.setSimilarity(tmpSim);
        
        return true;
    }

    public DocumentListElement getHead() {
        return head;
    }

    public void setHead(DocumentListElement head) {
        this.head = head;
    }

    public DocumentListElement getTail() {
        return tail;
    }

    public void setTail(DocumentListElement tail) {
        this.tail = tail;
    }
    
    public int size() {
        return this.size;
    }
}