import java.util.Scanner;

public class Main {

    public static int[][] inputMatrix(Scanner scanner, int row, int col, String name){
        /*
         * inputMatrix - generate matrix from user input;
         * scanner: system method to accept input from user;
         * row: number of rows for the matrix;
         * column: number of column for the matrix
         * returns a 2d matrix 
         */
        System.out.println("Enter values for " + name + "( "+ row + " x " + col + " ):");
        int[][] matrix = new int[row][col];
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){           
                try {
                    System.out.printf("Enter value for %S row %d col %d: ", name, i, j);
                    matrix[i][j] = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Input must be an integer");
                }
            }
        }

        return matrix;
    }

    public static int[][] multiplyMatrix(int[][] matrixA, int[][] matrixB){
        /*
         * multiplyMatrix - accept two matrices and multiply them
         * matrixA: this is the first matrix
         * matrixB: this is the second matrix
         * returns result of the two matrix which is also a matrix
         */
        int rowC = matrixA.length;
        int colC = matrixB[0].length;
        int colsA = matrixA[0].length;

        int[][] matrixC = new int[rowC][colC];
        
        for(int i = 0; i < rowC; i++){
            for(int j = 0; j < colC; j++){
                for(int k = 0; k < colsA; k++){
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j]; 
                }
            }
        }

        return matrixC;
    }

    public static void printmatrix(int[][] matrix){
        /*
         * print matrix - print the element in the matrix
         * matrix: accept matrix o be printed
         * returns elementt in the matrix formated
         */
      
        for(int i = 0; i <  matrix.length; i++){
            System.out.print("| ");
            for(int j = 0; j < matrix[i].length ; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("|");
            System.out.println("");
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int rowA;
        int colsA;
        int colsB;

        while(true){
            try {
                System.out.print("Enter number of rows for matrix A: ");
                rowA = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter number of col for matrix A: ");
                colsA = Integer.parseInt(scanner.nextLine());

                if (rowA > 0 && colsA > 0){
                    break;
                }

                System.out.println("Rows and columns must be positive");

            } catch ( NumberFormatException e) {
                System.out.println("Input must be an integer");
            }
         
        }
        while(true){
            try {
                System.out.print("Enter number of columns for b: ");
                colsB =Integer.parseInt(scanner.nextLine());

                if (colsB > 0){
                    break;
                }

                System.out.println("Integer must be positive");
        
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer");
            }
        }

        int[][] matrixA = inputMatrix(scanner, rowA, colsA, "Matrix A");
        int[][] matrixB = inputMatrix(scanner,colsA, colsB, "Matrix B");

        int[][] matrixC = multiplyMatrix(matrixA, matrixB);

        printmatrix(matrixC);
        scanner.close();
    }
}
