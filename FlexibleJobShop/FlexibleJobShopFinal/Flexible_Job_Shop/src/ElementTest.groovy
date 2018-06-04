import Task
import javafx.util.Pair;

class TaskTest extends GroovyTestCase {


    void testAddTask() {
        Task e1 = new Task();
        e1.addTask(2,7);
        e1.addTask(3,9);
        e1.addTask(3,9);
    }

    void testDeleteTask() {
        Task e1 = new Task();

        e1.addTask(2,7);
        e1.addTask(3,9);
        e1.deleteTask(3,9);
        e1.deleteTask(3,9);
    }

    void testPrintTasks() {
        Task e1 = new Task();

        e1.addTask(2,7);
        e1.addTask(3,9);
        e1.printTasks();
        e1.addTask(4,9);
        e1.deleteTask(3,9);
        e1.printTasks();

    }

    void testFastMachine() {
        Task e1 = new Task();
        Task e2 = new Task();
        Task e3 = new Task();

        e1.addTask(2,7);
        e1.addTask(3,9);
        e1.addTask(4,2);

        e2.addTask(2,1);
        e2.addTask(3,9);
        e2.addTask(4,2);

        e3.addTask(2,7);
        e3.addTask(3,1);
        e3.addTask(4,2);

        Pair<Integer,Integer> result1;
        Pair<Integer,Integer> result2;
        Pair<Integer,Integer> result3;

        result1 = e1.fastMachine();
        result2 = e2.fastMachine();
        result3 = e3.fastMachine();

        System.out.println(result1.toString() + "\n" + result2.toString() + "\n" + result3.toString());


    }

    void testSlowMachine() {

        Task e1 = new Task();
        Task e2 = new Task();
        Task e3 = new Task();

        e1.addTask(2,7);
        e1.addTask(3,9);
        e1.addTask(4,2);

        e2.addTask(2,1);
        e2.addTask(3,9);
        e2.addTask(4,2);

        e3.addTask(2,7);
        e3.addTask(3,1);
        e3.addTask(4,2);

        Pair<Integer,Integer> result1;
        Pair<Integer,Integer> result2;
        Pair<Integer,Integer> result3;

        result1 = e1.slowMachine();
        result2 = e2.slowMachine();
        result3 = e3.slowMachine();

        System.out.println(result1.toString() + "\n" + result2.toString() + "\n" + result3.toString());
    }

    void testContainsMachine() {

        Task e1 = new Task();

        e1.addTask(2,7);
        e1.addTask(3,9);
        e1.addTask(4,2);

        System.out.println(e1.containsMachine(2));
        System.out.println(e1.containsMachine(3));
        System.out.println(e1.containsMachine(4));
        System.out.println(e1.containsMachine(9));
    }

    void testGetSize() {
        Task e1 = new Task();

        e1.addTask(2,7);
        e1.addTask(3,9);
        e1.addTask(4,2);

        System.out.println(e1.getSize());
    }

    void testGetMachineSet() {
        Task e1 = new Task();

        e1.addTask(2,7);


        System.out.print(e1.getMachineSet().toString());


    }
}
