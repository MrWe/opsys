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
    Thread thread;
    CustomerQueue queue;
    Gui gui;
    int pos;
    Globals g;

	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		// Incomplete

        this.queue = queue;
        this.gui = gui;
        this.pos = pos;


	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		// Incomplete
        thread = new Thread(this);
        thread.start();

	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		// Incomplete
        thread.stop();
	}

    public void daydream(){
        gui.barberIsSleeping(pos);
        Random randm = new Random();
        int random = randm.nextInt(g.barberSleep);
        try {
            thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gui.barberIsAwake(pos);
    }

    public void cutHair(){
        Customer customer = queue.removeCustomerFromQueue();
        if(customer != null) {
            gui.fillBarberChair(pos, customer);
            Random randm = new Random();
            int random = randm.nextInt(g.barberWork);
            try {
                thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gui.emptyBarberChair(pos);
        }
    }

    @Override
    public void run() {
        while(true){
            cutHair();
            daydream();
        }

    }
    // Add more methods as needed
}

