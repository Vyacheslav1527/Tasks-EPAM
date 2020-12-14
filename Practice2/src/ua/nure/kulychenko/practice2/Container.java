package ua.nure.kulychenko.practice2;

import java.util.Iterator;

public interface Container extends Iterable {
	// ������� ��� ��������.
		void clear ();

		// ���������� ���������� ���������.
		int size ();
		
		// ���������� ��������� ������������� ����� ����������.
		String toString ();

		// ���������� �������� ��� ���������.
		// �������� ������ ������������� ����� ��������.
		Iterator <Object> iterator ();

}
