// Kalonte Jackson-Tate
// NID: ka358209
// COP 3503 Fall 2017
// SneakyKnights.java

import java.io.*;
import java.util.*;
//Using this import to specify points with integer precision
import java.awt.Point;

public class SneakyKnights
{
	//Converts a pair of coordinates to a specific value
	public static int colConvert(String colCoord)
	{
		int temp, colVal, ret, conLen;
		int j;
		ret = 0;
		temp = 0;
		conLen = colCoord.length();
		colVal = (conLen-1);
		for(j = 0; j < conLen; j++)
		{
			// Converts the character
			temp = ((int)colCoord.charAt(j))-'a'+1;
			ret += temp * (int)Math.pow(26,colVal);
			colVal--;
		}
		return ret;
	}
	
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		int row, col, length;
		int i;
		// Declaring chessboard
		HashSet<Point> chessBoard = new HashSet<Point>();
		length = coordinateStrings.size();
		for(i = 0; i < length; i++)
		{
			ArrayList<String> coordBoard = new ArrayList<String> (Arrays.asList((coordinateStrings.get(i)).split("(?=\\d)(?=\\D)|(?<=\\D)(?=\\d)")));
			row = Integer.parseInt(coordBoard.get(1));
			// Converting the column letter coord into a numerical value
			col = colConvert(coordBoard.get(0));
			
			// Declaring point knight is positoned at
			Point loc = new Point(col, row);
			
			// Declaring possible points for piece to move to w/o checking to see if its open or in bounds
			Point locSE = new Point(col-1, row-2);
			Point locNW = new Point(col-1, row+2);
			Point locSW = new Point(col+1, row-2);
			Point locNE = new Point(col+1, row+2);
			Point locWN = new Point(col-2, row+1);
			Point locWS = new Point(col-2, row-1);
			Point locEN = new Point(col+2, row+1);
			Point locES = new Point(col+2, row-1);
			chessBoard.add(loc);
			
			// Various Checks to see if this Knight is targetable in its' current position by other knights
			if((col+1) <= boardSize && (row+2) <= boardSize){
				if(chessBoard.contains(locNE)){
				return false;}
			}
			if((col+1) <= boardSize && (row-2) > 0){
				if(chessBoard.contains(locSW)){
				return false;}
			}
			if((col-1) > 0 && (row+2) <= boardSize){
				if(chessBoard.contains(locNW)){
				return false;}
			}
			if((col-1) > 0 && (row-2) > 0){
				if(chessBoard.contains(locSE)){
				return false;}
			}
			if((col+2) <= boardSize && (row+1) <= boardSize){
				if(chessBoard.contains(locEN)){
				return false;}
			}
			if((col+2) <= boardSize && (row-1) > 0){
				if(chessBoard.contains(locES)){
				return false;}
			}
			if((col-2) > 0 && (row+1) <= boardSize){
				if(chessBoard.contains(locWN)){
				return false;}
			}
			if((col-2) > 0 && (row-1) > 0){
				if(chessBoard.contains(locWS)){
				return false;}
			}
		}
		// Returns true if all knights are clear
		return true;
		
	}

// Had trouble at first with parsing the coordinates and assigning a numerical value to the coordinate
// in a way that also allowed me to run the checks above. Overall I'd say this was a medium difficulty assignment
public static double difficultyRating()
{
	return 3.0;
}

public static double hoursSpent()
{
	return 5.5;
}
}