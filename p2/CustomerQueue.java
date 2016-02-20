/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */


    int queueLength;
    Gui gui;
    Customer[] queue;
    int insertIndex = 0;
    int removeIndex = 0;

    public CustomerQueue(int queueLength, Gui gui) {
        this.queueLength = queueLength;
        this.gui = gui;
        queue = new Customer[queueLength];
	}

    public void addCustomerToQueue(Customer customer){
        if(insertIndex == queueLength){
            insertIndex = 0;
            System.out.println("Insert:" + insertIndex);
            if(queue[insertIndex] == null){
                queue[insertIndex] = customer;
                gui.fillLoungeChair(insertIndex, customer);
                insertIndex++;
            }

        }
        else if(queue[insertIndex] == null){
            System.out.println("Insert:" + insertIndex);
            queue[insertIndex] = customer;
            gui.fillLoungeChair(insertIndex, customer);
            insertIndex++;
        }
    }

    public synchronized Customer removeCustomerFromQueue(){
        System.out.println("Remove: " + removeIndex);
        Customer customer;
        if(removeIndex == queueLength-1){
            customer = queue[removeIndex];
            if(customer != null){
            queue[removeIndex] = null;
                removeFromLoungeChair(removeIndex);
                removeIndex = 0;
                return customer;
            }
        }
        customer = queue[removeIndex];
        if(customer != null){
            queue[removeIndex] = null;
            removeFromLoungeChair(removeIndex);
            removeIndex++;

        }
        return customer;
    }

    public synchronized void removeFromLoungeChair(int pos){
        gui.emptyLoungeChair(pos);
    }


}
