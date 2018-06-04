public class Interval<T, U, V,W, X> {

    private final T MachineTimeBefore;
    private final U MachineTimeAfter;
    private final V Job;
    private final W Machine;
    private final X Task;


    public Interval(T first, U second, V third, W fourth, X fifth) {
        this.MachineTimeBefore = first;
        this.MachineTimeAfter = second;
        this.Job = third;
        this.Machine =fourth;
        this.Task = fifth;
    }

    public T getMachineTimeBefore() {
        return MachineTimeBefore;
    }

    public U getMachineTimeAfter() {
        return MachineTimeAfter;
    }

    public V getJob() {
        return Job;
    }

    public W getMachine() {
        return Machine;
    }

    public X getTask() {
        return Task;
    }



}