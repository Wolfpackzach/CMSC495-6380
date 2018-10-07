/*
 * File Name:   GUI.java
 * Class:       CMSC 495-6380
 * Group:       Group 6
 * Authors:     Sheldon Jeffers and Joseph "Zach" Johnson
 * Purpose:     This class digests the data input into the program and parses it out to different parts of the program.
 * 				It is also responsible for performing the operations to calculate victories and rankings for the games.
 * 
 * Revision:
 * Version Number(dates): 1.0(9/29/18) - Created the Game design and methods,  -   Contributor: J.Z.Johnson
 * Version Number(dates): 1.1(10/06/18) - Game functionality, finished methods, point logic    -   Contributor: Sheldon Jeffers
 * Version Number(dates): 1.1(10/07/18) - Comments, method and class cleanup - Contributor: J.Z. Johnson
 */

import java.util.Random;

class Game {

	/*
	 * Default constructor
	 */
	public Game(){
	
	}

	/* Based on Elo system calculate expected player ranks
	 * calculate likelihood of winning based on current ratings
	 * http://tennisabstract.com/reports/atp_elo_ratings.html
	 * 
	 * @param float - rank1
	 * @param float - rank2
	 */
	static float Likelihood(float rank1,float rank2) { 
		float prob = 1.0f * 1.0f / (1 + 1.0f *(float)(Math.pow(10, 1.0f * (rank1 - rank2) / 400))); 
		return prob;
	} 

	/* Based on Elo systems likelihood on winning  , predict winner of the match
	 * then based on Elo system update the players rankings
	 * if a player wins the match ranking increases otherwise decreases
	 * 
	 * @param float - Rank1
	 * @param float - Rank2
	 * @param int - K
	 */
	static float[] EloRating(float Rank1, float Rank2,int K) { 

		// calculate the likelihood that the first player wins the match 
		float Pr1 = Likelihood(Rank2, Rank1); 

		// calculate the likelihood that the second player wins the match 
		float Pr2 = Likelihood(Rank1, Rank2); 

		/* Performance at a tennis match is not only based on players skill level
		 * other factor such as : stadium surface , player condition(e.g slight injury) , motivation
		 * add some randomness and predict the match winner based on elo system probabilities
		 */ 
		Random rand = new Random();
		float ra;
		ra=rand.nextFloat();

		float dif1=0,dif2=0;
		dif1= Math.abs(Pr1 - ra);
		dif2= Math.abs(Pr2 - ra);

		boolean winner=false;

		//first player is the winner , set winner to true
		if (dif1 < dif2){
			winner = true;}

		//second player is the winner , set winner to true
		if (dif2 < dif1){
			winner = false;}

		//if equal prediction , pick the winner only based on initial likelihood
		if (dif1==dif2){
			if (Pr1 > Pr2) {
				winner=true;
			}
			else {
				winner=false;
			}
		}

		System.out.println("Random"+ra+"so winner is "+winner+" Pa = " +Float.toString(Pr1)+ " Pb = " +Float.toString(Pr2)+ " \n");

		/*winning or losing the match influences the rating of each player*/

		// whether  first player wins the increase his/her skill based on  Elo Ratings 
		if (winner == true) { 
			Rank1 = Rank1 + K * (1 - Pr1); 
			Rank2 = Rank2 + K * (0 - Pr2); 
		} 
	
		// whether  second player wins the increase his/her skill based on  Elo Ratings  
		else { 
			Rank1 = Rank1 + K * (0 - Pr1); 
			Rank2 = Rank2 + K * (1 - Pr2); 
		} 

		Rank1=(Math.round( Rank1 * 1000000.0f) / 1000000.0f);
		Rank2=(Math.round(Rank2 * 1000000.0f) / 1000000.0f);
		//return updated ratings
		return new float[] {Rank1, Rank2};
	}
}
