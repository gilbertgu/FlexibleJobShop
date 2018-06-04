import javafx.util.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


public class Solution {

    private ArrayList<Quartet<Integer,Integer,Integer,Integer>> MAOS; 
    private ArrayList<Interval<Integer,Integer,Integer,Integer, Integer>> intervals = new ArrayList<>();
    private int nbJobs;
    private int nbMachine;
    private ProblemData problem;

    public Solution(String file) throws FileNotFoundException {

         problem = new ProblemData(file);
         nbJobs=problem.getNbOfJobs();
         nbMachine = problem.getSumMachine();
         this.MAOS = new ArrayList<Quartet<Integer,Integer,Integer,Integer>>();

         for (int i=0; i<problem.getNbOfJobs(); i++){
             ArrayList<Task> job =problem.getJob(i+1);

             for (int j=0; j<job.size(); j++){
                 Quartet task = new Quartet(i,job.get(j).fastMachine().getKey(),job.get(j).fastMachine().getValue(),j);
                 MAOS.add(task);
             }
         }

     }

     /*
     ###################################################################################################################

     Les fonctions de calcul de cout en fonction d'un solution

     ###################################################################################################################
      */

     public int getTime(){

         ArrayList<Quartet<Integer,Integer,Integer,Integer>> tmpMAOS = new ArrayList<>(this.MAOS);
         int jobTime[]= new int[nbJobs];
         int taskNb[] = new int[nbJobs];
         int machineTime[]= new int[nbMachine];

         int actualMachine =tmpMAOS.get(0).getMachine();
         int index = 0;

         intervals.clear();
         int machineTimeBeforeTaskExec;
         int machineTimeAfterTaskExec;
         int actualJobOfTask;
         int actualMachineOfTask;
         int actualTaskNumber;


         while (tmpMAOS.size()>0){
             Quartet<Integer,Integer,Integer,Integer> task =tmpMAOS.get(index);

             //la machine actuelle possède une tache à réaliser
            if (task.getMachine()==actualMachine && task.getnbTask()==taskNb[task.getJob()]){

                actualJobOfTask = task.getJob();
                actualMachineOfTask = actualMachine;
                actualTaskNumber = task.getnbTask();

                if (machineTime[task.getMachine()-1]<jobTime[task.getJob()]){
                    machineTimeBeforeTaskExec = jobTime[task.getJob()];
                    machineTime[task.getMachine()-1]=jobTime[task.getJob()]+task.getCost();
                }

                else{
                    machineTimeBeforeTaskExec = machineTime[task.getMachine()-1];
                    machineTime[task.getMachine()-1]+=task.getCost();
                }

                jobTime[task.getJob()]=machineTime[task.getMachine()-1];

                taskNb[task.getJob()]++;
                tmpMAOS.remove(task);
                //System.out.println("Une tache supprimé" + task.toString() + "index :" + index);

                if ((index +1) >= tmpMAOS.size() && tmpMAOS.size()!=0) {
                    //System.out.println("On a finis avec la machine:" + task.getMachine());
                    index= getFirstRealisableTask(tmpMAOS,actualMachine,taskNb);
                    actualMachine=tmpMAOS.get(index).getMachine();
                }

                machineTimeAfterTaskExec = machineTime[task.getMachine()-1];
                intervals.add(new Interval<>(machineTimeBeforeTaskExec,machineTimeAfterTaskExec,actualJobOfTask,actualMachineOfTask,actualTaskNumber));


            }
            else if (task.getMachine()==actualMachine && task.getnbTask()!=taskNb[task.getJob()]){ // on est bloqué avec la machine actuelle
                index= getFirstRealisableTask(tmpMAOS,actualMachine,taskNb);
                actualMachine=tmpMAOS.get(index).getMachine();
                //System.out.println("SURPRISE" + " M:" + task.getMachine() +  " " + task.getnbTask() + " " + taskNb[task.getJob()] );
            }
            else if ((index +1) >= tmpMAOS.size()) {
                //System.out.println("On a finis avec la machine:" + task.getMachine());
                index= getFirstRealisableTask(tmpMAOS,actualMachine,taskNb);
                actualMachine=tmpMAOS.get(index).getMachine();
            }

            else{
                //System.out.println(index);
                index++;
            }
         }

             return this.getMax(machineTime);
     }


     public int getTime( ArrayList<Quartet<Integer,Integer,Integer,Integer>> MAOS){

         ArrayList<Quartet<Integer,Integer,Integer,Integer>> tmpMAOS =  new ArrayList<>(MAOS);
         int jobTime[]= new int[nbJobs];
         int taskNb[] = new int[nbJobs];
         int machineTime[]= new int[nbMachine];

         int actualMachine =tmpMAOS.get(0).getMachine();
         int index = 0;

         while (tmpMAOS.size()>0){



             Quartet<Integer,Integer,Integer,Integer> task =tmpMAOS.get(index);

             //la machine actuelle possède une tache à réaliser
            if (task.getMachine()==actualMachine && task.getnbTask()==taskNb[task.getJob()]){

                if (machineTime[task.getMachine()-1]<jobTime[task.getJob()]){
                    machineTime[task.getMachine()-1]=jobTime[task.getJob()]+task.getCost();
                }

                else{
                    machineTime[task.getMachine()-1]+=task.getCost();
                }

                jobTime[task.getJob()]=machineTime[task.getMachine()-1];

                taskNb[task.getJob()]++;
                tmpMAOS.remove(task);
                //System.out.println("Une tache supprimé" + task.toString() + "index :" + index);

                if ((index +1) >= tmpMAOS.size()&& tmpMAOS.size()!=0) {
                    //System.out.println("On a finis avec la machine:" + task.getMachine());
                    index= getFirstRealisableTask(tmpMAOS,actualMachine,taskNb);
                    actualMachine=tmpMAOS.get(index).getMachine();
                }

            }
            else if (task.getMachine()==actualMachine && task.getnbTask()!=taskNb[task.getJob()]){ // on est bloqué avec la machine actuelle
                index= getFirstRealisableTask(tmpMAOS,actualMachine,taskNb);
                actualMachine=tmpMAOS.get(index).getMachine();
            }
            else if ((index +1) >= tmpMAOS.size()) { // on arrive à la fin de la liste
                index= getFirstRealisableTask(tmpMAOS,actualMachine,taskNb);
                actualMachine=tmpMAOS.get(index).getMachine();
            }

            else{
                //System.out.println(index);
                index++;
            }


         }
             return this.getMax(machineTime);
     }

     /*
     ###################################################################################################################

     Les fonctions utilisés dans le calcul de cout

     ###################################################################################################################
      */

     public int getFirstRealisableTask(ArrayList<Quartet<Integer,Integer,Integer,Integer>> tmpMAOS, int machine, int taskNb[]){
        boolean found=false;
        int result = 0;

        int index =0;

        while (!found && index<tmpMAOS.size() && tmpMAOS.size()!=0){


            if ((tmpMAOS.get(index).getnbTask()==taskNb[tmpMAOS.get(index).getJob()])){
                result = tmpMAOS.indexOf(tmpMAOS.get(index));
                found = true;
            }
            index++;
        }

        if (result==-1){
            System.out.println("IL Y A EU UNE ERREUR LA MACHINE SUIVANTE N'EST PAS TROUVEE");
        }
        return result;
     }


     public int getMax(int tab[]){
         int max = 0;

         for (int i=0; i<tab.length;i++){
             if (tab[i]>max){
                 max = tab[i];
             }
         }
         return max;
     }

         /*
     ###################################################################################################################

     La fonction de création d'un voisinnage aleatoire et de recuperation du meilleur voisin

     ###################################################################################################################
      */

    public int findNeighbourhood(){
        ArrayList<Quartet<Integer,Integer,Integer,Integer>> tmp =  new ArrayList<>(this.MAOS);
        int bestCost=this.getTime();
        //this.print();
        int result=bestCost;
        int actualCost;
        boolean notfound=true;

        ArrayList<Quartet<Integer,Integer,Integer,Integer>> bestCurrentSolution =  new ArrayList<>(tmp);
        ArrayList<Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>>> swapList =this.possibleSwapTasks();
        int i =0;

        Collections.shuffle(swapList);
        while(notfound && i < swapList.size()){
            //System.out.println(i + "\n");
            int index1 = this.MAOS.indexOf(swapList.get(i).getKey());
            int index2 = this.MAOS.indexOf(swapList.get(i).getValue());

            tmp= this.doSwap(index1, index2); // on effectue les swap dans notre MAOS

            actualCost=this.getTime(tmp);

            if (actualCost<bestCost){
                //System.out.println("PASSE DANS LE IF");
                bestCost=actualCost;
                bestCurrentSolution= new ArrayList<>(tmp);
                notfound=false;
            }
            i++;
        }
        i =0;
        notfound=true;
        while(notfound && i<this.MAOS.size()){
            int job = MAOS.get(i).getJob();
            int taskNb = MAOS.get(i).getnbTask()+1; //car ici on a un indice et non le num de la tache

            ArrayList<Integer> possibleMachines = problem.getTaskFromJob(job,taskNb).getMachineSet();

            Collections.shuffle(possibleMachines);
            for(int j=0; j<possibleMachines.size(); j++){
                tmp = new ArrayList<>(this.MAOS);
                tmp.get(i).setMachine(possibleMachines.get(j));

                //System.out.println("\navant le calcul\n");

                actualCost=this.getTime(tmp);

                //System.out.println("\naprès le calcul\n");

                if (actualCost<bestCost){
                    //System.out.println("PASSE DANS LE IF");
                    bestCost=actualCost;
                    bestCurrentSolution= new ArrayList<>(tmp);
                    notfound=false;
                }
            }
            i++;

        }

        System.out.println("Nouvelle solution trouvée");
        this.MAOS= new ArrayList<>(bestCurrentSolution);
        result=this.getTime(bestCurrentSolution);
        System.out.println("Nouveau Cout:" + result);

        return result;

    }

         /*
     ###################################################################################################################

     Les fonctions utilisés dans la generation du meilleur voisin

     ###################################################################################################################
      */

     public ArrayList<Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>>> possibleSwapTasks(){

         ArrayList<Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>>> result= new ArrayList<>();
         ArrayList<Quartet<Integer,Integer,Integer,Integer>> possiblePairs = new ArrayList<>();
         ArrayList<Quartet<Integer,Integer,Integer,Integer>> tmpMAOS =  new ArrayList<>(this.MAOS);


         for (int j=0; j< tmpMAOS.size(); j++){
             Quartet<Integer,Integer,Integer,Integer> task =tmpMAOS.get(j);



             possiblePairs = this.getSwapableTasks(task.getJob(), tmpMAOS );

             result.addAll(this.createPairs(possiblePairs,task));

             tmpMAOS.remove(task);

         }
        return result;

     }

     public ArrayList<Quartet<Integer,Integer,Integer,Integer>> getSwapableTasks(int job, ArrayList<Quartet<Integer,Integer,Integer,Integer>> MAOS ){
         ArrayList<Quartet<Integer,Integer,Integer,Integer>> result = new ArrayList<>();


         for (int j=0; j< MAOS.size(); j++){
             Quartet<Integer,Integer,Integer,Integer> task =MAOS.get(j);

             if (task.getJob() != job){ // les jobs doivent être diffenrents 

                result.add(task);
             }
         }
        return result;
     }

     public ArrayList<Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>>> createPairs( ArrayList<Quartet<Integer,Integer,Integer,Integer>> possibleSwap, Quartet<Integer,Integer,Integer,Integer> firstTask){
         ArrayList<Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>>> result = new ArrayList<>();

         for (int j=0; j< possibleSwap.size(); j++){


             Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>> newElement = new Pair<>(firstTask, possibleSwap.get(j));
             result.add(newElement);


         }

         return result;

     }

     public void printSwapableList(ArrayList<Pair< Quartet<Integer,Integer,Integer,Integer>, Quartet<Integer,Integer,Integer,Integer>>> swapList){
         for (int i = 0; i<swapList.size(); i++){
             System.out.println("[" + swapList.get(i).getKey().toString() + swapList.get(i).getValue().toString() + "]" );
         }
     }
     
     public ArrayList<Quartet<Integer,Integer,Integer,Integer>> doSwap(int index1, int index2){
         ArrayList<Quartet<Integer,Integer,Integer,Integer>> result = new ArrayList<>(this.MAOS);

         Collections.swap(result,index1,index2);
         return result;
     }

          /*
    ###################################################################################################################

    Fonction finale réalisant le HillClimbing avec recherche aléatoire de voisinnage

    ###################################################################################################################
    */

    public int HillClimb(){
        int result = Integer.MAX_VALUE;
        int actual;
        boolean notFinished = true;
        while (notFinished){
            actual=this.findNeighbourhood();
            if (actual>=result){
                notFinished=false;
            }
            else{
                result=actual;
            }

        }
        return result;

    }
        /*
    ###################################################################################################################

    Les setters et printer

    ###################################################################################################################
     */

    public void setMAOS(ArrayList<Quartet<Integer,Integer,Integer,Integer>> MAOS){
        this.MAOS= new ArrayList<>(MAOS);
    }

    public ArrayList<Interval<Integer,Integer,Integer,Integer, Integer>> getIntervals(){return this.intervals;}

    public void setIntervals(ArrayList<Interval<Integer,Integer,Integer,Integer, Integer>> intervals){this.intervals = intervals;}

    public void print(ArrayList<Quartet<Integer,Integer,Integer,Integer>> MAOS){

        Integer lastJob=1;

        for (int i=0; i< MAOS.size(); i++){

            Quartet task = MAOS.get(i);

            if (task.getJob() != lastJob){
                System.out.println("\n");
                lastJob= (Integer) task.getJob();
            }

            System.out.print("[J:" + task.getJob() + " M:" + task.getMachine()+ " C:" + task.getCost()+ " TaskNb:" + task.getnbTask() + "] ");


        }
    }

    public void print(){

        Integer lastJob=1;

        for (int i=0; i< this.MAOS.size(); i++){

            Quartet task = this.MAOS.get(i);

            if (task.getJob() != lastJob){
                System.out.println("\n");
                lastJob= (Integer) task.getJob();
            }

            System.out.print("[J:" + task.getJob() + " M:" + task.getMachine()+ " C:" + task.getCost()+ " TaskNb:" + task.getnbTask() + "] ");


        }
    }

    public void print_solution(){
        System.out.println();
        for(int i = 1; i<=this.nbMachine; i++) {
            System.out.print("M" + i + ": ");
            for (int j = 0; j < this.intervals.size(); j++) {
                if (this.intervals.get(j).getMachine() == i) {
                    int timeBefore = this.intervals.get(j).getMachineTimeBefore();
                    int timeAfter = this.intervals.get(j).getMachineTimeAfter();
                    int job = this.intervals.get(j).getJob();
                    int task = this.intervals.get(j).getTask();
                    System.out.print("[(" + timeBefore + ":" + timeAfter + ") J:" + job + " T:" + task + "]");

                }
            }

            System.out.println();

        }

        System.out.println();


    }

}
