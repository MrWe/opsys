/**
 * Created by Wiker on 07/04/16.
 */
public class IO {

    private Queue IOQueue;
    private long avgIoTime;

    public IO(Queue IOQueue, long avgIoTime){
        this.IOQueue = IOQueue;
        this.avgIoTime = avgIoTime;
    }

    public void insertProcess(Process p){
        IOQueue.insert(p);
    }

    public Process getFirstProcess(){
        return (Process)IOQueue.getNext();
    }

    public Process removeFirstProcess(){
        Process process = (Process)IOQueue.getNext();
        IOQueue.removeNext();
        return process;
    }
}
