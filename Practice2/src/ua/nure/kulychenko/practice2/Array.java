package ua.nure.kulychenko.practice2;



public interface Array extends Container {
	// Добавить указанный элемент в конец.
		void add (Object element);

		// Устанавливает элемент в указанной позиции.
		void set (int index, Object element);

		// Возвращает элемент в указанной позиции.
		Object get (int index);

		// Возвращает индекс первого вхождения указанного элемента,
		// или -1, если этот массив не содержит элемент.
		// (использовать метод equals для проверки вхождения)
		int indexOf (Object element);

		// Удаляет элемент в указанной позиции.
		Object remove (int index);

}
