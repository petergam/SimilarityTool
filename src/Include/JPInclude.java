package Include;

import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;

// TODO: Auto-generated Javadoc
/**
 * The Interface JPInclude.
 */
public interface JPInclude {
	
    /**
     * Include.
     *
     * @param document the document
     * @param includeType the include type
     * @return the jP document
     */
    public JPDocument include(JPDocument document, IncludeType includeType);
	
}
