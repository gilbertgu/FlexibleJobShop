import javafx.util.Pair

class SolutionTest extends groovy.util.GroovyTestCase {
    void testGetTime() {

        Solution solution = new Solution("test2.txt")

        System.out.print("Total time:" + solution.getTime());

        solution.print();
    }

    void testPrint() {
        Solution solution= new Solution("test2.txt")

        solution.print();
    }

    void testSwap() {
    }

    void testGetMax() {
        Solution solution = new Solution("test.txt");

        int[] tab= [11,2,5,1,10];

        System.out.println(solution.getMax(tab));
    }

    void testPossibleSwapTasks() {
    }

    void testGetSwapableTasks() {
    }

    void testCreatePairs() {
    }

    void testPrintSwapableList() {
        Solution solution = new Solution("test2.txt")

        solution.printSwapableList(solution.possibleSwapTasks());
    }

    void testgetTime() {
        Solution solution = new Solution("test.txt")

        System.out.print("Total time:" + solution.getTime());
        solution.print()
    }

    void testgetFirstRealisableTask() {
    }


    void testDoSwap() {
        Solution solution = new Solution("test2.txt")

        solution.print()

        System.out.println("\n\nAPRES LE SWAP:" )

        Quartet<Integer,Integer,Integer,Integer> task1 = new Quartet<>(1,3,7,0);
        Quartet<Integer,Integer,Integer,Integer> task2 = new Quartet<>(1,1,9,2);

        Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>> pair = new Pair<>(task1,task2)

        ArrayList<Quartet<Integer,Integer,Integer,Integer>> MAOS = solution.doSwap(0,3)

        solution.print(MAOS)
        solution.setMAOS(MAOS);
        System.out.print("COUT TOTAL": solution.getTime())


    }

    void testSetMAOS() {
    }



    void testHillClimb() {
        int bestSolution = Integer.MAX_VALUE;
        Solution solution= new Solution("test.txt")

        System.out.println("Solution initial: " + solution.getTime());
        solution.print_solution();

        System.out.println("\n\n VOISINAGE")
        for (int i = 0 ; i<100; i++){
            int res = solution.HillClimb();
            System.out.println( i + " Resultat: " + res );
            if (res < bestSolution){
                bestSolution = res;
            }
        }

        System.out.println("final solution :" + bestSolution);

        solution.print_solution();

    }


}

