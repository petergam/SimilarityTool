package Algorithms;

import Model.Article;
import Utilities.UtilMethods;

public class LevenshteinDistance {

	
	//http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
	public static int computeLevenshteinDistance(Article a1, Article a2) {

		int a1Size = a1.getWordsArrayList().size();
		int a2Size = a2.getWordsArrayList().size();

		int[][] distance = new int[a1Size + 1][a2Size + 1];

		for (int i = 0; i <= a1Size; i++)
			distance[i][0] = i;
		for (int j = 0; j <= a2Size; j++)
			distance[0][j] = j;

		for (int i = 1; i <= a1Size; i++)
			for (int j = 1; j <= a2Size; j++)
				distance[i][j] = UtilMethods.minimum(
						distance[i - 1][j] + 1,   //deletion
						distance[i][j - 1] + 1,   //insertion
						distance[i - 1][j - 1]
								+ ((a1.getWordsArrayList().get(i - 1).equals(a2
										.getWordsArrayList().get(j - 1))) ? 0
										: 1)); //substitution

		return distance[a1Size][a2Size];
	}
}