package Include;

import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;

/**
 * The Class JPIncludeDummy.
 * Dummy class. Does no change anything
 */
public class JPIncludeDummy extends JPAbstractInclude {

	/* (non-Javadoc)
	 * @see Include.JPInclude#include(Objects.JPDocument, Model.JPConfiguration.IncludeType)
	 */
	@Override
	public JPDocument include(JPDocument document, IncludeType includeType) {
		return document;
	}
	
}
