package Test;

import java.io.File;

import org.testng.annotations.DataProvider;

import Objects.JPDocument;
import Objects.JPSentence;
import Objects.JPWord;

public class DataProviderTest {

	private Object[][][] sentences = new Object[][][] {
			{ { "this", "DT", 0, 0, "thi" }, { "is", "VBZ", 1, 1, "is" },
					{ "a", "DT", 1, 0, "a" }, { "sentence", "NN", 1, 1, "sentenc" } },
			{ { "hello", "UH", 1, 0, "hello" }, { "my", "PRP$", 0, 0, "my" },
					{ "name", "NN", 1, 1, "name" }, { "is", "VBZ", 1, 1, "is" },
					{ "Peter", "NNP", 1, 1, "Peter" } } };

	private Object[][] files = new Object[][] {
			{
					new File("Texts/HP1.txt"),
					583,
					"Millions of fans worldwide lined up at the stroke of midnight to get their hands on Harry Potter and the Deathly Hallows, the final installment in author J.K. Rowling's magnus opus of a boy wizard's epic fight against the evil Lord Voldemort. I am pleased to report that the hype was well deserved. The Deathly Hallows is a grand finale. It is a fitting conclusion, filled with darkness and triumph, that will have Potter fans rejoicing. Rowling's narrative is spellbinding, every page seamlessly flowing into the next. She skillfully takes ten years and seven novels to a climax worthy of literary greatness.\nThe Deathly Hallows begins with Lord Voldemort and his Death Eaters firmly in control. The Ministry of Magic has fallen and The Order of the Phoenix, those loyal to the deceased Albus Dumbledore, face death at every turn. Harry, Ron, and Hermione are running for their lives. Severus Snape has been instituted as Headmaster of Hogwarts by the new Voldemort regime. It is a dark and hopeless time in the wizarding world.\nHarry knows that he must find and destroy the seven hidden pieces of Voldemort's soul, horcruxes, but struggles to decipher the mysterious clues left behind by Dumbledore. Even more disturbing are revelations that Dumbledore himself once practiced dark magic. And that he had a family that was undone by his tragic actions. The weight of the war takes its toll and despair begins to creep into Harry's psyche. But the battle is not his alone. Harry is surrounded by loyalty. Hermione, whose cunning and brilliance has always been extolled, proves that she is the greatest witch of her day. Ron, and the entire Weasley family, are courageous to the end. They help Harry to discover Dumbledore's greatest secret, the awesome power of The Deathly Hallows, three magical objects that can conquer death itself.\nThe question every fan has been asking is which characters die. They won't be disappointed, because there is death in spades. Many characters die and some of them not well. They are tortured, maimed, and killed in a variety of disturbing ways. Rowling does not retreat from the horrors of evil. Voldemort is Satan incarnate here, a monster with capability and drive. This is the battle of good versus evil that fans have been waiting to see.\nThe Deathly Hallows is packed to the brim with action. There are chases, spectacular wizarding duels, and all-out battle scenes that I\'m sure has Warner Brothers moist in anticipation of the film. But to J.K. Rowling's continued credit, none of it is overdone or unnecessary. The characters are fighting a long and brutal war. There are battles to be won and tragedies to be had. Such is the nature of war, which she never trivializes. Every loss is felt and has meaning in the context of the story.\nRowling also gets top marks for her superb character development. This is not the Harry, Ron, and Hermione from The Sorcerer\'s Stone. I love that they are mature. The characters handle the deadly issues they face with realistic emotional responses. There\'s no contrivances or melodrama here. I believe this is J.K. Rowling\'s greatest strength as a writer. She\'s highly imaginative, but her skill in writing dialogue and building tension is amongst the best writers of all time. She can take great pride in writing not one, but seven masterful works of fiction.\nI\'m too close to having read the book to judge it against the others in the series. It's a stellar conclusion to an incredible body of work. Fan reaction will be gushing, as it almost always is, but I think the critics will like this one as well. At the end of the day it\'s a fantastic read and that\'s all that you want in a great book." },
			{
					new File("Texts/HP2.txt"),
					1101,
					"So, here it is at last: The final confrontation between Harry Potter, the Boy Who Lived, the Chosen One, the “symbol of hope” for both the Wizard and Muggle worlds, and Lord Voldemort, He Who Must Not Be Named, the nefarious leader of the Death Eaters and would-be ruler of all. Good versus Evil. Love versus Hate. The Seeker versus the Dark Lord.\nJ. K. Rowling’s monumental, spellbinding epic, 10 years in the making, is deeply rooted in traditional literature and Hollywood sagas — from the Greek myths to Dickens and Tolkien to “Star Wars.” And true to its roots, it ends not with modernist, “Soprano”-esque equivocation, but with good old-fashioned closure: a big-screen, heart-racing, bone-chilling confrontation and an epilogue that clearly lays out people’s fates. Getting to the finish line is not seamless — the last part of “Harry Potter and the Deathly Hallows,” the seventh and final book in the series, has some lumpy passages of exposition and a couple of clunky detours — but the overall conclusion and its determination of the main characters’ story lines possess a convincing inevitability that make some of the prepublication speculation seem curiously blinkered in retrospect.\nWith each installment, the “Potter” series has grown increasingly dark, and this volume — a copy of which was purchased at a New York City store yesterday, though the book is embargoed for release until 12:01 a.m. on Saturday — is no exception. While Ms. Rowling’s astonishingly limber voice still moves effortlessly between Ron’s adolescent sarcasm and Harry’s growing solemnity, from youthful exuberance to more philosophical gravity, “Deathly Hallows” is, for the most part, a somber book that marks Harry’s final initiation into the complexities and sadnesses of adulthood.\nFrom his first days at Hogwarts, the young, green-eyed boy bore the burden of his destiny as a leader, coping with the expectations and duties of his role, and in this volume he is clearly more Henry V than Prince Hal, more King Arthur than young Wart: high-spirited war games of Quidditch have given way to real war, and Harry often wishes he were not the de facto leader of the Resistance movement, shouldering terrifying responsibilities, but an ordinary teenage boy — free to romance Ginny Weasley and hang out with his friends.\nHarry has already lost his parents, his godfather Sirius and his teacher Professor Dumbledore (all mentors he might have once received instruction from) and in this volume, the losses mount with unnerving speed: at least a half-dozen characters we have come to know die in these pages, and many others are wounded or tortured. Voldemort and his followers have infiltrated Hogwarts and the Ministry of Magic, creating havoc and terror in the Wizard and Muggle worlds alike, and the members of various populations — including elves, goblins and centaurs — are choosing sides.\nNo wonder then that Harry often seems overwhelmed with disillusionment and doubt in the final installment of this seven-volume bildungsroman. He continues to struggle to control his temper, and as he and Ron and Hermione search for the missing Horcruxes (secret magical objects in which Voldemort has stashed parts of his soul, objects that Harry must destroy if he hopes to kill the evil lord), he literally enters a dark wood, in which he must do battle not only with the Death Eaters, but also with the temptations of hubris and despair.\nHarry’s weird psychic connection with Voldemort (symbolized by the lightning-bolt forehead scar he bears as a result of the Dark Lord’s attack on him as a baby) seems to have grown stronger too, giving him clues to Voldemort’s actions and whereabouts, even as it lures him ever closer to the dark side. One of the plot’s significant turning points concerns Harry’s decision on whether to continue looking for the Horcruxes — the mission assigned to him by the late Dumbledore — or to pursue the Hallows, three magical objects said to make their possessor the master of Death.\nHarry’s journey will propel him forward to a final showdown with his arch enemy, and also send him backward into the past, to the house in Godric’s Hollow where his parents died, to learn about his family history and the equally mysterious history of Dumbledore’s family. At the same time, he will be forced to ponder the equation between fraternity and independence, free will and fate, and to come to terms with his own frailties and those of others. Indeed, ambiguities proliferate throughout “The Deathly Hallows”: we are made to see that kindly Dumbledore, sinister Severus Snape and perhaps even the awful Muggle cousin Dudley Dursley may be more complicated than they initially seem, that all of them, like Harry, have hidden aspects to their personalities, and that choice — more than talent or predisposition — matters most of all.\nIt is Ms. Rowling’s achievement in this series that she manages to make Harry both a familiar adolescent — coping with the banal frustrations of school and dating — and an epic hero, kin to everyone from the young King Arthur to Spider-Man and Luke Skywalker. This same magpie talent has enabled her to create a narrative that effortlessly mixes up allusions to Homer, Milton, Shakespeare and Kafka, with silly kid jokes about vomit-flavored candies, a narrative that fuses a plethora of genres (from the boarding-school novel to the detective story to the epic quest) into a story that could be Exhibit A in a Joseph Campbell survey of mythic archetypes.\nIn doing so, J. K. Rowling has created a world as fully detailed as L. Frank Baum’s Oz or J. R. R. Tolkien’s Middle Earth, a world so minutely imagined in terms of its history and rituals and rules that it qualifies as an alternate universe, which may be one reason the “Potter” books have spawned such a passionate following and such fervent exegesis. With this volume, the reader realizes that small incidents and asides in earlier installments (hidden among a huge number of red herrings) create a breadcrumb trail of clues to the plot, that Ms. Rowling has fitted together the jigsaw-puzzle pieces of this long undertaking with Dickensian ingenuity and ardor. Objects and spells from earlier books — like the invisibility cloak, Polyjuice Potion, Dumbledore’s Pensieve and Sirius’s flying motorcycle — play important roles in this volume, and characters encountered before, like the house-elf Dobby and Mr. Ollivander the wandmaker, resurface, too.\nThe world of Harry Potter is a place where the mundane and the marvelous, the ordinary and the surreal coexist. It’s a place where cars can fly and owls can deliver the mail, a place where paintings talk and a mirror reflects people’s innermost desires. It’s also a place utterly recognizable to readers, a place where death and the catastrophes of daily life are inevitable, and people’s lives are defined by love and loss and hope — the same way they are in our own mortal world." },
			{
					new File("Texts/HP3.txt"),
					747,
					"Harry Potter and the Deathly Hallows (Harry Potter 7) is more than just the seventh and final installment in J.K. Rowling\'s epic wizardry series. It is the keystone, the culmination of the 4000 or so previous pages. Rowling brings to Deathly Hallows a fully-realized world, complete with history, mythology, and a vast web of characters intertwined thoroughly with that history, that mythology, and with each other.\nIt is a darker landscape since Voldemort\'s return to power and Dumbledore\'s subsequent demise at the wand of Severus Snape; many of Voldemort\'s followers have been released from Azkaban as have the Dementors, who now serve the Dark Lord\'s purposes as well. The Ministry of Magic, now controlled by Death Eaters, has instituted a campaign against muggle-borns that smacks of Nazi Germany, and Harry Potter is dubbed \"Undesirable Number One,\" with a 2,000 galleon prize offered for his capture.\nPrior to all this, at the close of Book 6, Harry Potter and the Half-Blood Prince, Harry was left with a task. Before being struck down by Snape, Dumbledore had schooled Harry in the matter of horcruxes, physical objects to which a severed bit of a wizard\'s soul is attached, making that wizard immortal. According to Dumbledore, Lord Voldemort, who desires nothing so much as immortality, had split his soul into seven pieces, six of which were currently residing in horcruxes hidden throughout the world. Two of these were already destroyed - Tom Riddle\'s diary was impaled with a poisonous basilisk fang in Harry Potter and the Chamber of Secrets (Book 2), and a ring passed down frrom Marvolo Gaunt, Voldemort\'s maternal grandfather is destroyed by Dumbledore in Half-Blood Prince. Dumbledore set Harry upon a quest to destroy the remaining four of Voldemort\'s horcruxes, a feat which would then enable Harry to destroy Voldemort himself. And ultimately, Harry must destroy or be destroyed by Voldemort.\nDeathly Hallows opens upon the Order of the Phoenix\'s plan to move Harry from 4 Privet Drive to a safe house before the magical protection surrounding his aunt and uncle\'s home expires on Harry\'s seventeenth birthday. The plan to move Harry involves, as many plans in Book 7 do, the use of polyjuice potion, which allows the drinker to look like another person. Harry, along with six who volunteer to disguise themselves as Harry, are individually escorted into the night sky upon brooms, thestrals, and, in one instance, a flying motorcycle. The ensemble is ambushed instantly by a host of Death Eaters and He-who-must-not-be-named himself, who surprises everyone by having acquired the power of flight. Like a pale-skinned and dark-hearted Superman, Voldemort gives chase through the skies:\n\"And then Harry saw him. Voldemort was flying like smoke on the wind, without broomstick or thestral to hold him, his snakelike face gleaming out of the blackness, his white fingers raising his wand again --\" (p.60)\nHarry has also grown older, wiser since we last saw him. No longer the petulant and angst-ridden teen whose days were punctuated with dark moods and fights with those closest to him, he has matured beyond his years and accepts the impossible tasks before him decisively. The same holds true for Ron and Hermione and as the novel progresses, we see the relationships between the trio mature and triumph in the face of great difficulty.\nThe writing is fantastic as usual. Rowling strikes a perfect balance in attacking her dark subject matter in a manner fitting for both the adult and younger reader. Never once did I feel spoken down to, nor did I come across anything too mature for my ten-year-old daughter, who was reading the novel concurrently.\nI recoil at the thought of further divulging plot details of this book that held my attention througout its 750 pages of twists, turns, revelations, and surprises. A lot happens. Somebody gets his ear blown off; somebody gives birth; someone betrays somebody else. House-elves find themselves involved, as do goblins, and even a dragon. Characters you\'d completely forgotten about show up unexpectedly to play a role in knotting ends that have long been left loose. As already mentioned, there is much polyjuice potion drunk, and a good number of patronuses are thrown along the way (Mr. Weasley\'s is a weasel).\nHarry Potter and the Deathly Hallows exceeds expectations and is, by a long shot, the best yet of the series, which has been entirely transfixing, far more so than fans of the movies might suppose. Those readers who have kept the faith throughout will no doubt continue on if they haven\'t already. Those who are looking for added encouragement to pick up these magical books and forge ahead, find it here. Harry Potter is a rare treasure and a cultural phenomenon in the midst of which we can feel fortunate to find ourselves." },
			{ new File("Texts/EmptyFile.txt"), 0, "" } };

	
	@DataProvider(name = "generate-documents")
	public Object[][] semcorDocumentDataProvider() {		
		
		SemCorParser semCorParser = new SemCorParser();
		JPDocument[] expectedDocuments = semCorParser.parse(new File("semcor3.0/"));
		
		Object[][] returnArray = new Object[expectedDocuments.length][2];
		
		int i = 0;
		for (JPDocument expectedDocument : expectedDocuments) {
			JPDocument newDocument = new JPDocument();
			newDocument.setDocumentTitle(expectedDocument.getDocumentTitle());
			
			for (JPSentence sentence : expectedDocument.getSentenceArray()) {
				JPSentence s = new JPSentence();
				
				for (JPWord word : sentence.getWords()) {
					JPWord w = new JPWord();
					w.setValue(word.getValue());
					s.getWords().add(w);
				}
				newDocument.getSentenceArray().add(s);
			}

			newDocument.setNumberOfWords(expectedDocument.getNumberOfWords());
			
			returnArray[i][0] = newDocument;
			returnArray[i][1] = expectedDocument;
			i++;
		}
		
		return returnArray;
	}
	
	@DataProvider(name = "generate-documentslight")
	public Object[][] semcorDocumentLightDataProvider() {		
		
		SemCorParser semCorParser = new SemCorParser();
		JPDocument[] expectedDocuments = semCorParser.parse(new File("semcorlight3.0/"));
		
		Object[][] returnArray = new Object[expectedDocuments.length][2];
		
		int i = 0;
		for (JPDocument expectedDocument : expectedDocuments) {
			JPDocument newDocument = new JPDocument();
			newDocument.setDocumentTitle(expectedDocument.getDocumentTitle());
			
			for (JPSentence sentence : expectedDocument.getSentenceArray()) {
				JPSentence s = new JPSentence();
				
				for (JPWord word : sentence.getWords()) {
					JPWord w = new JPWord();
					w.setValue(word.getValue());
					s.getWords().add(w);
				}
				newDocument.getSentenceArray().add(s);
			}

			newDocument.setNumberOfWords(expectedDocument.getNumberOfWords());
			
			returnArray[i][0] = newDocument;
			returnArray[i][1] = expectedDocument;
			i++;
		}
		
		return returnArray;
	}

	@DataProvider(name = "fileDataProvider")
	public Object[][] fileDataProvider() {
		return files;
	}

	@DataProvider(name = "generate-documentSense")
	public Object[][] documentSenseDataProvider() {
		JPDocument document = new JPDocument();
		Object[][] senses = new Object[sentences.length][];

		for (int i = 0; i < sentences.length; i++) {
			Object[][] sentence = sentences[i];
			JPSentence s = new JPSentence();

			Object[] sense = new Object[sentence.length];

			for (int j = 0; j < sentence.length; j++) {
				Object[] word = sentence[j];
				JPWord w = new JPWord();
				w.setValue((String) word[0]);
				s.getWords().add(w);
				sense[j] = word[2];
			}

			senses[i] = sense;

			document.getSentenceArray().add(s);
		}

		return new Object[][] { { document, senses } };
	}

	@DataProvider(name = "generate-documentSensePOSTagged")
	public Object[][] documentSensePOSTaggedDataProvider() {
		JPDocument document = new JPDocument();
		Object[][] senses = new Object[sentences.length][];

		for (int i = 0; i < sentences.length; i++) {
			Object[][] sentence = sentences[i];
			JPSentence s = new JPSentence();
			s.setPOSTagged(true);

			Object[] sense = new Object[sentence.length];

			for (int j = 0; j < sentence.length; j++) {
				Object[] word = sentence[j];
				JPWord w = new JPWord();
				w.setValue((String) word[0]);
				w.setTag((String) word[1]);
				s.getWords().add(w);
				sense[j] = word[3];
			}

			senses[i] = sense;

			document.getSentenceArray().add(s);
		}

		return new Object[][] { { document, senses } };
	}
	
	@DataProvider(name = "generate-documentStemming")
	public Object[][] documentStemmingDataProvider() {
		JPDocument document = new JPDocument();
		Object[][] stemmings = new Object[sentences.length][];

		for (int i = 0; i < sentences.length; i++) {
			Object[][] sentence = sentences[i];
			JPSentence s = new JPSentence();
			s.setPOSTagged(true);

			Object[] stemming = new Object[sentence.length];

			for (int j = 0; j < sentence.length; j++) {
				Object[] word = sentence[j];
				JPWord w = new JPWord();
				w.setValue((String) word[0]);
				w.setTag((String) word[1]);
				s.getWords().add(w);
				stemming[j] = word[4];
			}

			stemmings[i] = stemming;

			document.getSentenceArray().add(s);
		}

		return new Object[][] { { document, stemmings } };
	}
	
	@DataProvider(name = "generate-simpleDocumenTagged")
	public Object[][] documentDataProvider() {
		JPDocument document = new JPDocument();

		for (int i = 0; i < sentences.length; i++) {
			Object[][] sentence = sentences[i];
			JPSentence s = new JPSentence();
			s.setPOSTagged(true);

			Object[] sense = new Object[sentence.length];

			for (int j = 0; j < sentence.length; j++) {
				Object[] word = sentence[j];
				JPWord w = new JPWord();
				w.setValue((String) word[0]);
				w.setTag((String) word[1]);
				s.getWords().add(w);
				w.setSenseIndex((Integer) word[2]);
			}


			document.getSentenceArray().add(s);
		}

		return new Object[][] { { document} };
	}

}
