
/*	Copyright 2014 Allen Sarkisyan
 *  Contact of the copyright holder and author is available at programminglinguist@gmail.com
  
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SudokuSolver {
	
	public static Boolean checkDuplicateInRows(int[] NewPuzzle){
	
		boolean check = false;
		
		
		for(int k=0;k<81;k+=9){
			
			ArrayList<Integer> newArray = new ArrayList<Integer>();
			
			for(int i=0;i<9;i++){
				newArray.add(NewPuzzle[k+i]);
			}
			
			HashSet arraySet = new HashSet(newArray);
		
			if(arraySet.size() < newArray.size()){
				check = true;
			}
			
		}
		
		return check;
	}
	
	public static int[] solveSingleRow(int[] puzzle){
		
		boolean check = false;
		
		
		for(int k=0;k<81;k+=9){//moves down a column
			
			ArrayList<Integer> newArray = new ArrayList<Integer>();
			
			for(int i=0;i<9;i++){//moves right to capture the row
				newArray.add(puzzle[k+i]);
			}
			
			HashSet arraySet = new HashSet(newArray);
			
			if(arraySet.size() == newArray.size()){//if no duplicates
				
				if(arraySet.contains(0)){//if there is one missing spot in the row, we can find the solution and udpate puzzle
					
					for(int i=1;i<9;i++){
						
						if(!arraySet.contains(i)){
							
							int iLocation = newArray.indexOf(0);
							
							puzzle[k + iLocation] = i;
							
							System.out.println("Number " + i + " should be placed at row " + (1+iLocation) + " column " + (k/9+1));
							
							return puzzle;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return puzzle;
	}
	
	public static Boolean checkDuplicateInCols(int[] NewPuzzle){
		
		boolean check = false;
		
		
		for(int k=0;k<9;k+=1){
			
			ArrayList<Integer> newArray = new ArrayList<Integer>();
			
			for(int i=0;i<81;i+=9){
				newArray.add(NewPuzzle[k+i]);
			}

			HashSet arraySet = new HashSet(newArray);
		
			if(arraySet.size() < newArray.size()){
				check = true;
			}
			
		}
		
		return check;
	}
	
	public static int[] solveSingleCol(int[] puzzle){//need to loop this as many times as there are
		//missing numbers to see if you can get them all. Doesn't work any further than that.
		
		for(int k=0;k<9;k+=1){//per column
			
			ArrayList<Integer> newArray = new ArrayList<Integer>();//stores one column per loop

			for(int i=0;i<81;i+=9){//next row for same column
				newArray.add(puzzle[k+i]);
			}

			HashSet arraySet = new HashSet(newArray);
			
			if(arraySet.size() == newArray.size()){//if no duplicates
				
				if(arraySet.contains(0)){//if there is one missing spot in the column, we can find the solution and udpate puzzle
					
					for(int i=1;i<9;i++){
						
						if(!arraySet.contains(i)){
							
							int iLocation = newArray.indexOf(0);
							
							puzzle[k + iLocation*9] = i;
							
							System.out.println("Number " + i + " should be placed at row " + (1+iLocation) + " column " + (k+1));
							
							return puzzle;
							
						}
						
					}
					
				}
				
			}
			
			
		}
		
		return puzzle;
		
	}
	
	public static Boolean checkDuplicateInBoxes(int[] NewPuzzle){
		
		boolean check = false;
		
		
		for(int j=0;j<61;j+=3){
			
			ArrayList<Integer> newArray = new ArrayList<Integer>();
			
			for(int k=0;k<19;k+=9){
				
				for(int i=0;i<3;i++){
			
					newArray.add(NewPuzzle[j+k+i]);
				
				}
			
			}

			HashSet arraySet = new HashSet(newArray);
		
			if(arraySet.size() < newArray.size()){
				check = true;
			}
			
			if(j==6 || j==33){
				j+=18;
			}
			
		}
		
		return check;
	}
	
	/*public static int[] solveSingleBox(int[] puzzle){
		
		for(int j=0;j<61;j+=3){//goes to next beginning of box, also j changes at end of loop so it's not always +3. 
			
			ArrayList<Integer> newArray = new ArrayList<Integer>();
			ArrayList<int[]> position = new ArrayList<int[]>();
			
			for(int k=0;k<19;k+=9){//goes to next row inside single box
				
				for(int i=0;i<3;i++){//goes left to right in a single for for a single box
					
					position.add({k,i});
					newArray.add(puzzle[j+k+i]);
				
				}
			
			}

			HashSet arraySet = new HashSet(newArray);
		
			if(arraySet.size() < newArray.size()){
				
				if(arraySet.contains(0)){//if there is one missing spot in the column, we can find the solution and update puzzle
					
					for(int i=1;i<9;i++){
						
						if(!arraySet.contains(i)){
							
							int iLocation = newArray.indexOf(0);
							
							puzzle[j + iLocation*9] = i;
							
							System.out.println("Number " + i + " should be placed at row " + (1+iLocation) + " column " + (k+1));
							
							return puzzle;
							
						}
						
					}
					
				}
				
			}
			
			if(j==6 || j==33){
				j+=18;
			}
			
		}
		
		return puzzle;
		
	}*/
	
	public static boolean moreThanNine(int[] tryPuzzle){
		
		for(int i=0;i<9;i++){
			int count = 0;
			for(int k=0;k<tryPuzzle.length;k++){
				if(tryPuzzle[k]==i){
					count++;
				}
				if(count==10){
					return true;
				}
			}
		}
		return false;
	}
	
	public static int countOccurance(int num, int[] puzzle){
		
		int count = 0;
		for(int i=0;i<puzzle.length;i++){
			if(puzzle[i]==num){
				count++;
			}
		}
		return count;
	}
	
	public static int[] makeCombNum(int occurance){
		
		int[] combNum = new int[occurance];
		
		for(int i=0;i<occurance;i++){
			combNum[i]=1;
		}
		return combNum;
	}
	
	public static int[] solvePuzzle(int[] combNum, int[] puzzle){
		
		int[] newArray = new int[81];

		System.arraycopy( puzzle, 0, newArray, 0, puzzle.length );

		int counter=0;
		
		for(int i=0;i<(newArray.length-1);i++){
			
			if(newArray[i]==0){
				newArray[i]=combNum[counter];
				counter++;
			}
			
		}

		return newArray;
	}
	
	public static int[] increaseNum(int[] combNum){
		
		if(combNum[combNum.length-1]!=9){
			combNum[combNum.length-1]+=1;
		}
		else{
			int i=1;
			while(combNum[combNum.length - i]==9){
				i++;
			}
			for(int k=1;k<i;k++){
				combNum[combNum.length - k]=0;
			}
			combNum[combNum.length - i]+=1;
		}
		
		//code to change zeroes to ones since sudoku has no zeros
		
		for(int i=0;i<combNum.length;i++){
			if(combNum[i]==0){
				combNum[i]=1;
			}
		}
		
		return combNum;
		
	}

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		int[] puzzle = {3,7,5,0,2,6,4,8,9,//14 very nicely placed empty spaces < 1 second
						1,0,6,0,8,7,3,0,2,
						4,2,8,5,3,9,6,1,7,
						5,1,7,0,0,8,9,0,3,
						9,4,3,6,7,5,0,0,8,
						0,6,2,3,9,1,5,7,4,
						7,8,1,9,5,4,2,3,0,
						6,3,0,8,1,2,7,0,5,
						2,0,4,7,6,3,8,9,1};
		
		int count = countOccurance(0, puzzle);//this is for empty space count
		System.out.println("Empty Spaces: " + count);
		System.out.println("Finding numbers...");
		
		boolean notSolved = true;
		
		for(int i=0;i<count;i++){
			puzzle = solveSingleRow(puzzle);//still need to do the boxes
			puzzle = solveSingleCol(puzzle);
		}
		
		count = countOccurance(0, puzzle);//this is for solving the rest of the puzzle after solving singles
		int[] combNum = makeCombNum(count);
		while(notSolved){
			int[] tryPuzzle = solvePuzzle(combNum, puzzle);
			if(moreThanNine(tryPuzzle)){//this line until the else if is added and improves from 35 seconds to 2 seconds
				increaseNum(combNum);
			}
			else if(checkDuplicateInRows(tryPuzzle)==true || checkDuplicateInCols(tryPuzzle)==true || checkDuplicateInBoxes(tryPuzzle)==true){
				increaseNum(combNum);
			}
			else{
				notSolved = false;
			}
		}
		
		System.out.println("These are the other missing numbers in order from top to bottom and left to right: " + Arrays.toString(combNum));
		
		long endTime = System.currentTimeMillis();
		System.out.println("Program finished in " + (endTime-startTime)/1000.0 + " seconds");
		
	}
	
}