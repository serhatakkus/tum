package pgdp.searchengine.pagerepository;

public class LinkedDocumentCollection extends DocumentCollection {

    public LinkedDocumentCollection(int numberOfBuckets) {
        super(numberOfBuckets);
    }
    
    @Override
    public boolean add(Document linkedDocument) {
        if (linkedDocument == null) {
            return false;
        }
        
        if (linkedDocument instanceof AbstractLinkedDocument ald) {
            if (ald.getAddress() == null) {
                return false;
            }
            
            return this.getBuckets()[this.indexFunction(ald.getAddress())].add(ald);
        }
        
        return false; 
    }
    
    private int indexFunction(String address) {
        if (address == null) {
            return -1;
        }
        return (address.hashCode() % this.getNumberOfBuckets() + this.getNumberOfBuckets()) % this.getNumberOfBuckets();
    }
    
    public AbstractLinkedDocument find(String address) {
        if (address == null) {
            return null;
        }
        
        Bucket bucket = this.getBuckets()[this.indexFunction(address)];
        
        if (bucket.getHead() == null) {
            return null;
        }
        
        DocumentListElement dle = bucket.getHead();
        
        while (dle != null) {
            if (dle.getDocument() instanceof AbstractLinkedDocument ald && ald.getAddress().equals(address)) {
                return ald;
            }
            dle = dle.getNext();
        }
        return null;
    }
    
    public boolean removeDummy(DummyLinkedDocument dld) {
        if (dld == null || dld.getAddress() == null) {
            return false;
        }

        int bucketIndex = indexFunction(dld.getAddress());

        DocumentListElement listElementToRemove = this.getBuckets()[bucketIndex].find(dld.getDocumentId());
        return this.getBuckets()[bucketIndex].remove(listElementToRemove);
    }
    
    public boolean contains(AbstractLinkedDocument ald) {
        if (ald == null) {
            return false;
        }
        return find(ald.getAddress()) != null;
    }  
    
    public boolean addToResultCollection(AbstractLinkedDocument abstractLinkedDocument) {
        if (abstractLinkedDocument == null) {
            return false;
        }
        
        if (find(abstractLinkedDocument.getAddress()) instanceof LinkedDocument) {
            return false;
        }
        
        this.updateOutgoingLinks(abstractLinkedDocument);
        if (abstractLinkedDocument instanceof LinkedDocument) {
            this.updateIncomingLinks((LinkedDocument) abstractLinkedDocument, ((LinkedDocument) abstractLinkedDocument).getOutgoingAddresses());
        }
        return this.add(abstractLinkedDocument);
    }
    
    private void updateIncomingLinks(LinkedDocument linkedDocument, String[] outgoingAddresses) {
        if (linkedDocument == null || outgoingAddresses == null) {
            return;
        }
        
        Bucket[] buckets = this.getBuckets();
        boolean contains;
        
        for (int i = 0; i < outgoingAddresses.length; i++) {
            if (outgoingAddresses[i].equals(linkedDocument.getAddress())) {
                continue;
            }
            contains = false;
            for (int j = 0; j < buckets.length; j++) {
                DocumentListElement dle = buckets[j].getHead();               
                while (dle != null) {
                    if (dle.getDocument() instanceof AbstractLinkedDocument ld && ld.getAddress().equals(outgoingAddresses[i])) {
                        linkedDocument.addOutgoingLink(ld);
                        ld.addIncomingLink(linkedDocument);
                        contains = true;
                        break;
                    }
                    dle = dle.getNext();
                }
                if (contains) {
                    break;
                }
            }
            if (!contains) {
                DummyLinkedDocument dummy = new DummyLinkedDocument(outgoingAddresses[i], this.getNumberOfBuckets());
                dummy.addIncomingLink(linkedDocument);
                linkedDocument.addOutgoingLink(dummy);
                this.add(dummy);
            }
        }
    }
    
    private void updateOutgoingLinks(AbstractLinkedDocument abstractLinkedDocument) {    
        if (abstractLinkedDocument == null) {
            return;
        }

        if (this.isEmpty()) {
            return;
        }
        
        LinkedDocumentCollection incomingLinksDummy;
        
        AbstractLinkedDocument ald = this.find(abstractLinkedDocument.getAddress());
        
        if (ald != null && ald.getAddress().equals(abstractLinkedDocument.getAddress())) {
            incomingLinksDummy = ald.getIncomingLinks();
            Bucket[] bucketsIncoming = incomingLinksDummy.getBuckets();
            for (int j = 0; j < bucketsIncoming.length; j++) {
                DocumentListElement dleIncoming = bucketsIncoming[j].getHead();
                while (dleIncoming != null) {
                    abstractLinkedDocument.addIncomingLink((LinkedDocument) dleIncoming.getDocument());
                    ((LinkedDocument) dleIncoming.getDocument()).addOutgoingLink(abstractLinkedDocument);
                    dleIncoming = dleIncoming.getNext();
                }
            }
            this.removeDummy((DummyLinkedDocument) ald);
        }
    }
}