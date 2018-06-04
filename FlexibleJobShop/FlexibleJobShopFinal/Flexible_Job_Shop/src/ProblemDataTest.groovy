class ProblemDataTest extends GroovyTestCase {


    void testPrintMatrice() {
        ProblemData pb1 = new ProblemData("test.txt");
        pb1.printMatrice();
    }

    void testGetJob() {
        ProblemData pb1 = new ProblemData("test.txt");
        pb1.printMatrice();

        System.out.println("\nOn va print le Job 1 ")
        Iterator<Task> it = pb1.getJob(1).iterator();
        while (it.hasNext()){
            Task task = it.next();
            System.out.print(task.toString());
        }
    }

    void testGetTaskFromJob() {
        ProblemData pb1 = new ProblemData("test.txt");
        pb1.printMatrice();

        System.out.println("\nOn va print la task 1 du job 1");
        pb1.getTaskFromJob(1,1).printTasks();

    }

    void testGetMinTimForJob() {
        ProblemData pb1 = new ProblemData("test.txt");
        pb1.printMatrice();

        System.out.println("\nTemps min pour Job1 :" + pb1.getMinTimForJob(1));
    }

    void testGetMaxTimForJob() {
        ProblemData pb1 = new ProblemData("test.txt");
        pb1.printMatrice();

        System.out.println("\nTemps max pour Job1 :" + pb1.getMaxTimForJob(1));
    }

    void testGetNbOfJobs() {
        ProblemData pb1 = new ProblemData("test.txt");
        pb1.printMatrice();

        System.out.println("\nNombre de Jobs :" + pb1.getNbOfJobs());
    }
}
