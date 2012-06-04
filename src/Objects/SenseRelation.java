package Objects;

import java.util.List;

import Objects.JPWord.JPWordType;

public class SenseRelation {

	private int success;
	private List<Relation> relations;
	private String message;
	
	public class Relation {
		
		private String word;
		private String newWord;
		private String tag;
		private int senseIndex;
		
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public String getNewWord() {
			return newWord;
		}
		public void setNewWord(String newWord) {
			this.newWord = newWord;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		public int getSenseIndex() {
			return senseIndex;
		}
		public void setSenseIndex(int senseIndex) {
			this.senseIndex = senseIndex;
		}
		public JPWordType getWordType() {
			if (tag.equals("v")) {
				return JPWordType.JPWordTypeVerb;
			} else if (tag.equals("n")) {
				return JPWordType.JPWordTypeNoun;
			} else if (tag.equals("a")) {
				return JPWordType.JPWordTypeAdjective;
			} else if (tag.equals("r")) {
				return JPWordType.JPWordTypeAdverb;
			}
			
			
			return JPWordType.JPWordTypeUnknown;
		}
		
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
