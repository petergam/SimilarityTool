package Deprecated;

import edu.mit.jwi.item.POS;

public class Word {
//
//	public enum Type {
//		UNKNOWN,
//		COORDINATING_CONJUNCTION,
//		CARDINAL_NUMBER,
//		DETERMINER,
//		EXSISTENTIAL_THERE,
//		FOREIGN_WORD,
//		SUBORDINATING_CONJUNCTION,
//		ADJECTIVE,
//		COMPARATIVE_ADjECTIVE,
//		SUPERLATIVE_ADJECTIVE,
//		LIST_ITEM_MARK,
//		MODAL,
//		SINGULAR_NOUN,
//		SINGULAR_PROPER_NOUN,
//		PLURAL_PROPER_NOUN,
//		PLURAL_NOUN,
//		PREDETERMINER,
//		POSSESSIVE_ENDING,
//		PERSONAL_PRONOUN,
//		POSSSESSIVE_PRONOUN,
//		ADVERB,
//		COMPARATIVE_ADVERB,
//		SUPERLATIVE_ADVERB,
//		PARTICLE,
//		SYMBOL,
//		TO,
//		INTERJECTION,
//		VERB,
//		PAST_TENSE_VERB,
//		PRESENT_PARTICIPLE_VERB,
//		PAST_PARTICIPLE_VERB,
//		NON_THIRD_PERSON_SINGULAR_PRESENT,
//		THIRD_PERSON_SINGULAR_PRESENT,
//		WH_DETERMINER,
//		WH_PRONOUN,
//		POSSESIVE_WH_PRONOUN,
//		WH_ADVERB
//	}
//	
//	private Type type;
//	private String text;
//	private int count;
//	
//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}
//
//	public Word() {
//		this.count = 1;
//	}
//	
//	public Word(String text, Type type) {
//		this.text = text;
//		this.type = type;
//	}
//	
//	public Type getType() {
//		return type;
//	}
//	public void setType(Type type) {
//		this.type = type;
//	}
//	
//	public String getText() {
//		return text;
//	}
//	
//	public void setText(String text) {
//		this.text = text;
//	}
//	
//	public void setStyleFromTag(String tag) {
//		if(tag.equals("CC")) {
//			type = Type.COORDINATING_CONJUNCTION;
//		}else if(tag.equals("CD")) {
//			type = Type.CARDINAL_NUMBER;
//
//		}else if(tag.equals("DT")) {
//			type = Type.DETERMINER;
//		}else if(tag.equals("EX")) {
//			type = Type.EXSISTENTIAL_THERE;
//		}else if(tag.equals("FW")) {
//			type = Type.FOREIGN_WORD;
//		}else if(tag.equals("IN")) {
//			type = Type.SUBORDINATING_CONJUNCTION;
//		}else if(tag.equals("JJ")) {
//			type = Type.ADJECTIVE;
//		}else if(tag.equals("JJR")) {
//			type = Type.COMPARATIVE_ADjECTIVE;
//		}else if(tag.equals("JJS")) {
//			type = Type.SUPERLATIVE_ADJECTIVE;
//		}else if(tag.equals("LS")) {
//			type = Type.LIST_ITEM_MARK;
//		}else if(tag.equals("MD")) {
//			type = Type.MODAL;
//		}else if(tag.equals("NN")) {
//			type = Type.SINGULAR_NOUN;
//		}else if(tag.equals("NNP")) {
//			type = Type.SINGULAR_PROPER_NOUN;
//		}else if(tag.equals("NNPS")) {
//			type = Type.PLURAL_PROPER_NOUN;
//		}else if(tag.equals("NNS")) {
//			type = Type.PLURAL_NOUN;
//		}else if(tag.equals("PDT")) {
//			type = Type.PREDETERMINER;
//		}else if(tag.equals("POS")) {
//			type = Type.POSSESSIVE_ENDING;
//		}else if(tag.equals("PRP")) {
//			type = Type.PERSONAL_PRONOUN;
//		}else if(tag.equals("PRP$")) {
//			type = Type.POSSSESSIVE_PRONOUN;
//		}else if(tag.equals("RB")) {
//			type = Type.ADVERB;
//		}else if(tag.equals("RBR")) {
//			type = Type.COMPARATIVE_ADVERB;
//		}else if(tag.equals("RBS")) {
//			type = Type.SUPERLATIVE_ADVERB;
//		}else if(tag.equals("RP")) {
//			type = Type.PARTICLE;
//		}else if(tag.equals("SYM")) {
//			type = Type.SYMBOL;
//		}else if(tag.equals("TO")) {
//			type = Type.TO;
//		}else if(tag.equals("UH")) {
//			type = Type.INTERJECTION;
//		}else if(tag.equals("VB")) {
//			type = Type.VERB;
//		}else if(tag.equals("VBD")) {
//			type = Type.PAST_TENSE_VERB;
//		}else if(tag.equals("VBG")) {
//			type = Type.PRESENT_PARTICIPLE_VERB;
//		}else if(tag.equals("VBN")) {
//			type = Type.PAST_PARTICIPLE_VERB;
//		}else if(tag.equals("VBP")) {
//			type = Type.NON_THIRD_PERSON_SINGULAR_PRESENT;
//		}else if(tag.equals("VBZ")) {
//			type = Type.THIRD_PERSON_SINGULAR_PRESENT;
//		}else if(tag.equals("WDT")) {
//			type = Type.WH_DETERMINER;
//		}else if(tag.equals("WP")) {
//			type = Type.WH_PRONOUN;
//		}else if(tag.equals("WP$")) {
//			type = Type.POSSESIVE_WH_PRONOUN;
//		}else if(tag.equals("WRB")) {
//			type = Type.WH_ADVERB;
//		}else {
//			type = Type.UNKNOWN;
//		}
//	}
//	
//	public POS getPos() {
//		return Word.posFromType(this.type);
//	}
//	
//	public static POS posFromType(Type type) {
//		switch (type) {
//		case UNKNOWN:
//		case COORDINATING_CONJUNCTION:
//		case CARDINAL_NUMBER:
//		case DETERMINER:
//		case EXSISTENTIAL_THERE:
//		case FOREIGN_WORD:
//		case SUBORDINATING_CONJUNCTION:
//		case ADJECTIVE:
//		case COMPARATIVE_ADjECTIVE:
//		case SUPERLATIVE_ADJECTIVE:
//			return POS.ADJECTIVE;
//		case LIST_ITEM_MARK:
//		case MODAL:
//		case SINGULAR_NOUN:
//		case SINGULAR_PROPER_NOUN:
//		case PLURAL_PROPER_NOUN:
//		case PLURAL_NOUN:
//		case PREDETERMINER:
//		case POSSESSIVE_ENDING:
//		case PERSONAL_PRONOUN:
//		case POSSSESSIVE_PRONOUN:
//		case WH_PRONOUN:
//		case POSSESIVE_WH_PRONOUN:
//			return POS.NOUN;
//		case ADVERB:
//		case COMPARATIVE_ADVERB:
//		case SUPERLATIVE_ADVERB:
//			return POS.ADVERB;
//		case PARTICLE:
//		case SYMBOL:
//		case TO:
//		case INTERJECTION:
//		case VERB:
//		case PAST_TENSE_VERB:
//		case PRESENT_PARTICIPLE_VERB:
//		case PAST_PARTICIPLE_VERB:
//		case NON_THIRD_PERSON_SINGULAR_PRESENT:
//		case THIRD_PERSON_SINGULAR_PRESENT:
//		case WH_DETERMINER:
//		case WH_ADVERB:
//			return POS.VERB;
//
//		default:
//			return POS.NOUN;
//		}
//		
//	}
}
