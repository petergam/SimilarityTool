package Include;

import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;

public class JPIncludeDummy extends JPAbstractInclude {

	@Override
	public JPDocument include(JPDocument document, IncludeType includeType) {
		return document;
	}
	
}
