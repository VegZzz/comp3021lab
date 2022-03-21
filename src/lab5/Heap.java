package lab5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.lang.IllegalStateException;
/**
 * TODO class declaration
 * We want to accept any type which is comparable to itself
 *
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {
    private ArrayList<T> container;

    public Heap() {
        container = new ArrayList<>();
    }

    /**
     * @return If size is 0, throw {@link IllegalStateException}.
     * Otherwise, return the first element of {@link this#container}
     */
    public T peek() {
        //TODO
        if(container.size()==0)
        {
        	throw new IllegalStateException();
        }else
        {
        	return container.get(0);
        }
    }

    /**
     *
     * @return If size is 0, throw {@link IllegalStateException}. Otherwise, temporarily save the first element.
     * Afterwards, set the first position to the last element, and remove the last element.
     * Call {@link this#heapifyDown()}, then return the original first element
     */
    public T poll() {
        //TODO
        if(container.size()==0)
        {
        	throw new IllegalStateException();
        }else
        {
        	T min = container.get(0);
        	container.set(0, container.get(container.size()-1));
        	container.remove(container.size()-1);
        	heapifyDown();
        	return min;
        }
    }

    private void heapifyDown() {
        int pos = 0;
        while (hasLeft(pos)) {
            int smallerChildIndex = getLeftIndex(pos);
            if (hasRight(pos) && container.get(getRightIndex(pos)).compareTo(container.get(getLeftIndex(pos))) < 0) {
                smallerChildIndex = getRightIndex(pos);
            }
            if (container.get(pos).compareTo(container.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(pos, smallerChildIndex);
            }
            pos = smallerChildIndex;
        }
    }

    /**
     * Add the object into {@link this#container}, then call {@link this#heapifyUp()}
     *
     * @param obj the object to add
     */
    public void add(T obj) {
        container.add(obj);
        heapifyUp();
    }

    public void addAll(Collection<T> list) {
        list.forEach(this::add);
    }

    /**
     * While the last element has a parent and is smaller than its parent, swap the two elements. Then, check again
     * with the new parent until there's either no parent or we're larger than our parent.
     */
    private void heapifyUp() {
        // TODO
    	int curI = container.size()-1;
    	
    	while(curI!=0&&container.get(curI).compareTo(container.get((curI+1)/2-1))==-1)
    	{
    		T temp = container.get(curI);
    		container.set(curI, container.get((curI+1)/2-1));
    		container.set((curI+1)/2-1, temp);
    		curI = (curI+1)/2-1;
    	}
    }

    public int size() {
        return container.size();
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasParent(int i) {
        return getParentIndex(i) >= 0;
    }

    private boolean hasLeft(int i) {
        return getLeftIndex(i) < container.size();
    }

    private boolean hasRight(int i) {
        return getRightIndex(i) < container.size();
    }

    private void swap(int i1, int i2) {
        Collections.swap(container, i1, i2);
    }
    
    public String toString()
    {
    	return container.toString();
    }
}
