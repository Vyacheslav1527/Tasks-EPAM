package ua.nure.kulychenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

	private Object[] elements;

	private int head;

	private int tail;
	public static final int NUMBER = 16;

	public QueueImpl() {
		elements = new Object[NUMBER];
	}

	// void enqueue(Object element); Добавляет указанный элемент в конец.
	@Override
	public void enqueue(Object e) {
		if (e == null) {
			// NoSuchElementException
			throw new IllegalArgumentException();
		}
		elements[tail] = e;
		if ((tail = (tail + 1) & (elements.length - 1)) == head) {
			doubleCapacity();
		}
	}

	private void doubleCapacity() {
		assert head == tail;
		int p = head;
		int n = elements.length;
		int r = n - p;
		int newCapacity = n << 1;
		if (newCapacity < 0) {
			throw new IllegalStateException("Sorry, deque too big");
		}
		Object[] a = new Object[newCapacity];
		System.arraycopy(elements, p, a, 0, r);
		System.arraycopy(elements, 0, a, r, p);
		elements = a;
		head = 0;
		tail = n;
	}

	// Object dequeue(); Удаляет голову
	@Override
	public Object dequeue() {
		Object x = pollFirst();
		if (x == null) {
			throw new NoSuchElementException();
		}
		return x;
	}

	public Object pollFirst() {
		int h = head;
		@SuppressWarnings("unchecked")
		Object result = elements[h];
		// Element is null if deque empty
		if (result == null) {
			return null;
		}
		elements[h] = null;
		head = (h + 1) & (elements.length - 1);
		return result;
	}

	// Object top(); Возвращает голову
	@Override
	public Object top() {

		return elements[head];
	}

	public int size() {
		return (tail - head) & (elements.length - 1);
	}

	@Override
	public void clear() {
		int h = head;
		int t = tail;
		if (h != t) {
			head = tail = 0;
			int i = h;
			int mask = elements.length - 1;
			do {
				elements[i] = null;
				i = (i + 1) & mask;
			} while (i != t);
		}
	}

	@Override
	public String toString() {
		Iterator<Object> it = iterator();
		if (!it.hasNext()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			Object e = it.next();
			sb.append(e == this ? "(this Collection)" : e);
			if (!it.hasNext()) {
				return sb.append(']').toString();
			}
			sb.append(',').append(' ');
		}
	}

	// ****************************Iterator********************************************

	@Override
	public Iterator<Object> iterator() {
		return new DeqIterator();
	}

	public class DeqIterator implements Iterator<Object> {
		private int cursor = head;
		private int fence = tail;
		private int lastRet = -1;
		public boolean hasNext() {
			return cursor != fence;
		}
		@Override
		public Object next() {
			if (cursor == fence) {
				throw new NoSuchElementException();
			}
			Object result = elements[cursor];
			lastRet = cursor;
			cursor = (cursor + 1) & (elements.length - 1);
			return result;
		}
		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			if (delete(lastRet)) {
				cursor = (cursor - 1) & (elements.length - 1);
				fence = tail;
			}
			lastRet = -1;
		}
		private boolean delete(int i) {
			final Object[] elemen = elements;
			final int mask = elemen.length - 1;
			final int h = head;
			final int t = tail;
			final int front = (i - h) & mask;
			final int back = (t - i) & mask;
			if (front >= ((t - h) & mask)) {
				throw new IllegalArgumentException();
			}
			if (front < back) {
				elements[h] = null;
				head = (h + 1) & mask;
				return false;
			} else {
				if (i < t) {
					System.arraycopy(elements, i + 1, elements, i, back);
					tail = t - 1;
				} else {
					System.arraycopy(elements, i + 1, elements, i, mask - i);
					elements[mask] = elements[0];
					System.arraycopy(elements, 1, elements, 0, t);
					tail = (t - 1) & mask;
				}
				return true;
			}
		}
	}

	public static void main(String[] args) {

		QueueImpl mArray1 = new QueueImpl();

		mArray1.enqueue("A");
		mArray1.enqueue("B");
		mArray1.enqueue("C");
		System.out.println("add: " + mArray1);

		System.out.println("dequeue: " + mArray1.dequeue());

		System.out.println("top: " + mArray1.top());

		Iterator<Object> iter = mArray1.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());

		}
		System.out.println("Методы итератора");

		iter = mArray1.iterator();
		System.out.println(mArray1);
		iter.next();

		iter.remove();
		System.out.println(mArray1);
		System.out.println(iter.hasNext());
		System.out.println(iter.next());
		System.out.println(iter.hasNext());

		mArray1.enqueue("A");
		mArray1.enqueue("B");
		mArray1.enqueue("C");

		System.out.println("методы Джен" + mArray1);
		System.out.println(mArray1.size());

		mArray1.clear();
		System.out.println(mArray1);
		System.out.println(mArray1.size());

		mArray1.enqueue("A");
		mArray1.enqueue("B");
		mArray1.enqueue("C");

		System.out.println(mArray1);
		System.out.println(mArray1.size());
	}
}
