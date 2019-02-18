package model.data_structures;

public interface ILinkedList<T> {
	Integer getSize();
	void add( T element);
	void addAtK(T element,int i);
	void addAatEnd(T element);
	T getElement(int i);
	T getCurrentElement();
	void delete();
	void deleteAtK(int i);
	T next();
	T previous();
}
