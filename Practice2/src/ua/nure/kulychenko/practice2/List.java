package ua.nure.kulychenko.practice2;




public interface List extends Container {
	// Inserts the specified element at the beginning.
		void addFirst(Object element);

		// Appends the specified element to the end.
		void addLast(Object element);

		// Removes the first element.
		Object removeFirst();

		// Removes the last element.
		Object removeLast();

		
		// Returns the first element.
		Object getFirst();

		// Returns the last element.
		Object getLast();

		// Returns the first occurrence of the specified element.
		// Returns null if no such element.
		// (use 'equals' method to check an occurrence)
		Object search(Object element);

		// Removes the first occurrence of the specified element.
		// Returns true if this list contained the specified element. 
		// (use 'equals' method to check an occurrence)
		boolean remove(Object element);
}
