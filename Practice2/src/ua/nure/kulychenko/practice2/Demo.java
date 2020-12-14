package ua.nure.kulychenko.practice2;

import java.util.Iterator;


public final class Demo {
    private Demo() {
    }

    public static void main(String[] args) {

        System.out.println("==== ArrayImpl");

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
		
		Iterator<Object> iter = mArray1.iterator();
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





        System.out.println("==== ListImpl");

        ListImpl MyList = new ListImpl ();

		
		 MyList.addFirst("S");
			MyList.addFirst("5");
			MyList.addLast("Y");
			MyList.addLast("A");
			System.out.println("size: " + MyList.size());

			
			System.out.println(MyList);
			System.out.println("getFirst: " + MyList.getFirst());
			System.out.println("getLast: " + MyList.getLast());

			MyList.removeFirst();
			System.out.println("getFirst: " + MyList.getFirst());

			MyList.removeLast();
			System.out.println("getLast: " + MyList.getLast());

			System.out.println("remove(Object): " + MyList.remove("A"));

			System.out.println("search(Object): " + MyList.search("Y"));
			MyList.clear();
			System.out.println("после клира: " + MyList);
			MyList.addFirst("A");
			MyList.addLast("B");
			MyList.addLast("C");
			System.out.println("после клира и add: " + MyList);
			System.out.println("size: " + MyList.size());

			
			Iterator ListItr = MyList.iterator();
			while (ListItr.hasNext()) {
				System.out.println(ListItr.next());
				
				System.out.println("Методы итератора");

				ListItr = MyList.iterator();
				//ListItr.next();
				ListItr.next();
				ListItr.remove();
		        System.out.println(MyList);
		        System.out.println(ListItr.hasNext());
		        System.out.println(ListItr.next());
		        System.out.println(ListItr.hasNext());

	


        System.out.println("==== QueueImpl");

        QueueImpl mArray2 = new QueueImpl();

		mArray2.enqueue("A");
		mArray2.enqueue("B");
		mArray2.enqueue("C");
		System.out.println("add: " + mArray1);

		mArray2.dequeue();
		System.out.println("dequeue: " + mArray1);
		

		System.out.println("top: " + mArray2.top()); 
		
		Iterator<Object> iter2 = mArray2.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());

		}
		System.out.println("Методы итератора");

        iter2 = mArray2.iterator();
       
        iter2.next();
        iter2.remove();
        System.out.println(mArray2);
        System.out.println(iter2.hasNext());
        System.out.println(iter2.next());
        System.out.println(iter2.hasNext());
        
        
        System.out.println("==== StackImpl");
        StackImpl mArray3 = new StackImpl();

		mArray3.push("A");
		mArray3.push("B");
		mArray3.push("C");
		System.out.println("push: " + mArray3);

		
		System.out.println("size: " + mArray3.size());
		
		mArray3.pop();
		System.out.println("pop: " + mArray3);
		
	
		System.out.println("top: " + mArray3.top());


		mArray3.clear();
		System.out.println("clear: " + mArray3);

		mArray3.push("A");
		mArray3.push("B");
		mArray3.push("C");

		
		System.out.println("push 2: " + mArray3);
		
		Iterator<Object> iter3 = mArray3.iterator();
		while (iter3.hasNext()) {
			System.out.println(iter3.next());

		}
		System.out.println("Методы итератора");

        iter3 = mArray3.iterator();
        iter3.next();
        iter3.next();
        iter3.remove();
        System.out.println(mArray3);
        System.out.println(iter3.hasNext());
        System.out.println(iter3.next());
        System.out.println(iter3.hasNext());


    }
    }
}
    


