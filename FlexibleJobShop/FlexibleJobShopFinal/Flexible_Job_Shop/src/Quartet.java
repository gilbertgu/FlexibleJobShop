public class Quartet<T, U, V,W > {

    private final T job;
    private U machine;
    private final V cost;
    private final W nbTask;


    public Quartet(T first, U second, V third, W fourth) {
        this.job = first;
        this.machine = second;
        this.cost = third;
        this.nbTask=fourth;
    }

    public T getJob() { return job; }
    public U getMachine() { return machine; }
    public V getCost() { return cost; }
    public W getnbTask() { return nbTask; }

    public String toString(){
        return "J: " + this.job + " M:" + this.machine + " C:" + this.cost + " nbTask:" + this.nbTask + " ";
    }

    public void setMachine(U machine){
        this.machine=machine;
    }

}