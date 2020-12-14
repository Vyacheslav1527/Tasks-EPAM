package ua.nure.kulychenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

	private int modCount;
	private int size;

	private Node firstNode;
	private Node lastNode;

	private static class Node {
		private Object element;
		private Node next;
		private Node prev;

		Node(Node prev, Object element, Node next) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
	}

	@Override
	public void addFirst(Object e) {
		final Node f = firstNode;
		final Node newNode = new Node(null, e, f);
		firstNode = newNode;
		if (f == null) {
			lastNode = newNode;
		} else {
			f.prev = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void addLast(Object e) {
		final Node l = lastNode;
		final Node newNode = new Node(l, e, null);
		lastNode = newNode;
		if (l == null) {
			firstNode = newNode;
		} else {

			l.next = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void clear() {
		
		firstNode = lastNode = null;
		size = 0;
		modCount++;
	}

	@Override
	public Object getFirst() {
		final Node f = firstNode;
		if (f == null) {
			throw new NoSuchElementException();
		}
		return f.element;
	}

	@Override
	public Object getLast() {
		final Node l = lastNode;
		if (l == null) {
			throw new NoSuchElementException();
		}
		return l.element;
	}

	@Override
	public Object removeFirst() {
		final Node f = firstNode;
		if (f == null) {
			throw new NoSuchElementException();
		}
		return unlinkFirst(f);
	}

	@Override
	public Object removeLast() {
		final Node l = lastNode;
		if (l == null) {
			throw new NoSuchElementException();
		}
		return unlinkLast(l);
	}

	private Object unlinkFirst(Node f) {

		final Object element = f.element;
		final Node next = f.next;
		f.element = null;
		f.next = null;
		firstNode = next;
		if (next == null) {
			lastNode = null;
		} else {
			next.prev = null;
		}
		size--;
		modCount++;
		return element;
	}

	private Object unlinkLast(Node l) {

		final Object element = l.element;
		final Node prev = l.prev;
		l.element = null;
		l.prev = null;
		lastNode = prev;
		if (prev == null) {
			firstNode = null;
		} else {
			prev.next = null;
		}
		size--;
		modCount++;
		return element;
	}

	@Override
	public Object search(Object o) {
		if (o == null) {
			for (Node x = firstNode; x != null; x = x.next) {
				if (x.element == null) {
					return o;
				}
			}
		} else {
			for (Node x = firstNode; x != null; x = x.next) {
				if (o.equals(x.element)) {
					return x.element;
				}
			}
		}
		return o;

	}

	@Override
	public int size() {
		return size;
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

	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index)) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	@Override
	public Iterator<Object> iterator() {
		int index = 0;
		checkPositionIndex(index);
		return new ListItr(index);
	}

	private class ListItr implements Iterator<Object> {
		private Node lastReturned;
		private Node next = (this.index == size) ? null : node(this.index);
		private int nextIndex;
		private int expectedModCount = modCount;
		private int index;

		ListItr(int index) {

			this.index=index;
			nextIndex = index;
		}

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public Object next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.element;
		}

		@Override
		public void remove() {

			if (lastReturned == null) {
				throw new IllegalStateException();
			}

			Node lastNext = lastReturned.next;
			unlink(lastReturned);
			if (next == lastReturned) {
				next = lastNext;
			} else {
				nextIndex--;
			}
			lastReturned = null;
			expectedModCount++;
		}

		Node node(int index) {
			if (index < (size >> 1)) {
				Node x = firstNode;
				for (int i = 0; i < index; i++) {
					Node y = x.next;
					x = y;
				}
				return x;
			} else {
				Node x = lastNode;
				for (int i = size - 1; i > index; i--) {
					x = x.prev;
				}
				return x;
			}
		}

	}

	@Override
	public boolean remove(Object element) {
		if (element == null) {
			for (Node x = firstNode; x != null; x = x.next) {
				if (x.element == null) {
					unlink(x);
					return true;
				}
			}
		} else {
			for (Node x = firstNode; x != null; x = x.next) {
				if (element.equals(x.element)) {
					unlink(x);
					return true;
				}
			}
		}
		return false;
	}

	Object unlink(Node x) {

		final Object element = x.element;
		final Node next = x.next;
		final Node prev = x.prev;

		if (prev == null) {
			firstNode = next;
		} else {
			prev.next = next;
			x.prev = null;
		}

		if (next == null) {
			lastNode = prev;
		} else {
			next.prev = prev;
			x.next = null;
		}

		x.element = null;
		size--;
		modCount++;
		return element;
	}

	public static void main(String[] args) {
		ListImpl myList = new ListImpl();

		myList.addFirst("S");
		myList.addFirst("5");
		myList.addLast("Y");
		myList.addLast("A");
		System.out.println("size: " + myList.size());

		System.out.println(myList);
		System.out.println("getFirst: " + myList.getFirst());
		System.out.println("getLast: " + myList.getLast());

		myList.removeFirst();
		System.out.println("getFirst: " + myList.getFirst());

		myList.removeLast();
		System.out.println("getLast: " + myList.getLast());

		System.out.println("remove(Object): " + myList.remove("A"));

		System.out.println("search(Object): " + myList.search("Y"));
		myList.clear();
		System.out.println("после клира: " + myList);
		myList.addFirst("A");
		myList.addLast("B");
		myList.addLast("C");
		System.out.println("после клира и add: " + myList);
		System.out.println("size: " + myList.size());

		Iterator listItr = myList.iterator();
		while (listItr.hasNext()) {
			System.out.println(listItr.next());

			System.out.println("Методы итератора");

			listItr = myList.iterator();
			listItr.next();
			listItr.next();
			listItr.remove();
			System.out.println(myList);
			System.out.println(listItr.hasNext());
			System.out.println(listItr.next());
			System.out.println(listItr.hasNext());

			myList.clear();
			System.out.println("после клира: " + myList);

		}

	}
}
