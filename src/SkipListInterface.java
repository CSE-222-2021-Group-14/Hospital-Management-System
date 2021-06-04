/**
 * SkipListInterface Generic Interface
 * 
 * @author Seyda
 * @param <E> extends Comparable
 */
public interface SkipListInterface<E extends Comparable<? super E>> {
	
	/**
	 * Insert Method
	 * Takes data, inserts the data to the skip list
	 * If the data is inserted returns true, otherwise returns false
	 *
	 * @param data E
	 * @return boolean true/false
	 */
	public boolean insert(E data);
	
	/**
	 * Find Method
	 * Takes a target, searches the target
	 * If the target is found returns the element, otherwise returns null
	 * 
	 * @param target E
	 * @return E element
	 */
	public E find(E target);
	
	/**
	 * Remove Method
	 * Takes element and removes the element from skip list
	 * If element is removed returns true otherwise returns false
	 * 
	 * @param el E
	 * @return boolean true/false
	 */
	public boolean remove(E el);
}
