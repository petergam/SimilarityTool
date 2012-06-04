package Model;

import java.io.File;
import java.util.ArrayList;

import Algorithms.JPAbstractAlgorithm;
import Algorithms.FuzzySimilarityAlgorithm;
import Algorithms.LevenshteinDistanceAlgorithm;
import Algorithms.TFIDFAlgorithm;
import Include.JPInclude;
import Loader.JPDocumentLoader;
import Loader.JPDocumentLoaderPlainText;
import POSTagger.JPPOSTagger;
import POSTagger.JPPOSTaggerDummy;
import POSTagger.JPPOSTaggerStanford;
import Parser.JPStringParser;
import SenseRelate.JPAbstractSenseRelate;
import SenseRelate.JPSenseRelateDummy;
import SenseRelate.JPSenseRelateWordNet;
import Stemmer.JPStemmer;
import Stemmer.JPStemmerDummy;
import Stemmer.JPStemmerWordnet;
import Trimmer.JPTrimmer;
import Trimmer.JPTrimmerDummy;
import Trimmer.JPTrimmerStopWords;

public class JPConfiguration {

	public enum AlgorithmIndex {
		AlgorithmIndexFuzzySimilarity, AlgorithmIndexLevenshteinDistance, AlgorithmIndexTFIDF;

		public static AlgorithmIndex getAlgorithmIndexFromInt(int index) {
			switch (index) {
			case 0:
				return AlgorithmIndexFuzzySimilarity;
			case 1:
				return AlgorithmIndexLevenshteinDistance;
			case 2:
				return AlgorithmIndexTFIDF;
			default:
				throw new RuntimeException("Unknown algorithm index: " + index);
			}
		}
	}

	public enum StemmerType {
		StemmerTypeDummy, StemmerTypeWordnet;
		public static StemmerType getStemmerTypeFromInt(int index) {
			switch (index) {
			case 0:
				return StemmerTypeDummy;
			case 1:
				return StemmerTypeWordnet;
			default:
				throw new RuntimeException("Unknown stemmer type: " + index);
			}
		}
	}
	
	public enum SenseRelateType {
		SenseRelateTypeDummy, SenseRelateTypeWordNet;

		public static SenseRelateType getSenseRelateTypeFromInt(int index) {
			switch (index) {
			case 0:
				return SenseRelateTypeDummy;
			case 1:
				return SenseRelateTypeWordNet;
			default:
				throw new RuntimeException("Unknown sense relate type: " + index);
			}
		}
	}
	
	public enum POSTaggerType {
		POSTaggerTypeDummy, POSTaggerTypeStanford;

		public static POSTaggerType getPOSTaggerTypeFromInt(int index) {
			switch (index) {
			case 0:
				return POSTaggerTypeDummy;
			case 1:
				return POSTaggerTypeStanford;
			default:
				throw new RuntimeException("Unknown POS tagger type: " + index);
			}
		}
	}
	
	public enum TrimmerType {
		TrimmerTypeDummy, TripperTypeStopWords;

		public static TrimmerType getTrimmerTypeFromInt(int index) {
			switch (index) {
			case 0:
				return TrimmerTypeDummy;
			case 1:
				return TripperTypeStopWords;
			default:
				throw new RuntimeException("Unknown trimmer type: " + index);
			}
		}
	}
	
	public enum IncludeType {
		IncludeTypeAll, IncludeTypePOSTagged, IncludeTypeSenseRelated;
		
		public static IncludeType getIncludeTypeFromInt(int index) {
			switch (index) {
			case 0:
				return IncludeTypeAll;
			case 1:
				return IncludeTypePOSTagged;
			case 2:
				return IncludeTypeSenseRelated;
			default:
				throw new RuntimeException("Unknown include type: " + index);
			}
		}
	}
	

	private AlgorithmIndex algorithmIndex;
	private StemmerType stemmerType = StemmerType.StemmerTypeDummy;
	private SenseRelateType senseRelateType = SenseRelateType.SenseRelateTypeDummy;
	private POSTaggerType posTaggerType = POSTaggerType.POSTaggerTypeDummy;
	private TrimmerType trimmerType = TrimmerType.TrimmerTypeDummy;
	private IncludeType includeType = IncludeType.IncludeTypeAll;
	private ArrayList<JPInclude> includes = new ArrayList<JPInclude>();
	private File mainDocumentFile;
	private File[] documentFiles;
	

	public AlgorithmIndex getAlgorithmIndex() {
		return algorithmIndex;
	}
	public void setAlgorithmIndex(AlgorithmIndex algorithmIndex) {
		this.algorithmIndex = algorithmIndex;
	}
	public StemmerType getStemmerType() {
		return stemmerType;
	}
	public void setStemmerType(StemmerType stemmerType) {
		this.stemmerType = stemmerType;
	}
	public File getMainDocumentFile() {
		return mainDocumentFile;
	}
	public void setMainDocumentFile(File mainDocumentFile) {
		this.mainDocumentFile = mainDocumentFile;
	}
	public File[] getDocumentFiles() {
		return documentFiles;
	}
	public void setDocumentFiles(File[] documentFiles) {
		this.documentFiles = documentFiles;
	}
	
	public JPAbstractAlgorithm getAlgorithm() {
		switch (this.algorithmIndex) {
		case AlgorithmIndexFuzzySimilarity:
			return new FuzzySimilarityAlgorithm();
		case AlgorithmIndexLevenshteinDistance:
			return new LevenshteinDistanceAlgorithm();
		case AlgorithmIndexTFIDF:
			return new TFIDFAlgorithm();
		default:
            throw new RuntimeException("Unknown algorithm index: " + this.algorithmIndex);
		}
	}
	
	public JPStemmer getStemmer() {
		switch (stemmerType) {
		case StemmerTypeDummy:
			return new JPStemmerDummy();
		case StemmerTypeWordnet:
			return new JPStemmerWordnet();
		default:
            throw new RuntimeException("Unknown stemmer type: " + stemmerType);
		}
	}
	
	public JPAbstractSenseRelate getSenseRelate() {
		switch (senseRelateType) {
		case SenseRelateTypeDummy:
			return new JPSenseRelateDummy();
		case SenseRelateTypeWordNet:
			return new JPSenseRelateWordNet();
		default:
            throw new RuntimeException("Unknown sense relate type: " + senseRelateType);
		}
	}
	
	public JPPOSTagger getPOSTagger() {
		switch (posTaggerType) {
		case POSTaggerTypeDummy:
			return new JPPOSTaggerDummy();
		case POSTaggerTypeStanford:
			return new JPPOSTaggerStanford();
		default:
            throw new RuntimeException("Unknown POS tagger type: " + posTaggerType);
		}
	}
	
	public JPTrimmer getTrimmer() {
		switch (trimmerType) {
		case TrimmerTypeDummy:
			return new JPTrimmerDummy();
		case TripperTypeStopWords:
			return new JPTrimmerStopWords();
		default:
            throw new RuntimeException("Unknown trimmer type: " + trimmerType);
		}
	}
		
	public ArrayList<JPInclude> getIncludeTypes() {
		return includes;
	}
	public void setIncludeTypes(ArrayList<JPInclude> includeTypes) {
		this.includes = includeTypes;
	}
	public SenseRelateType getSenseRelateType() {
		return senseRelateType;
	}
	public void setSenseRelateType(SenseRelateType senseRelateType) {
		this.senseRelateType = senseRelateType;
	}
	public POSTaggerType getPosTaggerType() {
		return posTaggerType;
	}
	public void setPosTaggerType(POSTaggerType posTaggerType) {
		this.posTaggerType = posTaggerType;
	}
	public void setTrimmerType(TrimmerType trimmerType) {
		this.trimmerType = trimmerType;
	}
	public TrimmerType getTrimmerType() {
		return trimmerType;
	}
	
	public JPDocumentLoader getDocumentLoaderForFile(File file) {
		int dotposition= file.getPath().lastIndexOf(".");
		String ext = file.getPath().substring(dotposition + 1, file.getPath().length()); 	
		
		if (ext.equals("txt")) {
			return new JPDocumentLoaderPlainText();
		} else {
			throw new RuntimeException("File extension not supported: " + ext);
		}
	}
	
	public JPStringParser getParserForLoader(JPDocumentLoader loader) {
		return new JPStringParser();
	}
	public IncludeType getIncludeType() {
		return includeType;
	}
	public void setIncludeType(IncludeType includeType) {
		this.includeType = includeType;
	}
}
