import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Random;

/**
 * SkipList Generic Class Implements SkipListInterface
 * 
 * @author Seyda
 * @param <E> extends Comparable
 */
public class SkipList<E extends Comparable<E>> implements SkipListInterface<E> {
	
	/**
	 * Head
	 */
	private SLNode<E> head = null;
	
	/**
	 * Order
	 */
	private int order = 0;
	
	/**
	 * Minimum Key
	 */
	private int minKey = 0;
	
	/**
	 * Maximum Key
	 */
	private int maxKey = 0;
	
	/**
	 * Size
	 */
	private int size = 0;
	
	/**
	 * Maximum Level
	 */
	private int maxLevel = 0;
	
	/**
	 * Maximum Capacity
	 */
	private int maxCap = 0;
	
	/**
	 * Constant LOG2
	 */
	private static final double LOG2 = Math.log(2.0);
	
	/**
	 * Random Number
	 */
	private Random rand = null;
	
	/**
	 * One parameter constructor
	 * 
	 * @param order int
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public SkipList(int order) {
		if (order < 2) {
			throw new InvalidParameterException("Use the basic skip list instead");
		}
		this.order = order;
		maxKey = order - 1;
		minKey = maxKey / 2;
		size = 0;
		maxLevel = 0;
		maxCap = computeMaxCap();
		head = new SLNode(null, maxLevel, order);
		rand = new Random();
	}

	/**
	 * Search Method
	 * Takes a target, searches skip list for target, returns preceding nodes
	 *
	 * @param target target
	 * @return SLNode links
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	private SLNode<E>[] search(E target) {
		SLNode<E>[] pred = new SLNode[maxLevel];
		SLNode<E> current = head;
		for (int i = current.links.length - 1; i >= 0; --i) {
			while (current.links[i] != null && current.links[i].data[current.links[i].size() - 1].compareTo(target) < 0) {
				current = current.links[i];
			}
			pred[i] = current;
		}
		return pred;
	}

	/**
	 * Compute Maximum Capacity Method
	 * Computes the maximum capacity with respect to maximum level and returns new maximum capacity
	 * 
	 * @return int maxCap
	 */
	private int computeMaxCap() {
		return (int) Math.pow(2, maxLevel) - 1;
	}
	
	/**
	 * Remove Method
	 * Takes element and removes the element from skip list
	 * If element is removed returns true, otherwise returns false
	 * 
	 * @param el E
	 * @return boolean true/false
	 */
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public boolean remove(E el) {
		SLNode<E> pred[] = search(el);
		if (pred[0].contains(el)) {
			if (pred[0].size() > minKey || pred[0].links[0] == null) {
				size--;
				pred[0].remove(el);
				return true;
			} else {
				E smallest = pred[0].links[0].data[0];
				pred[0].links[0].remove(smallest);
				pred[0].add(smallest);
				pred[0].remove(el);
				pred[0].links[0].add(el);
			}
		}
		if (pred[0].links[0] != null && pred[0].links[0].contains(el)) {
			size--;
			if (pred[0].links[0].size() > minKey) {
				pred[0].links[0].remove(el);
			} else if (pred[0].size() > minKey) {
				E popped = pred[0].pop();
				pred[0].links[0].remove(el);
				pred[0].links[0].add(popped);
			} else {
				pred[0].links[0].remove(el);
				for (int i = 0; i < pred[0].links[0].size(); ++i)
				{
					pred[0].add(pred[0].links[0].data[i]);
				}
				SLNode<E> temp = pred[0].links[0];
				for (int i = 0; i < temp.links.length; ++i) {
					pred[i].links[i] = temp.links[i];
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Insert Method
	 * Takes data, inserts the data to the skip list
	 * If the data is inserted returns true, otherwise returns false
	 *
	 * @param data E
	 * @return boolean true/false
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public boolean insert(E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		size++;
		SLNode<E> pred[] = search(data);
		if (size > maxCap) {
			maxLevel++;
			maxCap = computeMaxCap();
			head.links = Arrays.copyOf(head.links, maxLevel);
			pred = Arrays.copyOf(pred, maxLevel);
			pred[maxLevel - 1] = head;
		}
		if (pred[0] == head && head.size() > 0 && pred[0].data[pred[0].size() - 1].compareTo(data) > 0) {
			if (!pred[0].isFull()) {
				pred[0].add(data);
				return true;
			}
			E popped = pred[0].pop();
			pred[0].add(data);
			data = popped;
		}
		if (pred[0].links[0] == null) {
			if (!pred[0].isFull()) {
				pred[0].add(data);
			} else {
				SLNode<E> lastNode = new SLNode<E>(data, logRandom(), order);
				for (int i = 0; i < minKey - 1; ++i) {
					lastNode.add(pred[0].pop());
				}
				for (int i = 0; i < lastNode.links.length; ++i) {
					lastNode.links[i] = pred[i].links[i];
					pred[i].links[i] = lastNode;
				}
			}
			return true;
		} else if (pred[0].links[0] != null && !pred[0].links[0].isFull()) {
			pred[0].links[0].add(data);
		} else {
			SLNode<E> newNode = new SLNode<E>(data, logRandom(), order);
			for (int i = 0; i < minKey - 1; ++i) {
				newNode.add(pred[0].links[0].pop());
			}
			if (data.compareTo(pred[0].links[0].data[pred[0].links[0].size() - 1]) < 0) {
				E popped_el = pred[0].links[0].pop();
				newNode.remove(data);
				pred[0].links[0].add(data);
				newNode.add(popped_el);
			}
			int i;
			for (i = 0; i < newNode.links.length && i < pred[0].links[0].links.length; ++i) {
				newNode.links[i] = pred[0].links[0].links[i];
				pred[0].links[0].links[i] = newNode;
			}
			while (i < newNode.links.length) {
				newNode.links[i] = pred[i].links[i];
				pred[i].links[i] = newNode;
				++i;
			}
		}
		return true;
	}

	/**
	 * Log Random Method
	 * Generates random number for level of the element
	 *
	 * @return int level
	 */
	private int logRandom() {
		int r = rand.nextInt(maxCap);
		int k = (int) (Math.log(r + 1) / LOG2);
		if (k > maxLevel - 1) {
			k = maxLevel - 1;
		}
		return maxLevel - k;
	}

	/**
	 * Find Method
	 * Takes a target, searches the target
	 * If the target is found returns the element, otherwise returns null
	 * 
	 * @param target E
	 * @return E element
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public E find(E target) {
		SLNode<E> pred[] = search(target);
		if (pred[0].contains(target)) {
			return target;
		} else if (pred[0].links[0] == null) {
			return null;
		} else if (pred[0].links[0].contains(target)) {
			return target;
		}
		return null;
	}
	
	/**
	 * Size Method
	 * Retrieves the size of the skip list
	 * 
	 * @return int size
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Overridden toString Method
	 * Returns string form of the skip list
	 * 
	 * @return String links
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		SLNode<E> temp = head;
		while (temp != null) {
			sb.append(temp);
			if (temp.links[0] != null) {
				sb.append(" ---> ");
			}
			temp = temp.links[0];
		}
		return sb.toString();
	}
	
	/**
	 * SLNode Static Inner Class
	 * 
	 * @param <E> extends Comparable
	 */
	private static class SLNode<E extends Comparable<E>> {
		
		/**
		 * Links
		 */
		SLNode<E>[] links;
		
		/**
		 * Data
		 */
		E[] data;
		
		/**
		 * Counter i
		 */
		int i = 0;
		
		/**
		 * Capacity
		 */
		int capacity;
		
		/**
		 * Three parameter constructor
		 * 
		 * @param data E
		 * @param level int
		 * @param order int
		 */
		@SuppressWarnings({"unchecked", "rawtypes"})
		public SLNode(E data, int level, int order) {
			links = (SLNode<E>[]) new SLNode[level];
			this.data = (E[]) new Comparable[order - 1];
			this.data[i] = data;
			capacity = order - 1;
			if (data != null) {
				i++;
			}
		}

		/**
		 * Add Method
		 * Adds element
		 *
		 * @param el E
		 * @return boolean true/false
		 */
		private boolean add(E el) {
			if (isFull()) {
				return false;
			}
			int place = i;
			for (place = i; place >= 1 && (data[place - 1] == null || el.compareTo(data[place - 1]) < 0); --place) {
				data[place] = data[place - 1];
			}
			data[place] = el;
			i++;
			return true;
		}

		/**
		 * Is Full Method
		 * Returns true if there is no space in the array, otherwise returns false
		 *
		 * @return boolean true/false
		 */
		public boolean isFull() {
			return i == capacity;
		}

		/**
		 * Pop Method
		 * Pops last element
		 *
		 * @return last element in the array
		 */
		public E pop() {
			if (!(i > 1)) {
				throw new RuntimeException("Can't remove from a node that has less than 2 elements in it");
			}
			i--;
			return data[i];
		}

		/**
		 * Remove Method
		 * Removes the certain element
		 *
		 * @param data E
		 * @return E element
		 */
		public E remove(E data) {
			int j = 0;
			while (!(this.data[j].equals(data))) {
				j++;
			}

			while (j + 1 < i) {
				this.data[j] = this.data[j + 1];
				j++;
			}
			i--;
			return data;
		}

		/**
		 * Size Method
		 * Retrieves the size of the array
		 *
		 * @return int size
		 */
		public int size() {
			return i;
		}

		/**
		 * Contains Method
		 * Returns true if the target is in the array, otherwise returns false
		 * 
		 * @param target E
		 * @return boolean true/false
		 */
		public boolean contains(E target) {
			for (int j = 0; j < i; ++j) {
				if (target.equals(data[j])) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Overridden toString Method
		 * Returns string form of the array
		 * 
		 * @return String data
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < i; j++) {
				sb.append(data[j] + " ");
			}
			return sb.toString();
		}
	}
}
