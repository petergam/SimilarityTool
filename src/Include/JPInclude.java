package Include;

import Model.JPConfiguration.IncludeNeighbourWordsType;
import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;

/**
 * The Interface JPInclude.
 */
public interface JPInclude {
	
    /**
     * Include. Include extra information in a document.
     *
     * @param document the document
     * @param includeType the include type
     * @return the new document
     */
    public JPDocument include(JPDocument document, IncludeType includeType, IncludeNeighbourWordsType includeNeigbbourWordsType);
	
}
