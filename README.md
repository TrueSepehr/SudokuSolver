# 9×9 SudokuSolver - Constraint satisfaction problem (CSP)
A 9×9 Sudoku puzzle could be considered as a CSP with 81 variables and one square is one variable. Obviously, the domains of variables are the digits from 1 to 9. The names of 81 variables could be A1 through A9 for the top row (left to right), down to I1 through I9 for the bottom row. A row, column, or box is called a unit. What’s more, there are 27 different constraints: 9 for row, 9 for column and 9 for 3×3 box :

```
Alldiff (A1, A2, A3, A4, A5, A6, A7, A8, A9)
Alldiff (B1, B2, B3, B4, B5, B6, B7, B8, B9)
. . .
Alldiff (A1, B1, C1, D1, E1, F1, G1, H1, I1)
Alldiff (A2, B2, C2, D2, E2, F2, G2, H2, I2)
. . .
Alldiff (A1, A2, A3, B1, B2, B3, C1, C2, C3)
Alldiff (A4, A5, A6, B4, B5, B6, C4, C5, C6)
. . .
```

The following algorithms and heuristics are also used in this project :
- Backtracking
- MRV Heuristic
- Degree Heuristic
- LCV Heuristic
- Forward Checking

Input : 

       { {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
         {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
         {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
         {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
         {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
         {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
         {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
         {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
         {0, 0, 5, 2, 0, 6, 3, 0, 0} }
         
Output :  

          Sudoku is Valid. Solving... (or "Sudoku is Invalid! Try again...")
          "program execution time"
          3 1 6 5 7 8 4 9 2
          5 2 9 1 3 4 7 6 8
          4 8 7 6 2 9 5 3 1
          2 6 3 4 1 5 9 8 7
          9 7 4 8 6 3 1 2 5
          8 5 1 7 9 2 6 4 3
          1 3 8 9 4 7 2 5 6
          6 9 2 3 5 1 8 7 4
          7 4 5 2 8 6 3 1 9
         
    

This is a university project for artificial intelligence course.
