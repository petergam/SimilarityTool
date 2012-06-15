package SenseRelate;

import Objects.JPDocument;

/**
 * The Interface JPSenseRelate.
 */
public interface JPSenseRelate {

    /**
     * Sense relate. Find senses to the words in a document
     *
     * @param document the document that should have senses
     * @return the sense related document
     */
    public JPDocument senseRelate(JPDocument document);

}
