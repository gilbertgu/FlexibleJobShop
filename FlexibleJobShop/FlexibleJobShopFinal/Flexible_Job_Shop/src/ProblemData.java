import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ProblemData {

    private ArrayList<Task>[] matrix;
    private int sumMachine;

    // Constructeur ------------------------------------------------------------------------------------

    public ProblemData(String myFile) throws FileNotFoundException {

        final File file = new File(myFile);

        try {
            Scanner sc = new Scanner(file);

            this.matrix = new ArrayList[sc.nextInt()];
            this.sumMachine=sc.nextInt();

            int indexTab=0;

            sc.nextLine();

            while (sc.hasNextLine()){
                int taskNb = sc.nextInt();
                this.matrix[indexTab] = new ArrayList<Task>(); // on ajoute un job

                for (int i=1; i<=taskNb; i++){ //on parcour le job
                     int machineNb = sc.nextInt();

                     Task newE = new Task();

                    for (int j=1; j<=machineNb; j++){ //on parcour une tache
                        Integer machine = sc.nextInt();
                        Integer cost = sc.nextInt();
                        newE.addTask(machine,cost);
                    }

                    this.matrix[indexTab].add(newE); // on ajoute la tache

                }
                indexTab++;
                sc.nextLine();
            }
        } catch (FileNotFoundException e) {
        System.err.println("le file n'existe pas");
        }

    }

    // Fonctions ------------------------------------------------------------------------------------------

    public void printMatrice(){
        for (int i=0; i< this.matrix.length; i++){
            System.out.print("Job" + (i+1) + " :");
            Iterator<Task> it = this.matrix[i].iterator();

            while (it.hasNext()){
                it.next().printTasks();
            }
            System.out.println("");
        }
    }

    public ArrayList<Task> getJob(int jobNb) {
        return this.matrix[jobNb-1];
    }

    public Task getTaskFromJob(int job, int taskNb){
        return this.matrix[job].get(taskNb-1); //-1 car l'index d'une Array list commence à 0
    }

    public int getMinTimForJob(int jobNb){
        int result =0;
        Iterator<Task> it = this.matrix[jobNb-1].iterator();
        while (it.hasNext()){
            Task task = it.next();
            result += task.fastMachine().getValue();
        }

        return result;
    }

    public int getMaxTimForJob(int jobNb){
        int result =0;
        Iterator<Task> it = this.matrix[jobNb-1].iterator();
        while (it.hasNext()){
            Task task = it.next();
            result += task.slowMachine().getValue();
        }

        return result;
    }

    public int getNbOfJobs(){
        return this.matrix.length;
    }

    public int getSumMachine(){
        return this.sumMachine;
    }
}
