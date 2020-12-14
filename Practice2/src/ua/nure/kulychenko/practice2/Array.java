package ua.nure.kulychenko.practice2;



public interface Array extends Container {
	// �������� ��������� ������� � �����.
		void add (Object element);

		// ������������� ������� � ��������� �������.
		void set (int index, Object element);

		// ���������� ������� � ��������� �������.
		Object get (int index);

		// ���������� ������ ������� ��������� ���������� ��������,
		// ��� -1, ���� ���� ������ �� �������� �������.
		// (������������ ����� equals ��� �������� ���������)
		int indexOf (Object element);

		// ������� ������� � ��������� �������.
		Object remove (int index);

}
