package ua.nure.kulychenko.practice2;

public interface Stack extends Container {
	
	// Pushes the specified element onto the top.// Помещает указанный элемент в верх.
		void push(Object element);
		
		// Removes and returns the top element.// Удаляет и возвращает верхний элемент.
		Object pop();

		// Returns the top element.// Возвращает верхний элемент.
		Object top();
}

