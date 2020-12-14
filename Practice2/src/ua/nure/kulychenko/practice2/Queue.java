package ua.nure.kulychenko.practice2;



public interface Queue extends Container {

	// Appends the specified element to the end. Добавляет указанный элемент в конец.
		void enqueue(Object element);

		// Removes the head. Е роll() - возвращает 
		//элемент из головы очереди и удаляет его. Возвращает null, если очередь пуста.
		Object dequeue();

		// Returns the head. 
		//Возвращает голову.Е peek() - возвращает элемент из головы очереди. 
		//Возвращает null, если очередь пуста. Элемент не удаляется.
		Object top();
}
