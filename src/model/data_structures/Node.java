package model.data_structures;

public class Node<T> {

	private T elemento;
	private Node<T> siguiente;
	private Node<T> anterior;
	
	public Node( T element)
	{
		this.elemento= element;
		siguiente = null;
		anterior= null;
	}
	
	public Node<T> darSiguiente()
	{
		return siguiente ;
	}
	
	public Node<T> darAnterior()
	{
		return anterior;
	}
	
	public void cambiarSiguiente(Node<T> element )
	{
		siguiente= element;
	}
	
	public void cambiarAnterior(Node<T> element)
	{
		anterior = element;
	}
	
	public T darElement()
	{
		return elemento ;
	}
	
}
