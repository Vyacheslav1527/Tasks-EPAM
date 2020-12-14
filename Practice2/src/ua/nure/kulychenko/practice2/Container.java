package ua.nure.kulychenko.practice2;

import java.util.Iterator;

public interface Container extends Iterable {
	// Удаляет все элементы.
		void clear ();

		// Возвращает количество элементов.
		int size ();
		
		// Возвращает строковое представление этого контейнера.
		String toString ();

		// Возвращает итератор для элементов.
		// Итератор должен реализовывать метод удаления.
		Iterator <Object> iterator ();

}
