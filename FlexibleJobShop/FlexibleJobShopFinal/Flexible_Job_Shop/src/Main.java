import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {

        int bestSolution = Integer.MAX_VALUE;


        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println();
        System.out.println("###################START JOB SHOP SOLVER###################");
        System.out.println();
        System.out.println("Veullez donner le numéro d'itérations à réaliser: ");
        String inIterations = reader.nextLine();
        int nbIterations = Integer.parseInt(inIterations);
        System.out.println("Veullez donner le \"PATH\" complet de votre fichier de test: ");
        String file = reader.nextLine();
        //once finished
        reader.close();



        //Dessous, veullez donner le "PATH" complet de votre fichier de test.
        Solution solution= new Solution(file);
        ArrayList<Interval<Integer,Integer,Integer,Integer, Integer>> bestInterval = new ArrayList<>(solution.getIntervals());
        System.out.println("Solution initial: " + solution.getTime());
        solution.print_solution();

        System.out.println("\n\n VOISINAGE");
        for (int i = 0 ; i<nbIterations; i++){  //iterations de HillClimb
            int res = solution.HillClimb();
            System.out.println( i + " Resultat: " + res );
            if (res < bestSolution){
                System.out.println( "Changement MAOS " );
                bestSolution = res;
                bestInterval = new ArrayList<>(solution.getIntervals());
            }
        }

        System.out.println("\nFinal solution :" + bestSolution);
        solution.setIntervals(bestInterval);
        solution.print_solution();

        System.out.println();
        System.out.println("##################END###################");
        System.out.println();

    }
}
