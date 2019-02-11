package sudoku;

public class sudokuSearches
{
	public static boolean rowSearch(int row, int target, int[][] myArray)
	{
		for (int i = 0; i < myArray[row].length; i++)
		{
			if (myArray[row][i] == target)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean colSearch(int col, int target, int[][] myArray)
	{
		for (int i = 0; i < myArray.length; i++)
		{
			if (myArray[i][col] == target)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean squareSearch(int row, int col, int target, int[][] myArray)
	{
		for (int j = row/3 * 3; j < row/3 * 3 + 3; j++)
		{
			for (int k = col/3 * 3; k < col/3 * 3 + 3; k++)
			{
				if (myArray[j][k] == target)
				{
					return true;
				}
			}
		}
		
		return false;
	}
}