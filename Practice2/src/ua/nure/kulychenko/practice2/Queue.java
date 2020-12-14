package ua.nure.kulychenko.practice2;



public interface Queue extends Container {

	// Appends the specified element to the end. ��������� ��������� ������� � �����.
		void enqueue(Object element);

		// Removes the head. � ��ll() - ���������� 
		//������� �� ������ ������� � ������� ���. ���������� null, ���� ������� �����.
		Object dequeue();

		// Returns the head. 
		//���������� ������.� peek() - ���������� ������� �� ������ �������. 
		//���������� null, ���� ������� �����. ������� �� ���������.
		Object top();
}
