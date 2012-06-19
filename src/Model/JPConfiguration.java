package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import Algorithms.JPAbstractAlgorithm;
import Algorithms.FuzzySimilarityAlgorithm;
import Algorithms.LevenshteinDistanceAlgorithm;
import Algorithms.OnthologyBasedQueryAlgorithm;
import Algorithms.TFIDFAlgorithm;
import Include.JPInclude;
import Loader.JPDocumentLoader;
import Loader.JPDocumentLoaderPlainText;
import POSTagger.JPPOSTagger;
import POSTagger.JPPOSTaggerDummy;
import POSTagger.JPPOSTaggerIllinois;
import POSTagger.JPPOSTaggerStanford;
import Parser.JPStringParser;
import SenseRelate.JPAbstractSenseRelate;
import SenseRelate.JPSenseRelateBaseline;
import SenseRelate.JPSenseRelateWordNet;
import Stemmer.JPStemmer;
import Stemmer.JPStemmerDummy;
import Stemmer.JPStemmerLovins;
import Stemmer.JPStemmerPorter;
import Stemmer.JPStemmerWordnet;
import Trimmer.JPTrimmer;
import Trimmer.JPTrimmerDummy;
import Trimmer.JPTrimmerStopWords;

/**
 * The Class JPConfiguration.
 * Configuration for a computation
 */
public class JPConfiguration {

	boolean normalized;
	/**
	 * The Enum AlgorithmIndex.
	 */
	public enum AlgorithmIndex {
		
		/** The Algorithm index fuzzy similarity. */
		AlgorithmIndexFuzzySimilarity, 
 /** The Algorithm index levenshtein distance. */
 AlgorithmIndexLevenshteinDistance, 
 /** The Algorithm index tfidf. */
 AlgorithmIndexTFIDF, 
 /** The Algorithm index onthology based query. */
 AlgorithmIndexOnthologyBasedQuery;

		/**
		 * Gets the algorithm index from int.
		 *
		 * @param index the index
		 * @return the algorithm index from int
		 */
		public static AlgorithmIndex getAlgorithmIndexFromInt(int index) {
			switch (index) {
			case 0:
				return AlgorithmIndexFuzzySimilarity;
			case 1:
				return AlgorithmIndexLevenshteinDistance;
			case 2:
				return AlgorithmIndexTFIDF;
			case 3:
				return AlgorithmIndexOnthologyBasedQuery;
			default:
				throw new RuntimeException("Unknown algorithm index: " + index);
			}
		}
	}

	/**
	 * The Enum StemmerType.
	 */
	public enum StemmerType {
		
		/** The Stemmer type dummy. */
		StemmerTypeDummy, 
 /** The Stemmer type wordnet. */
 StemmerTypeWordnet, 
 /** The Stemmer type porter. */
 StemmerTypePorter, 
 /** The Stemmer type lovins. */
 StemmerTypeLovins;
		
		/**
		 * Gets the stemmer type from int.
		 *
		 * @param index the index
		 * @return the stemmer type from int
		 */
		public static StemmerType getStemmerTypeFromInt(int index) {
			switch (index) {
			case 0:
				return StemmerTypeDummy;
			case 1:
				return StemmerTypeWordnet;
			case 2:
				return StemmerTypePorter;
			case 3:
				return StemmerTypeLovins;
			default:
				throw new RuntimeException("Unknown stemmer type: " + index);
			}
		}
	}
	
	/**
	 * The Enum SenseRelateType.
	 */
	public enum SenseRelateType {
		
		/** The Sense relate type dummy. */
		SenseRelateTypeDummy, 
 /** The Sense relate type word net. */
 SenseRelateTypeWordNet;

		/**
		 * Gets the sense relate type from int.
		 *
		 * @param index the index
		 * @return the sense relate type from int
		 */
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
	
	/**
	 * The Enum POSTaggerType.
	 */
	public enum POSTaggerType {
		
		/** The POS tagger type dummy. */
		POSTaggerTypeDummy, 
 /** The POS tagger type stanford. */
 POSTaggerTypeStanford, 
 /** The POS tagger type illinois. */
 POSTaggerTypeIllinois;

		/**
		 * Gets the POS tagger type from int.
		 *
		 * @param index the index
		 * @return the POS tagger type from int
		 */
		public static POSTaggerType getPOSTaggerTypeFromInt(int index) {
			switch (index) {
			case 0:
				return POSTaggerTypeDummy;
			case 1:
				return POSTaggerTypeStanford;
			case 2:
				return POSTaggerTypeIllinois;
			default:
				throw new RuntimeException("Unknown POS tagger type: " + index);
			}
		}
	}
	
	/**
	 * The Enum TrimmerType.
	 */
	public enum TrimmerType {
		
		/** The Trimmer type dummy. */
		TrimmerTypeDummy, 
 /** The Tripper type stop words. */
 TripperTypeStopWords;

		/**
		 * Gets the trimmer type from int.
		 *
		 * @param index the index
		 * @return the trimmer type from int
		 */
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

	/**
	 * The Enum IncludeType.
	 */
	public enum IncludeType {

		/** The Include type all. */
		IncludeTypeAll,
		/** The Include type pos tagged. */
		IncludeTypePOSTagged,
		/** The Include type sense related. */
		IncludeTypeSenseRelated;

		/**
		 * Gets the include type from int.
		 * 
		 * @param index
		 *            the index
		 * @return the include type from int
		 */
		public static IncludeType getIncludeTypeFromInt(int index) {
			switch (index) {
			case 0:
				return IncludeTypeAll;
			case 1:
				return IncludeTypePOSTagged;
			case 2:
				return IncludeTypeSenseRelated;
			default:
				return IncludeTypeAll;
			}
		}
	}
	
	public enum IncludeNeighbourWordsType {
		IncludeNeighbourWordsTypeAll,
		IncludeNeighbourWordsTypeSynonyms,
		IncludeNeighbourWordsTypeHypoHyperNyms
	}

	/** The algorithm index. */
	private AlgorithmIndex algorithmIndex;
	
	/** The stemmer type. */
	private StemmerType stemmerType = StemmerType.StemmerTypeDummy;
	
	/** The sense relate type. */
	private SenseRelateType senseRelateType = SenseRelateType.SenseRelateTypeDummy;
	
	/** The pos tagger type. */
	private POSTaggerType posTaggerType = POSTaggerType.POSTaggerTypeDummy;
	
	/** The trimmer type. */
	private TrimmerType trimmerType = TrimmerType.TrimmerTypeDummy;
	
	/** The include type. */
	private IncludeType includeType = IncludeType.IncludeTypeAll;
	
	private IncludeNeighbourWordsType includeNeighbourWordsType = IncludeNeighbourWordsType.IncludeNeighbourWordsTypeAll;

	
	/** The includes. */
	private ArrayList<JPInclude> includes = new ArrayList<JPInclude>();
	
	/** The main document file. */
	private File mainDocumentFile;
	
	/** The document files. */
	private File[] documentFiles;
		
	public HashMap<String,String> algorithmSettings = new HashMap<String, String>();
	

	/**
	 * Gets the algorithm index.
	 *
	 * @return the algorithm index
	 */
	public AlgorithmIndex getAlgorithmIndex() {
		return algorithmIndex;
	}
	
	/**
	 * Sets the algorithm index.
	 *
	 * @param algorithmIndex the new algorithm index
	 */
	public void setAlgorithmIndex(AlgorithmIndex algorithmIndex) {
		this.algorithmIndex = algorithmIndex;
	}
	
	/**
	 * Gets the stemmer type.
	 *
	 * @return the stemmer type
	 */
	public StemmerType getStemmerType() {
		return stemmerType;
	}
	
	/**
	 * Sets the stemmer type.
	 *
	 * @param stemmerType the new stemmer type
	 */
	public void setStemmerType(StemmerType stemmerType) {
		this.stemmerType = stemmerType;
	}
	
	/**
	 * Gets the main document file.
	 *
	 * @return the main document file
	 */
	public File getMainDocumentFile() {
		return mainDocumentFile;
	}
	
	/**
	 * Sets the main document file.
	 *
	 * @param mainDocumentFile the new main document file
	 */
	public void setMainDocumentFile(File mainDocumentFile) {
		this.mainDocumentFile = mainDocumentFile;
	}
	
	/**
	 * Gets the other document files.
	 *
	 * @return the other document files
	 */
	public File[] getDocumentFiles() {
		return documentFiles;
	}
	
	/**
	 * Sets the other document files.
	 *
	 * @param documentFiles the new document files
	 */
	public void setDocumentFiles(File[] documentFiles) {
		this.documentFiles = documentFiles;
	}
	
	/**
	 * Gets the algorithm.
	 *
	 * @return the algorithm
	 */
	public JPAbstractAlgorithm getAlgorithm() {
		switch (this.algorithmIndex) {
		case AlgorithmIndexFuzzySimilarity:
			return new FuzzySimilarityAlgorithm();
		case AlgorithmIndexLevenshteinDistance:
			return new LevenshteinDistanceAlgorithm();
		case AlgorithmIndexTFIDF:
			return new TFIDFAlgorithm();
		case AlgorithmIndexOnthologyBasedQuery:
			return new OnthologyBasedQueryAlgorithm();
		default:
            throw new RuntimeException("Unknown algorithm index: " + this.algorithmIndex);
		}
	}
	
	/**
	 * Gets the stemmer.
	 *
	 * @return the stemmer
	 */
	public JPStemmer getStemmer() {
		switch (stemmerType) {
		case StemmerTypeDummy:
			return new JPStemmerDummy();
		case StemmerTypeWordnet:
			return new JPStemmerWordnet();
		case StemmerTypePorter:
			return new JPStemmerPorter();
		case StemmerTypeLovins:
			return new JPStemmerLovins();
		default:
            throw new RuntimeException("Unknown stemmer type: " + stemmerType);
		}
	}
	
	/**
	 * Gets the sense relate.
	 *
	 * @return the sense relate
	 */
	public JPAbstractSenseRelate getSenseRelate() {
		switch (senseRelateType) {
		case SenseRelateTypeDummy:
			return new JPSenseRelateBaseline();
		case SenseRelateTypeWordNet:
			return new JPSenseRelateWordNet();
		default:
            throw new RuntimeException("Unknown sense relate type: " + senseRelateType);
		}
	}
	
	/**
	 * Gets the POS tagger.
	 *
	 * @return the POS tagger
	 */
	public JPPOSTagger getPOSTagger() {
		switch (posTaggerType) {
		case POSTaggerTypeDummy:
			return new JPPOSTaggerDummy();
		case POSTaggerTypeStanford:
			return new JPPOSTaggerStanford();
		case POSTaggerTypeIllinois:
			return new JPPOSTaggerIllinois();
		default:
            throw new RuntimeException("Unknown POS tagger type: " + posTaggerType);
		}
	}
	
	/**
	 * Gets the trimmer.
	 *
	 * @return the trimmer
	 */
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
		
	/**
	 * Gets the include types.
	 *
	 * @return the include types
	 */
	public ArrayList<JPInclude> getIncludeTypes() {
		return includes;
	}
	
	/**
	 * Sets the include types.
	 *
	 * @param includeTypes the new include types
	 */
	public void setIncludeTypes(ArrayList<JPInclude> includeTypes) {
		this.includes = includeTypes;
	}
	
	/**
	 * Gets the sense relate type.
	 *
	 * @return the sense relate type
	 */
	public SenseRelateType getSenseRelateType() {
		return senseRelateType;
	}
	
	/**
	 * Sets the sense relate type.
	 *
	 * @param senseRelateType the new sense relate type
	 */
	public void setSenseRelateType(SenseRelateType senseRelateType) {
		this.senseRelateType = senseRelateType;
	}
	
	/**
	 * Gets the POS tagger type.
	 *
	 * @return the POS tagger type
	 */
	public POSTaggerType getPosTaggerType() {
		return posTaggerType;
	}
	
	/**
	 * Sets the POS tagger type.
	 *
	 * @param posTaggerType the new POS tagger type
	 */
	public void setPosTaggerType(POSTaggerType posTaggerType) {
		this.posTaggerType = posTaggerType;
	}
	
	/**
	 * Sets the trimmer type.
	 *
	 * @param trimmerType the new trimmer type
	 */
	public void setTrimmerType(TrimmerType trimmerType) {
		this.trimmerType = trimmerType;
	}
	
	/**
	 * Gets the trimmer type.
	 *
	 * @return the trimmer type
	 */
	public TrimmerType getTrimmerType() {
		return trimmerType;
	}
	
	/**
	 * Gets the document loader for file.
	 *
	 * @param file the file
	 * @return the document loader for file
	 */
	public JPDocumentLoader getDocumentLoaderForFile(File file) {
		int dotposition= file.getPath().lastIndexOf(".");
		String ext = file.getPath().substring(dotposition + 1, file.getPath().length()); 	
		
		if (ext.equals("txt")) {
			return new JPDocumentLoaderPlainText();
		} else {
			throw new RuntimeException("File extension not supported: " + ext);
		}
	}
	
	/**
	 * Gets the parser for loader.
	 *
	 * @param loader the loader
	 * @return the parser for loader
	 */
	public JPStringParser getParserForLoader(JPDocumentLoader loader) {
		return new JPStringParser();
	}
	
	/**
	 * Gets the include type.
	 *
	 * @return the include type
	 */
	public IncludeType getIncludeType() {
		return includeType;
	}
	
	/**
	 * Sets the include type.
	 *
	 * @param includeType the new include type
	 */
	public void setIncludeType(IncludeType includeType) {
		this.includeType = includeType;
	}
	
	public void setNormalized(boolean normalized) {
		this.normalized = normalized;
	}
	
	public boolean getNormalized() {
		return normalized;
	}

	public IncludeNeighbourWordsType getIncludeNeighbourWordsType() {
		return includeNeighbourWordsType;
	}

	public void setIncludeNeighbourWordsType(IncludeNeighbourWordsType includeNeighbourWordsType) {
		this.includeNeighbourWordsType = includeNeighbourWordsType;
	}

}
