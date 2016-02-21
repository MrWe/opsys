import java.util.Random;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman implements Runnable{
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */

    Gui gui;
    CustomerQueue queue;
    Thread thread;
    Globals g;
	public Doorman(CustomerQueue queue, Gui gui) { 
        this.gui = gui;
        this.queue = queue;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
        thread = new Thread(this);
        thread.start();
		// Incomplete

	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		thread.stop();
	}

    public void addCustomer(){
        queue.addCustomerToQueue(new Customer());
    }

    public void daydream(){
        Random randm = new Random();
        int random = randm.nextInt(g.doormanSleep);
        try {
            thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(true){
            addCustomer();
            daydream();
        }
    }
}
