import java.util.Random;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber implements Runnable{
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



    private void daydream(){
        gui.barberIsSleeping(pos);
        sleep(g.barberSleep);
        gui.barberIsAwake(pos);
    }

    private void cutHair(){
        Customer customer = queue.removeCustomerFromQueue();
        //Checks if customer is returned
        if(customer != null) {
            gui.fillBarberChair(pos, customer);
            sleep(g.barberWork);
            gui.emptyBarberChair(pos);
        }
    }

    private void sleep(int time){
        Random randm = new Random();
        int random = randm.nextInt(time);
        try {
            thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            cutHair();
            daydream();
        }

    }
}

