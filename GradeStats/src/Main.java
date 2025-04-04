import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] gradeInput;
        int[] gradesOfStudent;
        int sumGradesOfStudent = 0;
        int maxGrade = 0;
        int minGrade = Integer.MAX_VALUE;
        double averageGrade;
        int[] stats = new int[5];

        //accept grades of student as input 
        System.out.print("Enter student grades (separated by space): ");
        gradeInput = scanner.nextLine().split(" ");

        // use number of student to sert the size of student grade
        gradesOfStudent = new int[gradeInput.length];
        
        /*
         * type cast student grade stored as string to integer useing the integer wrapper
         * store the integers in the grades of studentarray
         * set maxGrade
         * set minGrade
         */
        for(int i = 0; i < gradeInput.length; i++){
            System.out.print("Enter grade of a student: ");
            gradesOfStudent[i] = Integer.parseInt(gradeInput[i]);
            sumGradesOfStudent += gradesOfStudent[i];

            if(gradesOfStudent[i] > maxGrade){
                maxGrade = gradesOfStudent[i];
            }

            if (gradesOfStudent[i] < minGrade){
                minGrade = gradesOfStudent[i];
            }

        }
        System.out.println("");

        //compute the average grade of student using type casting and math operators
        averageGrade = (double) sumGradesOfStudent / gradesOfStudent.length;

        System.out.println("Values: " + gradesOfStudent.length);
        System.out.println("");
        System.out.println("");
        System.out.println("The maximum grade is: " + maxGrade);
        System.out.println("The minimum grade is: " + minGrade);
        System.out.printf("The average grade is: %f%n", averageGrade);
        System.out.println("");
        System.out.println("");
        System.out.println("");

        /*
         * set the maximum number of student belonging too a particular gradeRange in the stats array
         */
        for (int grades: gradesOfStudent){
            if (grades > 80){
                stats[4]++;
            } else if (grades >= 61){
                stats[3]++;
            } else if (grades >= 41){
                stats[2]++;
            } else if (grades >= 21){
                stats[1]++;
            }else {
                stats[0]++;
            }
        }

        // find the range with the bmaximum number of student 
        int maxNumOfStudentInRange = 0;

        for (int studentInRange: stats){
            if (studentInRange > maxNumOfStudentInRange){
                maxNumOfStudentInRange = studentInRange;
            }
        }

        /*
         * we're using a nested for loop to generate a graph to represent the number of student in each grade range
         */
        for (int i = maxNumOfStudentInRange; i >= 1; i-- ){
            System.out.printf("%3d >", i);{
                for(int j = 0; j < stats.length; j++){
                    if(i > stats[j]){
                        System.out.print("          ");
                    } else {
                        System.out.print("   #######");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("   +-----------+---------+---------+---------+---------+");
        System.out.println("   I    0-20   I  21-40  I  41-60  I  61-80  I  81-100 I");


        




        




        scanner.close();
    }
}
