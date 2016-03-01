import java.util.Random;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread{
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
    private Thread thread;
    private CustomerQueue queue;
    private Gui gui;
    private int pos;
    private Globals g;

	public Barber(CustomerQueue queue, Gui gui, int pos) {
        this.queue = queue;
        this.gui = gui;
        this.pos = pos;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
        thread = new Thread(this);
        thread.start();

	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
        thread.stop();
	}



    @Override
    public void run() {
        while(true){
            Customer customer = queue.removeCustomerFromQueue();
            //Checks if customer is
            gui.fillBarberChair(pos, customer);
            try {
                sleep(g.barberWork);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gui.emptyBarberChair(pos);


            try {
                gui.barberIsSleeping(pos);
                sleep(g.barberSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gui.barberIsAwake(pos);
        }

    }
}

