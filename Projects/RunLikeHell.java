// Kalonte Jackson-Tate
// NID: ka358209
// COP 3503 Fall 2017
// RunLikeHell.java

import java.io.*;
import java.util.*;

public class RunLikeHell
{
	// DP solution with O(n) runtime and O(1) Space Complexcity
	public static int maxGain(int [] blocks)
	{
    int size = blocks.length;
		int sumVal[] = new int[2];
    // For Loop that checks all possible solutions and saves the max
    // whether we choose to take it or not
		for(int i = 0; i < size; i++){
      // Saves the sums of the values taken at 0 or 1 in the array
		sumVal[i % 2] = Math.max(blocks[i] + sumVal[i % 2], sumVal[(i + 1) % 2]);
    }
    // Returns Value
		if(size % 2 != 0){
			return sumVal[0];
    }
    else{
		return sumVal[1];
    }
	}

  // Returns the difficulty rating of this assignment
	public static double difficultyRating()
	{
		return 2;
	}

	// Returns the number of hours spent on this assignment
	public static double hoursSpent()
	{
		return 1.5;
	}
}
