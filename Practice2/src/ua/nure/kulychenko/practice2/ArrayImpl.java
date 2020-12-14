package ua.nure.kulychenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

	private Object[] elementData = new Object[INIT_SIZE];

	private int size;
	private static final int INIT_SIZE = 3;

	@Override
	public void add(Object o) {
		if (elementData.length < size + 1) {
			Object[] tmpArray = new Object[(elementData.length * 3) / 3];
			System.arraycopy(elementData, 0, tmpArray, 0, elementData.length);
			elementData = tmpArray;
		}
		elementData[size] = o;
		size++;
	}

	// 2. Устанавливает элемент в указанной позиции
	@Override
	public void set(int index, Object element) {
		elementData[index] = element;
	}

	// 3.Возвращает элемент в указанной позиции
	public Object get(int index) {
		return elementData[index];
	}
	// 4. Возвращает индекс первого вхождения указанного элемента,
	// или -1, если этот массив не содержит элемент.
	// (использовать метод equals для проверки вхождения)

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < INIT_SIZE; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < INIT_SIZE; i++) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public Object remove(int index) {
		if (index >= INIT_SIZE) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}

		Object oldValue = elementData[index];

		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		}
		elementData[--size] = null;

		return oldValue;

	}

	private static String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + INIT_SIZE;
	}

	@Override
	public void clear() {
		elementData = new Object[INIT_SIZE];
		size = 0;
	}

	// 7.Возвращает количество элементов
	public int size() {
		return size;
	}

	// 8. Возвращает строковое представление этого контейнера.
	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < size(); i++) {
			result.append(String.valueOf(elementData[i])).append(", ");
		}
		result.replace(result.length() - 2, result.length(), "]");
		return result.toString();
	}

	@Override
	public Iterator iterator() {
		return new MyItr();
	}

	private class MyItr implements Iterator {
		// index of next element to return
		private int cursor;
		// index of last element returned; -1 if no such
		private int lastRet = -1;

		public boolean hasNext() {
			return cursor != INIT_SIZE;
		}

		// @SuppressWarnings("unchecked")
		@Override
		public Object next() {
			int i = cursor;
			if (i >= INIT_SIZE) {
				throw new NoSuchElementException();
			}
			Object[] array = ArrayImpl.this.elementData;
			if (i >= array.length) {
				throw new NoSuchElementException();
			}
			cursor = i + 1;
			lastRet = i;
			return array[lastRet];
		}

		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}

			try {
				ArrayImpl.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;

			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}
	}

	public static void main(String[] args) {

		ArrayImpl mArray1 = new ArrayImpl();

		mArray1.add("A");
		mArray1.add("B");
		mArray1.add("C");
		System.out.println("add: " + mArray1);

		mArray1.set(0, "J");
		System.out.println("set: " + mArray1);

		System.out.println("get: " + mArray1.get(2));

		int a = mArray1.indexOf("C");
		System.out.println("indexOf: " + a);

		int s = mArray1.size();
		System.out.println("size: " + s);

		mArray1.remove(0);
		System.out.println("remove: " + mArray1);

		mArray1.clear();
		System.out.println("clear: " + mArray1);

		mArray1.add("Y");
		mArray1.add("E");
		mArray1.add("S");

		System.out.println("add 2: " + mArray1);

		Iterator iter = mArray1.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		System.out.println("Методы итератора");

		iter = mArray1.iterator();

		iter.next();
		iter.remove();
		System.out.println(mArray1);
		System.out.println(iter.hasNext());
		System.out.println(iter.next());
		System.out.println(iter.hasNext());

	}
}
