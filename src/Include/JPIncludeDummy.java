package Include;

import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class JPIncludeDummy.
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
