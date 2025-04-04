import java.util.Scanner;

public class Main {
    /*
     * accept user input of grades as string
     * find the maximum, minimum and average grade 
     * create a graph to represent the frequency of grades between ranges
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] gradeInput;
        int[] gradesOfStudent;
        int sumGradesOfStudent = 0;
        int maxGrade = 0;
        int minGrade = Integer.MAX_VALUE;
        double averageGrade;
        int[] stats = new int[5];

        System.out.print("Enter student grades (separated by space): ");
        gradeInput = scanner.nextLine().split(" ");

        gradesOfStudent = new int[gradeInput.length];
        
        
        for(int i = 0; i < gradeInput.length; i++){
            try{
                 System.out.print("Enter grade of a student: ");
                gradesOfStudent[i] = Integer.parseInt(gradeInput[i]);
                sumGradesOfStudent += gradesOfStudent[i];

                if(gradesOfStudent[i] > maxGrade){
                    maxGrade = gradesOfStudent[i];
                }

                if (gradesOfStudent[i] < minGrade){
                    minGrade = gradesOfStudent[i];
                }
            }      catch (NumberFormatException e) {
                    System.err.println("Error: '" + gradeInput[i] + "' is not a valid integer. Skipping...");
                }
           

        }
        System.out.println("");

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

        int maxNumOfStudentInRange = 0;

        for (int studentInRange: stats){
            if (studentInRange > maxNumOfStudentInRange){
                maxNumOfStudentInRange = studentInRange;
            }
        }

      
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
