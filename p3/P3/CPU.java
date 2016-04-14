/**
 * Created by Wiker on 07/04/16.
 */
public class CPU {

    private Queue CPUQueue;

    private long maxCpuTime;

    private long timePassed;

    private long cpuSetActiveTime;

    private boolean cpuIsActive = false;

    public Process activeProcess;


    public CPU(Queue CPUQueue, long maxCpuTime){
        this.CPUQueue = CPUQueue;
        this.maxCpuTime = maxCpuTime;
        this.timePassed = 0;
        cpuSetActiveTime = 0;
    }

    public void insertProcess(Process p){
        CPUQueue.insert(p);
    }

    public Process getFirstProcess(){
        return (Process)CPUQueue.getNext();
    }

    public Process removeFirstProcess(){
        Process process = (Process)CPUQueue.getNext();
        CPUQueue.removeNext();
        return process;
    }

    public void setCpuSetActiveTime(long clock){
        cpuSetActiveTime = clock;
    }

    public void timePassed(long timePassed) {
        this.timePassed += timePassed;
        //timespassed - cpusetActivetime = time in cpu for current process
    }
    public boolean isEmpty(){
        return CPUQueue.isEmpty();
    }

    public long getMaxCpuTime(){
        return maxCpuTime;
    }


    public void setActiveProcess(Process p){
        this.activeProcess = p;

    }

    public Process getActiveProcess(){
        return activeProcess;
    }

    public void setCpuIsActive(boolean isActive){
        this.cpuIsActive = isActive;
    }

    public boolean getCpuIsActive(){
        return cpuIsActive;
    }

    //TODO: Currentprocess in cpu
}
