package Objects;

import java.util.List;

import Objects.JPWord.JPWordType;

// TODO: Auto-generated Javadoc
/**
 * The Class SenseRelation.
 */
public class SenseRelation {

	/** The success. */
	private int success;
	
	/** The relations. */
	private List<Relation> relations;
	
	/** The message. */
	private String message;
	
	/**
	 * The Class Relation.
	 */
	public class Relation {
		
		/** The word. */
		private String word;
		
		/** The new word. */
		private String newWord;
		
		/** The tag. */
		private String tag;
		
		/** The sense index. */
		private int senseIndex;
		
		/**
		 * Gets the word.
		 *
		 * @return the word
		 */
		public String getWord() {
			return word;
		}
		
		/**
		 * Sets the word.
		 *
		 * @param word the new word
		 */
		public void setWord(String word) {
			this.word = word;
		}
		
		/**
		 * Gets the new word.
		 *
		 * @return the new word
		 */
		public String getNewWord() {
			return newWord;
		}
		
		/**
		 * Sets the new word.
		 *
		 * @param newWord the new new word
		 */
		public void setNewWord(String newWord) {
			this.newWord = newWord;
		}
		
		/**
		 * Gets the tag.
		 *
		 * @return the tag
		 */
		public String getTag() {
			return tag;
		}
		
		/**
		 * Sets the tag.
		 *
		 * @param tag the new tag
		 */
		public void setTag(String tag) {
			this.tag = tag;
		}
		
		/**
		 * Gets the sense index.
		 *
		 * @return the sense index
		 */
		public int getSenseIndex() {
			return senseIndex;
		}
		
		/**
		 * Sets the sense index.
		 *
		 * @param senseIndex the new sense index
		 */
		public void setSenseIndex(int senseIndex) {
			this.senseIndex = senseIndex;
		}
		
		/**
		 * Gets the word type.
		 *
		 * @return the word type
		 */
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

	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public int getSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	public void setSuccess(int success) {
		this.success = success;
	}

	/**
	 * Gets the relations.
	 *
	 * @return the relations
	 */
	public List<Relation> getRelations() {
		return relations;
	}

	/**
	 * Sets the relations.
	 *
	 * @param relations the new relations
	 */
	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
