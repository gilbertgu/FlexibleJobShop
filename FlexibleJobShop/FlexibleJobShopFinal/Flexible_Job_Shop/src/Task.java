import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Task {

    private ArrayList<Pair<Integer, Integer>> task; //left (Key) element is a machine
                                                //right (Value) is the task's cost with this machine

    // Constructeur ------------------------------------------------------------------------------------

    public Task() {
        task = new ArrayList<Pair<Integer, Integer>>();
    }

    // Fonctions ------------------------------------------------------------------------------------------

    public void addTask(Integer machine, Integer cost){
        Pair<Integer,Integer> newTask = new Pair(machine,cost);
        if (task.contains(newTask)){
            System.out.println("l'task que tu veux ajouter existe déjà");
        }
        else {
            task.add(newTask);
        }
    }

    public void deleteTask(Integer machine, Integer cost){
        Pair<Integer,Integer> toRemove = new Pair(machine,cost);
        if (task.contains(toRemove)){
            task.remove(toRemove);
        }
        else{System.out.println("ce que tu veux supprimer n'existe pas ");}

    }

    public void printTasks(){
        Iterator<Pair<Integer,Integer>> it = task.iterator();
        System.out.print("{");
        while (it.hasNext()){
            Pair<Integer,Integer> s = it.next();
            System.out.print("M:" + s.getKey() + " C:" + s.getValue() + " ");
        }
        System.out.print("} ");

    }

    public Pair<Integer,Integer> fastMachine(){
        Pair<Integer,Integer> result = new Pair(-1,Integer.MAX_VALUE);
        Iterator<Pair<Integer,Integer>> it = task.iterator();

        while (it.hasNext()){
            Pair<Integer,Integer> s = it.next();
            if (s.getValue()< result.getValue()){
                result = s;
            }
        }

        if (result.getValue()==-1){
            System.err.println("il n'y a pas de machine associé à cette tache !\n PAS NORMAL !!!!!!!!!\n");
        }
        return result;
    }

    public Pair<Integer,Integer> slowMachine(){
        Pair<Integer,Integer> result = new Pair(-1,-1);
        Iterator<Pair<Integer,Integer>> it = task.iterator();

        while (it.hasNext()){
            Pair<Integer,Integer> s = it.next();
            if (s.getValue()> result.getValue()){
                result = s;
            }
        }

        if (result.getValue()==-1){
            System.err.println("il n'y a pas de machine associé à cette tache !\n PAS NORMAL !!!!!!!!!\n");
        }
        return result;
    }

    public boolean containsMachine (Integer machine){
        boolean result =false;
        Iterator<Pair<Integer,Integer>> it = task.iterator();

        while (it.hasNext() && !result){
            Pair<Integer,Integer> s = it.next();
            if (s.getKey()== machine ){
                result = true;
            }
        }
        return result;
    }

    public int getSize(){
        return this.task.size();
    }

    public String toString(){
        Iterator<Pair<Integer,Integer>> it = task.iterator();
        String result ="";
        result += "{";
        while (it.hasNext()){
            Pair<Integer,Integer> s = it.next();
            result += "M:" + s.getKey() + " C:" + s.getValue() + " ";
        }
        result += "} ";
        return result;
    }

    public ArrayList<Integer> getMachineSet(){
        ArrayList<Integer> result = new ArrayList<>();

        for (int i =0; i< this.getSize(); i++){
            result.add(this.task.get(i).getKey());
        }

        return result;

    }



}