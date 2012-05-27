package Model;

import WVToolExtension.AbstractInclude;
import WVToolExtension.IncludeDummy;
import WVToolExtension.IncludeHypernyms;
import WVToolExtension.IncludeSynonyms;
import WVToolExtension.JPInclude;
import Algorithms.Algorithm;
import Algorithms.FuzzySimilarityAlgorithm;
import Algorithms.LevenshteinDistanceAlgorithm;
import edu.udo.cs.wvtool.generic.stemmer.AbstractStemmer;
import edu.udo.cs.wvtool.generic.stemmer.DummyStemmer;
import edu.udo.cs.wvtool.generic.stemmer.LovinsStemmerWrapper;
import edu.udo.cs.wvtool.generic.stemmer.PorterStemmerWrapper;
import edu.udo.cs.wvtool.generic.stemmer.ToLowerCaseConverter;
import edu.udo.cs.wvtool.generic.stemmer.WordNetHypernymStemmer;
import edu.udo.cs.wvtool.generic.stemmer.WordNetSynonymStemmer;
import edu.udo.cs.wvtool.generic.wordfilter.AbstractStopWordFilter;
import edu.udo.cs.wvtool.generic.wordfilter.DummyWordFilter;
import edu.udo.cs.wvtool.generic.wordfilter.StopWordsWrapper;

public class ComputeSetup {

	public enum AlgorithmIndex {
		AlgorithmIndexFuzzySimilarity, AlgorithmIndexLevenshteinDistance;

		public static AlgorithmIndex getAlgorithmIndexFromInt(int index) {
			switch (index) {
			case 0:
				return AlgorithmIndexFuzzySimilarity;
			case 1:
				return AlgorithmIndexLevenshteinDistance;
			default:
				throw new RuntimeException("Unknown algorithm index: " + index);
			}
		}
	}

	public enum StemmerType {
		StemmerTypeDummy, StemmerTypeLovins, StemmerTypePorter, StemmerTypeLoverCase, StemmerTypeHypernym, StemmerTypeSynonym;
		public static StemmerType getStemmerTypeFromInt(int index) {
			switch (index) {
			case 0:
				return StemmerTypeDummy;
			case 1:
				return StemmerTypeLovins;
			case 2:
				return StemmerTypePorter;
			case 3:
				return StemmerTypeLoverCase;
			case 4:
				return StemmerTypeHypernym;
			case 5:
				return StemmerTypeSynonym;
			default:
				throw new RuntimeException("Unknown stemmer type: " + index);
			}
		}
	}

	public enum WordFilterType {
		WordFilterTypeDummy, WordFilterTypeRemoveStopWords;

		public static WordFilterType getWordFilterTypeFromInt(int index) {
			switch (index) {
			case 0:
				return WordFilterTypeDummy;
			case 1:
				return WordFilterTypeRemoveStopWords;
			default:
				throw new RuntimeException("Unknown filter type: " + index);
			}
		}
	}
	
	public enum IncludeType {
		IncludeTypeDummy, IncludeTypeHypernyms, IncludeTypeSynonyms;

		public static IncludeType getIncludeTypeFromInt(int index) {
			switch (index) {
			case 0:
				return IncludeTypeDummy;
			case 1:
				return IncludeTypeHypernyms;
			case 2:
				return IncludeTypeSynonyms;
			default:
				throw new RuntimeException("Unknown include type: " + index);
			}
		}
	}


	private AlgorithmIndex algorithmIndex;
	private StemmerType stemmerType;
	private WordFilterType filterType;
	private IncludeType includeType;
	private String mainDocumentPath;
	private String[] documentPaths;

	public IncludeType getIncludeType() {
		return includeType;
	}
	public void setIncludeType(IncludeType includeType) {
		this.includeType = includeType;
	}
	
	public WordFilterType getFilterType() {
		return filterType;
	}
	public void setFilterType(WordFilterType filterType) {
		this.filterType = filterType;
	}
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
	public String getMainDocumentPath() {
		return mainDocumentPath;
	}
	public void setMainDocumentPath(String mainDocumentPath) {
		this.mainDocumentPath = mainDocumentPath;
	}
	public String[] getDocumentPaths() {
		return documentPaths;
	}
	public void setDocumentPaths(String[] documentPaths) {
		this.documentPaths = documentPaths;
	}
	
	public Algorithm getAlgorithm() {
		switch (this.algorithmIndex) {
		case AlgorithmIndexFuzzySimilarity:
			return new FuzzySimilarityAlgorithm();
		case AlgorithmIndexLevenshteinDistance:
			return new LevenshteinDistanceAlgorithm();
		default:
            throw new RuntimeException("Unknown algorithm index: " + this.algorithmIndex);
		}
	}
	
	public AbstractStopWordFilter getFilter() {
		switch (filterType) {
		case WordFilterTypeDummy:
			return new DummyWordFilter();
		case WordFilterTypeRemoveStopWords:
			return new StopWordsWrapper();
		default:
            throw new RuntimeException("Unknown filter: " + filterType);
		}
	}
	
	public AbstractStemmer getStemmer() {
		switch (stemmerType) {
		case StemmerTypeDummy:
			return new DummyStemmer();
		case StemmerTypeLovins:
			return new LovinsStemmerWrapper();
		case StemmerTypePorter:
			return new PorterStemmerWrapper();
		case StemmerTypeLoverCase:
			return new ToLowerCaseConverter();
		case StemmerTypeHypernym:
			return new WordNetHypernymStemmer();
		case StemmerTypeSynonym:
			return new WordNetSynonymStemmer();
		default:
            throw new RuntimeException("Unknown stemmer type: " + stemmerType);
		}
	}
	
	public AbstractInclude getInclude() {
		switch (includeType) {
		case IncludeTypeDummy:
			return new IncludeDummy();
		case IncludeTypeHypernyms:
			return new IncludeHypernyms();
		case IncludeTypeSynonyms:
			return new IncludeSynonyms();
		default:
            throw new RuntimeException("Unknown include: " + includeType);
		}
	}

	
}
