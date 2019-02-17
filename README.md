# Sudoku
Sudoku is a logic-based, combinatorial number-placement puzzle. The objective is to fill a 9×9 grid with digits so that each column, each row, and each of the nine 3×3 subgrids that compose the grid contain all of the digits from 1 to 9. The puzzle setter provides a partially completed grid, which for a well-posed puzzle has a single solution.

For example, the following is a typical Sudoku puzzle, and the solution to its right:

![https://i.imgur.com/q3LjhDz.png](https://i.imgur.com/q3LjhDz.png)

# This Project
This program intends to use several Java classes to solve any Sudoku puzzle. First, by searching every row, column, and 3x3 square to eliminate any possibilities down to one number for a 1x1 box. Then, by searching every corresponding row, column, and 3x3 square to see if only that box has a distinct number choice. Finally, if all other methods fail, the program will guess and check numbers until it gets the correct one. Through this, the correct Sudoku answer should be revealed.
