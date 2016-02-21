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
    private int insertIndex = 0;
    private int removeIndex = 0;

    public CustomerQueue(int queueLength, Gui gui) {
        this.queueLength = queueLength;
        this.gui = gui;
        queue = new Customer[queueLength];
	}

    //Hacks a circular array into being
    public void addCustomerToQueue(Customer customer){
        /*
        If you are at the end of the array
        Start at the beginning
        Check if seat is taken, false if taken
        */
        if(insertIndex == queueLength && !seatIsTaken(insertIndex = 0)){
                addCustomerToQueueHelper(customer);
        }
        //If not at end of array, input customer if seat is not taken
        else if(!seatIsTaken(insertIndex)){
            addCustomerToQueueHelper(customer);
        }
    }
    //Hacks a circular array into being
    public synchronized Customer removeCustomerFromQueue(){
        Customer customer = queue[removeIndex]; //Easier to have one Customer instance for the scope
        if(customerIsNotNull(customer)) { // if customer was available
            if (removeIndex == queueLength - 1) { //If at end of array
                removeCustomerFromQueueHelper();
                removeIndex = 0; //Starts at beginning
                return customer; //Return customer to barber
            }
            //If not at end of array, just remove and increment to next place in array
            removeCustomerFromQueueHelper();
            removeIndex++;
        }
        return customer; //Return customer to barber
    }

    private boolean seatIsTaken(int index){
        return !(queue[index] == null);
    }

    private boolean customerIsNotNull(Customer customer){
        return customer != null;
    }

    private void removeCustomerFromQueueHelper(){
        queue[removeIndex] = null;
        removeFromLoungeChair(removeIndex);
    }

    private void addCustomerToQueueHelper(Customer customer){
        queue[insertIndex] = customer; //Insert customer in array
        gui.fillLoungeChair(insertIndex, customer); //Show customer in gui
        insertIndex++; //Jump to next place in array
    }

    private synchronized void removeFromLoungeChair(int pos){
        gui.emptyLoungeChair(pos);
    }


}
