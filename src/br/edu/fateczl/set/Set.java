package br.edu.fateczl.set;

public class Set<T> {
	
	private Node<T> first;
	
	public Set() {
		first = null;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		int count = 0;
		Node<T> aux = first;
		while(aux != null) {
			aux = aux.getNext();
			count++;
		}
		return count;
	}
	
	private Node<T> getNode(int position) throws Exception{
		if(isEmpty()) 
			throw new Exception("Empty set");
		
		if(position < 0 || position > size() - 1) 
			throw new Exception("Invalid position");
		
		Node<T> aux = first;
		for(int i = 0; i < position; i++) {
			aux = aux.getNext();
		}
		
		return aux;
	}
	
	public void addFirst(T data) {
		if(!alreadyExist(data)) {
			Node<T> node = new Node<>(data, first);
			first = node;
		}
	}
	
	public void addLast(T data) throws Exception {
		if(isEmpty()) {
			addFirst(data);
		} else {
			if(!alreadyExist(data)) {
				getNode(size() - 1).setNext(new Node<>(data, null));
			}
		}
	}

	public void add(T data, int position) throws Exception {
		
		if(position < 0) 
			throw new Exception("Invalid position");
		
		int size = size();
		
		if(position == 0) {
			addFirst(data);
		} else if(position == size) {
			addLast(data);
		} else {
			if(!alreadyExist(data)) {
				Node<T> element = new Node<>(data, null);
				Node<T> previous = getNode(position - 1);
				element.setNext(previous.getNext());
				previous.setNext(element);
			}
		}
	}
	
	public void removeFirst() throws Exception {
		if(isEmpty()) 
			throw new Exception("Empty set");
		
		first = first.getNext();
	}
	
	public void removeLast() throws Exception {
		if(isEmpty()) 
			throw new Exception("Empty set");
		
		int size = size();
		
		if(size == 1) {
			removeFirst();
		} else {
			getNode(size - 2).setNext(null);
		}	
	}
	
	public void remove(int position) throws Exception {
		if(isEmpty()) 
			throw new Exception("Empty set");
		
		int size = size();
		
		if(position < 0 || position > size - 1) 
			throw new Exception("Invalid position");
		
		if(position == 0) {
			removeFirst();
		} else if(position == size) {
			removeLast();
		} else {
			Node<T> previous = getNode(position - 1);
			previous.setNext(previous.getNext().getNext());
		}
	}
	
	public T get(int position) throws Exception {
		if(isEmpty()) 
			throw new Exception("Empty set");
		
		int size = size();
		
		if(position < 0 || position > size - 1) 
			throw new Exception("Invalid position");
		
		if(position == 0) 
			return first.getData();
		
		if(position == size) 
			return getNode(size - 1).getData();
		
		return getNode(position).getData();
		
	}
	
	private boolean alreadyExist(T data) {
		Node<T> aux = first;
		while(aux != null) {
			if(aux.getData().equals(data))
				return true;
			aux = aux.getNext();
		}
		return false;
	}
}
