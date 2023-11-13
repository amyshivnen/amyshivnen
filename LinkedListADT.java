//Abstract Data Type definition for a Linked List

//Note that this is not a full definition as we are only implementing some
//of the standard functions associated with a list.
 
package generics;

public interface LinkedListADT <T>
	{

	
	public void add(T obj);
	
      
    public void add(T obj, int position);
    
    
    public void delete(T obj); 
    

    public void display();
    

    public T getFirst();

    
    public T getLast();

   
    public T getNext(T currentObj);

    
    public boolean isEmpty();
    
     
    public boolean contains(T obj);
    
    
    public void customFunction() ;
    
	}