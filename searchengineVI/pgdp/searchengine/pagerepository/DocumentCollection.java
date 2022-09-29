package pgdp.searchengine.pagerepository;

import java.util.Iterator;

import pgdp.searchengine.util.Date;
import pgdp.searchengine.util.WordCount;

public class DocumentCollection implements Iterable<Document> {
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

    public WordCount[] getCompleteWordCountArray() {
        WordCount[] allWords = new WordCount[0];

        for (int i = 0; i < numberOfBuckets; i++) {
            if (buckets[i].getHead() != null) {
                DocumentListElement dle = buckets[i].getHead();
                while (dle != null) {
                    allWords = Document.equalizeWordCountArray(allWords, dle.getDocument().getWordCountArray());
                    dle = dle.getNext();
                }
            }
        }
        Document.sort(allWords);
        return allWords;
    }

    public void equalizeAllWordCountArrays() {
        WordCount[] allWords = this.getCompleteWordCountArray();

        for (int i = 0; i < buckets.length; i++) {
            DocumentListElement currentDle = buckets[i].getHead();
            while (currentDle != null) {
                WordCount[] equalizedArray = Document.equalizeWordCountArray(currentDle.getWordCountArray(), allWords);
                Document.sort(equalizedArray);
                currentDle.setWordCountArray(equalizedArray);
                currentDle = currentDle.getNext();
            }
        }
    }

    public int getNumberOfDocumentsContaining(String word) {
        if (word == null) {
            return 0;
        }
        
        int count = 0;
        WordCount[] currentWordCountArray;
        DocumentListElement currentDle;
        word = word.toLowerCase();

        for (int i = 0; i < buckets.length; i++) {
            currentDle = buckets[i].getHead();
            while (currentDle != null) {
                currentWordCountArray = currentDle.getWordCountArray();
                for (int j = 0; j < currentWordCountArray.length; j++) {
                    if (currentWordCountArray[j].getWord().equals(word) && currentWordCountArray[j].getCount() != 0) {
                        count++;
                        break;
                    }
                }
                currentDle = currentDle.getNext();
            }
        }

        return count;
    }

    private void calculateWeights(WordCount[] wordCount) {
        if (wordCount == null) {
            return;
        }

        double weightSum = 0;

        for (int i = 0; i < wordCount.length; i++) {
            double invertedFrequency = Math
                    .log((this.size() + 1.0) / this.getNumberOfDocumentsContaining(wordCount[i].getWord())) / Math.log(2);
            double weight = wordCount[i].getCount() * invertedFrequency;
            weightSum += weight * weight;
            wordCount[i].setWeight(weight);
        }
        weightSum = Math.sqrt(weightSum);

        for (int i = 0; i < wordCount.length; i++) {
            wordCount[i].setNormalizedWeight(wordCount[i].getWeight() / weightSum);
        }
    }

    public Document[] query(String query) {
        if (query == null) {
            return new Document[] {};
        }

        Document queryDoc = new Document("", "", query, null, null);
        int queryId = queryDoc.getDocumentId();
        this.add(queryDoc);

        WordCount[] queryWordCount = Document.equalizeWordCountArray(queryDoc.getWordCountArray(),
                getCompleteWordCountArray());
        Document.sort(queryWordCount);
        this.calculateWeights(queryWordCount);

        this.equalizeAllWordCountArrays();
        for (int i = 0; i < numberOfBuckets; i++) {
            DocumentListElement dle = buckets[i].getHead();
            while (dle != null) {
                this.calculateWeights(dle.getWordCountArray());
                dle.setSimilarity(Document.complexSimilarity(queryWordCount, dle.getWordCountArray()));
                dle = dle.getNext();
            }
        }

        this.removeDocument(queryId);
        
        return this.similarityRanking();
    }

    public Document[] similarityRanking() {
        this.sortBuckets();
        Document[] ranked = new Document[this.size()];
        DocumentListElement[] allHeads = new DocumentListElement[buckets.length];
        double highestSimilarity;
        int highestSimIndex;

        for (int i = 0; i < allHeads.length; i++) {
            allHeads[i] = buckets[i].getHead();
        }

        for (int i = 0; i < ranked.length; i++) {
            highestSimilarity = 0;
            highestSimIndex = -1;

            for (int j = 0; j < allHeads.length; j++) {
                if (highestSimIndex == -1 && allHeads[j] != null) {
                    highestSimilarity = allHeads[j].getSimilarity();
                    highestSimIndex = j;
                }
                if (allHeads[j] != null && allHeads[j].getSimilarity() > highestSimilarity) {
                    highestSimilarity = allHeads[j].getSimilarity();
                    highestSimIndex = j;
                }
                if (allHeads[j] != null && allHeads[j].getSimilarity() == highestSimilarity && allHeads[j].getDocumentId() < allHeads[highestSimIndex].getDocumentId()) {
                    highestSimilarity = allHeads[j].getSimilarity();
                    highestSimIndex = j;
                }
            }

            ranked[i] = allHeads[highestSimIndex].getDocument();

            allHeads[highestSimIndex] = allHeads[highestSimIndex].getNext();
        }
        return ranked;
    }

    public void sortBuckets() {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].getHead() != null) {
                DocumentListElement currentListElement;
                boolean sorted = false;
                while (!sorted) {
                    currentListElement = buckets[i].getHead();
                    sorted = true;
                    while (currentListElement.getNext() != null) {
                        if (currentListElement.getSimilarity() < currentListElement.getNext().getSimilarity()) {
                            buckets[i].swapListElements(currentListElement, currentListElement.getNext());
                            sorted = false;
                        }
                        if (currentListElement.getSimilarity() == currentListElement.getNext().getSimilarity() && currentListElement.getDocumentId() > currentListElement.getNext().getDocumentId()) {
                            buckets[i].swapListElements(currentListElement, currentListElement.getNext());
                            sorted = false;
                        }
                        currentListElement = currentListElement.getNext();
                    }
                }
            }
        }
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

    @Override
    public Iterator<Document> iterator() {

        return new Iterator<Document>() {
            private int bucket = 0;
            private DocumentListElement currentDoc = null;
            
            {
                while (bucket < buckets.length && buckets[bucket].getHead() == null) {
                    bucket++;
                }
                if (bucket < buckets.length) {
                    currentDoc = buckets[bucket].getHead();
                }        
            }

            @Override
            public boolean hasNext() {
                return bucket < buckets.length;
            }

            @Override
            public Document next() {
                if (bucket >= buckets.length) {
                    return null;
                }

                Document toReturnDoc = currentDoc.getDocument();

                if (currentDoc.getNext() != null) {
                    currentDoc = currentDoc.getNext();
                } else {
                    do {
bucket++;
                    } while (bucket < buckets.length && buckets[bucket].getHead() == null);
                    
                    if (bucket < buckets.length) {
                        currentDoc = buckets[bucket].getHead();
                    }
                }

                return toReturnDoc;
            }
        };
    }

}