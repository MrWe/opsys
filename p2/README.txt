CustomerQueue:
	All logic is handled here. 
	Keeps track of the buffer and makes sure no indexOutOfBound-errors occur.
	Adds and removes customer from queue.
	removeCustomerFromQueue uses synchronized to make sure the barbers doesn't access the same customer at the same time.

Barber:
	Creates three threads with two sleep cycles.
	Sends a call to CustomerQueue to remove and return a customer
Doorman:
	Creates one thread which sends calls to CustomerQueue to add customers to the queue.
