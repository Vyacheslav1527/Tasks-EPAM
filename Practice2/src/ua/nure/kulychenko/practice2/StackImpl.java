package ua.nure.kulychenko.practice2;





import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
	// Реализация на базе класса class ArrayDeque<E> extends AbstractCollection<E>
   // implements Deque<E>, Cloneable, Serializable
	
	public static final int NUMBER = 16;

	private Object[] elements;

	private int head;

	private int tail;

	

	public StackImpl() {
		elements = new Object[NUMBER];
	}

	// add First Добавляет указанный элемент в начало.
	@Override
	public void push(Object e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		head = (head - 1) & (elements.length - 1);
		elements[head] = e;
		if (head == tail) {
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

	// Object dequeue(); Удаляет и возвращает голову (верхний элемент)
	@Override
	public Object pop() {
		Object x = pollFirst();
		if (x == null) {
			throw new NoSuchElementException();
		}
		return x;
	}

	public Object pollFirst() {
		int h = head;
		@SuppressWarnings("unchecked")
		Object result =  elements[h];
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
		// elements[head] is null if deque empty
		return  elements[head];
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
		Iterator<Object> it = descendingIterator();
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
	
	 public Iterator<Object> descendingIterator() {
	        return new DescendingIterator();
	    }
	 @Override
	public Iterator<Object> iterator() {
		return new DeqIterator();
	}

	private class DeqIterator implements Iterator<Object> {

		private int cursor = head;

		private int fence = tail;

		private int lastRet = -1;
		
		@Override
		public boolean hasNext() {
			return cursor != fence;
		}
		@Override
		public Object next() {
			if (cursor == fence) {
				throw new NoSuchElementException();
			}
			@SuppressWarnings("unchecked")
			Object result =  elements[cursor];
			// This check doesn't catch all possible comodifications,
			// but does catch the ones that corrupt traversal
			if (tail != fence || result == null) {
				throw new IllegalArgumentException();
			}
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

		
	}

	
	 private class DescendingIterator implements Iterator<Object> {
	        /*
	         * This class is nearly a mirror-image of DeqIterator, using
	         * tail instead of head for initial cursor, and head instead of
	         * tail for fence.
	         */
	        private int cursor = tail;
	        private int fence = head;
	        private int lastRet = -1;

	        public boolean hasNext() {
	            return cursor != fence;
	        }

	        public Object next() {
	            if (cursor == fence) {
	                throw new NoSuchElementException();
	            }
	            cursor = (cursor - 1) & (elements.length - 1);
	            @SuppressWarnings("unchecked")
	            Object result =  elements[cursor];
	            if (head != fence || result == null) {
	                throw new IllegalArgumentException();
	            }
	            lastRet = cursor;
	            return result;
	        }
	        @Override
	        public void remove() {
	            if (lastRet < 0) {
	                throw new IllegalStateException();
	            }
	            if (!delete(lastRet)) {
	                cursor = (cursor + 1) & (elements.length - 1);
	                fence = head;
	            }
	            lastRet = -1;
	        }
	    }
	
	
	 
	private boolean delete(int i) {
		checkInvariants();
		final Object[] elemen = this.elements;
		final int mask = elemen.length - 1;
		final int h = head;
		final int t = tail;
		final int front = (i - h) & mask;
		final int back = (t - i) & mask;

		// Invariant: head <= i < tail mod circularity
		if (front >= ((t - h) & mask)) {
			throw new IllegalArgumentException();
		}
		// Optimize for least element motion
		if (front < back) {
			if (h <= i) {
				System.arraycopy(elements, h, elements, h + 1, front);
			} else {
				System.arraycopy(elements, 0, elements, 1, i);
				elements[0] = elements[mask];
				System.arraycopy(elements, h, elements, h + 1, mask - h);
			}
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

	private void checkInvariants() {
		assert elements[tail] == null;
		assert (head == tail) ? (elements[head] == null)
				: (elements[head] != null && elements[(tail - 1) & (elements.length - 1)] != null);
		assert elements[(head - 1) & (elements.length - 1)] == null;
	}

	public static void main(String[] args) {

		StackImpl mArray1 = new StackImpl();

		mArray1.push("A");
		mArray1.push("B");
		mArray1.push("C");
		System.out.println(mArray1.size());
		System.out.println("push: " + mArray1);

		System.out.println("pop: " + mArray1.pop());

		System.out.println("top: " + mArray1.top());

		mArray1.clear();
		System.out.println(mArray1);
		System.out.println(mArray1.size());

		mArray1.push("A");
		mArray1.push("B");
		mArray1.push("C");

		System.out.println("итератор: ");

		Iterator<Object> it = mArray1.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}
		System.out.println("Методы итератора");

		it = mArray1.iterator();
		System.out.println(mArray1);
		it.next();

		it.remove();
		System.out.println(mArray1);
		System.out.println(it.hasNext());
		System.out.println(it.next());
		System.out.println(it.hasNext());

	}
}
