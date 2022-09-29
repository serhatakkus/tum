package pgdp.searchengine.pagerepository;

import pgdp.searchengine.util.Date;

public class DocumentCollection {
    private final int numberOfBuckets;
    private final Bucket[] buckets;

    public DocumentCollection(int numberOfBuckets) {
        this.numberOfBuckets = numberOfBuckets > 0 ? numberOfBuckets : 1;
        buckets = new Bucket[this.numberOfBuckets];
        for (int i = 0; i < this.numberOfBuckets; ++i) {
            buckets[i] = new Bucket();
        }
    }

    public boolean add(Document document) {
        if (document == null || document.getDocumentId() < 0) {
            return false;
        }

        int bucketIndex = indexFunction(document.getDocumentId());

        return buckets[bucketIndex].add(document);
    }

    public Document find(int documentId) {
        if (documentId < 0) {
            return null;
        }

        int bucketIndex = indexFunction(documentId);

        DocumentListElement foundListElement = buckets[bucketIndex].find(documentId);

        return foundListElement != null ? foundListElement.getDocument() : null;
    }

    public boolean removeDocument(int documentId) {
        if (documentId < 0) {
            return false;
        }

        int bucketIndex = indexFunction(documentId);

        DocumentListElement listElementToRemove = buckets[bucketIndex].find(documentId);

        return buckets[bucketIndex].remove(listElementToRemove);
    }

    public boolean removeDocumentsFromAuthor(Author author) {
        if (author == null) {
            return false;
        }

        DocumentListElement currentListElement;
        boolean removedSomething = false;

        for (int i = 0; i < numberOfBuckets; ++i) {
            currentListElement = buckets[i].getHead();
            while (currentListElement != null) {
                if (currentListElement.getDocument().getAuthor().equals(author)) {
                    DocumentListElement listElementToRemove = currentListElement;
                    currentListElement = currentListElement.getNext();
                    removedSomething |= buckets[i].remove(listElementToRemove);
                } else {
                    currentListElement = currentListElement.getNext();
                }
            }
        }
        return removedSomething;
    }

    public boolean removeAll() {
        if (this.isEmpty()) {
            return false;
        }

        for (int i = 0; i < numberOfBuckets; ++i) {
            buckets[i] = new Bucket();
        }
        return true;
    }

    public boolean removeOldDocuments(Date releaseDate, Date lastUpdated) {
        if (releaseDate == null && lastUpdated == null) {
            if (!removeAll()) {
                System.out.println("Hello there!");
                return false;
            }
            return true;
        }

        if (releaseDate != null && lastUpdated != null && releaseDate.daysUntil(lastUpdated) < 0) {
            return false;
        }

        DocumentListElement currentListElement;
        boolean removedSomething = false;

        for (int i = 0; i < numberOfBuckets; ++i) {
            currentListElement = buckets[i].getHead();
            while (currentListElement != null) {
                if (releaseDate != null
                        && currentListElement.getDocument().getReleaseDate().daysUntil(releaseDate) > 0) {
                    if (lastUpdated != null
                            && currentListElement.getDocument().getLastUpdateDate().daysUntil(lastUpdated) > 0) {
                        DocumentListElement listElementToRemove = currentListElement;
                        removedSomething |= buckets[i].remove(listElementToRemove);
                    } else if (lastUpdated == null) {
                        DocumentListElement listElementToRemove = currentListElement;
                        removedSomething |= buckets[i].remove(listElementToRemove);
                    }
                } else if (releaseDate == null
                        && currentListElement.getDocument().getLastUpdateDate().daysUntil(lastUpdated) > 0) {
                    DocumentListElement listElementToRemove = currentListElement;
                    removedSomething |= buckets[i].remove(listElementToRemove);
                }
                currentListElement = currentListElement.getNext();
            }
        }
        return removedSomething;
    }

    private int indexFunction(int documentId) {
        return documentId % numberOfBuckets;
    }

    public DocumentListElement getHead(int bucketIndex) {
        if (bucketIndex < 0 || bucketIndex >= numberOfBuckets) {
            return null;
        }
        return buckets[bucketIndex].getHead();
    }

    public DocumentListElement getTail(int bucketIndex) {
        if (bucketIndex < 0 || bucketIndex >= numberOfBuckets) {
            return null;
        }
        return buckets[bucketIndex].getTail();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean contains(Document document) {
        if (document == null) {
            return false;
        }
        return this.find(document.getDocumentId()) != null;
    }

    public int getNumberOfBuckets() {
        return numberOfBuckets;
    }

    public Bucket[] getBuckets() {
        return buckets;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < numberOfBuckets; ++i) {
            size += buckets[i].size();
        }
        return size;
    }

}