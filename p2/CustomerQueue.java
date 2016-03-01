/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */


    /*
    * Define variables
    */
    private int queueLength;
    private Gui gui;
    private Customer[] queue;
    private int insertIndex = 0; //Used to keep track of insertions by doorman
    private int removeIndex = 0; // Used to keep track of removals by barbers

    public CustomerQueue(int queueLength, Gui gui) {
        this.queueLength = queueLength;
        this.gui = gui;
        queue = new Customer[queueLength];
	}

    //Hacks a circular array into being
    public synchronized void addCustomerToQueue(Customer customer){
        /*
        If you are at the end of the array,
        start at the beginning.
        */
        if(insertIndex == queueLength)
            insertIndex = 0;

        while(!(queue[insertIndex] == null)){
            try {
                gui.println("Doorman: Waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        gui.println("Doorman: awake: " + insertIndex);
        addCustomerToQueueHelper(customer);
        this.notify();

    }
    //Hacks a circular array into being
    public synchronized Customer removeCustomerFromQueue(){
        Customer customer;//Easier to have one Customer instance for the scope
            if(removeIndex == queueLength)
                removeIndex = 0;

            while ((customer = queue[removeIndex]) == null) {
                try {
                    gui.println("Barber: waiting");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Customer found, remove and notify
            gui.println("Barber awake: " + removeIndex);
            removeCustomerFromQueueHelper(customer);
            removeIndex++;
            this.notify();
            return customer; //Return customer to barber
    }


    private void removeCustomerFromQueueHelper(Customer customer){
        queue[removeIndex] = null;
        gui.emptyLoungeChair(removeIndex);
    }

    private void addCustomerToQueueHelper(Customer customer){
        queue[insertIndex] = customer; //Insert customer in array
        gui.fillLoungeChair(insertIndex, customer); //Show customer in gui
        insertIndex++; //Jump to next place in array
    }



}
