package sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuMain
{
	public static void main(String[] args)
	{
		int[][] sudoku = {{5, 0, 0, 0, 9, 0, 6, 0, 0},
						  {0, 4, 0, 0, 1, 0, 0, 0, 3},
						  {0, 0, 7, 8, 0, 0, 0, 0, 2},
						  {0, 9, 0, 3, 2, 0, 0, 6, 0},
						  {0, 3, 0, 1, 0, 4, 0, 5, 0},
						  {0, 7, 0, 0, 5, 9, 0, 3, 0},
						  {7, 0, 0, 0, 0, 2, 1, 0, 0},
						  {2, 0, 0, 0, 6, 0, 0, 7, 0},
						  {0, 0, 1, 0, 8, 0, 0, 0, 6}};
		solveSudoku(sudoku);
		
		if (guessAndCheck(sudoku))
		{	
			for (int i = 0; i < sudoku.length; i++)
			{
				System.out.println(Arrays.toString(sudoku[i]));
			}
		}
		else
		{
			System.out.println("This Sudoku is unsolvable.");
		}
	}
	
	public static void solveSudoku(int[][] myArray)
	{
		boolean changed = false;
		
		for (int j = 0; j < myArray.length; j++)
		{
			for (int k = 0; k < myArray[j].length; k++)
			{
				if (myArray[j][k] == 0)
				{
					myArray[j][k] = getCandidate(j, k, myArray);
					
					if (myArray[j][k] != 0)
					{
						changed = true;
					}
				}	
			}
			
			if (j + 1 == myArray.length && changed)
			{
				j = -1;
				changed = false;
			}
		}
	}
	
	public static int getCandidate(int row, int col, int[][] myArray)
	{
		ArrayList<Integer> options = boxSolver(row, col, myArray);
		
		if (options.size() == 1)
		{
//			System.out.println("(" + row + ", " + col + "): " + options);
			return options.get(0);
		}
		else
		{
			ArrayList<Integer> rowCandidates = new ArrayList<Integer>(options);
			ArrayList<Integer> colCandidates = new ArrayList<Integer>(options);
			ArrayList<Integer> squareCandidates = new ArrayList<Integer>(options);
			
			for (int j = 0; j < myArray[row].length; j++)
			{
				ArrayList<Integer> temp = new ArrayList<Integer>();
				
				if (myArray[row][j] == 0 && j != col)
				{
					temp = boxSolver(row, j, myArray);
				}
				
				for (int k = 0; k < temp.size(); k++)
				{
					if (rowCandidates.contains(temp.get(k)))
					{
						rowCandidates.remove(temp.get(k));
					}
				}
			}
			
			if (rowCandidates.size() == 1)
			{
//				System.out.println("(" + row + ", " + col + "): " + rowCandidates);
				return rowCandidates.get(0);
			}
			
			for (int j = 0; j < myArray.length; j++)
			{
				ArrayList<Integer> temp = new ArrayList<Integer>();
				
				if (myArray[j][col] == 0 && j != row)
				{
					temp = boxSolver(j, col, myArray);
				}
				
				for (int k = 0; k < temp.size(); k++)
				{
					if (colCandidates.contains(temp.get(k)))
					{
						colCandidates.remove(temp.get(k));
					}
				}
			}
			
			if (colCandidates.size() == 1)
			{
//				System.out.println("(" + row + ", " + col + "): " + colCandidates);
				return colCandidates.get(0);
			}
			
			for (int j = row/3 * 3; j < row/3 * 3 + 3; j++)
			{
				for (int k = col/3 * 3; k < col/3 * 3 + 3; k++)
				{
					ArrayList<Integer> temp = new ArrayList<Integer>();
					
					if (myArray[j][k] == 0 && !(j == row && k == col))
					{
						temp = boxSolver(j, k, myArray);
					}
					
					for (int i = 0; i < temp.size(); i++)
					{
						if (squareCandidates.contains(temp.get(i)))
						{
							squareCandidates.remove(temp.get(i));
						}
					}
				}
			}
			
			if (squareCandidates.size() == 1)
			{
//				System.out.println("(" + row + ", " + col + "): " + squareCandidates);
				return squareCandidates.get(0);
			}
			
//			System.out.println("(" + row + ", " + col + "): " + options);
			return 0;
		}
	}
	
	public static ArrayList<Integer> boxSolver(int row, int col, int[][] myArray)
	{
		ArrayList<Integer> options = new ArrayList<Integer>();
		
		for (int i = 1; i <= 9; i++)
		{
			options.add(i);
		}
		
		for (int i = 1; i <= 9; i++)
		{
				if (SudokuSearches.rowSearch(row, i, myArray))
				{
					options.remove(options.indexOf(i));
				}
				else if (SudokuSearches.colSearch(col, i, myArray))
				{
					options.remove(options.indexOf(i));
				}
				else if (SudokuSearches.squareSearch(row, col, i, myArray))
				{
					options.remove(options.indexOf(i));
				}
		}
		
		return options;
	}
	
	public static boolean guessAndCheck(int[][] myArray)
	{
		for (int j = 0; j < myArray.length; j++)
		{
			for (int k = 0; k < myArray[j].length; k++)
			{
				if (myArray[j][k] == 0)
				{
					ArrayList<Integer> options = boxSolver(j, k, myArray);
					
					for (int i = 0; i < options.size(); i++)
					{
						myArray[j][k] = options.get(i);
						
						if (guessAndCheck(myArray))
						{
							return true;
						}
						else
						{
							myArray[j][k] = 0;
						}
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
}
