package Include;

import Model.JPConfiguration.IncludeType;
import Objects.JPDocument;

public interface JPInclude {
	
    public JPDocument include(JPDocument document, IncludeType includeType);
	
}
