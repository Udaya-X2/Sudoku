package sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class sudokuMain
{	
	public static void main(String[] args)
	{
		int[][] sudoku = {{0, 0, 3, 0, 2, 0, 6, 0, 0},
				  {9, 0, 0, 3, 0, 5, 0, 0, 1},
				  {0, 0, 1, 8, 0, 6, 4, 0, 0},
				  {0, 0, 8, 1, 0, 2, 9, 0, 0},
				  {7, 0, 0, 0, 0, 0, 0, 0, 8},
				  {0, 0, 6, 7, 0, 8, 2, 0, 0},
				  {0, 0, 2, 6, 0, 9, 5, 0, 0},
				  {8, 0, 0, 2, 0, 3, 0, 0, 9},
				  {0, 0, 5, 0, 1, 0, 3, 0, 0}};
		boolean changed = false;
		
		for (int j = 0; j < sudoku.length; j++)
		{
			for (int k = 0; k < sudoku[j].length; k++)
			{
				if (sudoku[j][k] == 0)
				{
					sudoku[j][k] = getCandidate(j, k, sudoku);
					
					if (sudoku[j][k] != 0)
					{
						changed = true;
					}
				}	
			}
			
			if (j + 1 == sudoku.length && changed)
			{
				j = -1;
				changed = false;
			}
		}
		
		for (int i = 0; i < sudoku.length; i++)
		{
			System.out.println(Arrays.toString(sudoku[i]));
		}
	}
	
	public static int getCandidate(int row, int col, int[][] myArray)
	{
		ArrayList<Integer> options = boxSolver(row, col, myArray);
		
		if (options.size() == 1)
		{
			return options.get(0);
		}
		else
		{
			String possibilities = String.valueOf(options);
			
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
						if (options.contains(temp.get(i)))
						{
							options.remove(temp.get(i));
						}
					}
				}
			}
			
			if (options.size() == 1)
			{
				System.out.println("(" + row + ", " + col + "): " + options);
				return options.get(0);
			}
			else
			{
				System.out.println("(" + row + ", " + col + "): " + possibilities);
				return 0;
			}
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
				if (sudokuSearches.rowSearch(row, i, myArray))
				{
					options.remove(options.indexOf(i));
				}
				else if (sudokuSearches.colSearch(col, i, myArray))
				{
					options.remove(options.indexOf(i));
				}
				else if (sudokuSearches.squareSearch(row, col, i, myArray))
				{
					options.remove(options.indexOf(i));
				}
		}
		
		System.out.println("(" + row + ", " + col + "): " + options);
		
		return options;
	}
}
