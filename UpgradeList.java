package NUA;
import java.util.*;

public class UpgradeList {
	
	//Variables
		private FlyerInfo[] minHeap;
	    private Map<FlyerInfo, Integer> indexMap;
	    private Comparator<FlyerInfo> comparator;
	    private int maxSize;
	    private int size;
	
	    //Constructor
	    public UpgradeList(Comparator<FlyerInfo> comparator) {
	        this.maxSize = 1000;
	        this.minHeap = new FlyerInfo[this.maxSize + 1];
	        this.indexMap = new HashMap<>();
	        this.comparator = comparator;
	        this.size = 0;
	        minHeap[0] = new FlyerInfo();
	    }// end UpgradeList
	
	    private int parent(int current) {
	        return current / 2;
	    }//end parent
	
	    private int leftChild(int current) {
	        return (2 * current);
	    }//end leftChild
	
	    private int rightChild(int current) {
	        return (2 * current) + 1;
	    }//end rightChild
	
	    //Method to rotate elements
	    private void rotate(int root, int leaf) {
	    	FlyerInfo temp;
	        temp = minHeap[root];
	
	        indexMap.put(minHeap[leaf], root);
	        indexMap.put(temp, leaf);
	
	        minHeap[root] = minHeap[leaf];
	        minHeap[leaf] = temp;
	    }//end rotate
	
	    //Method to ensure heap properties
	    private void heapify(int current) {
	        //checking if the node is a leaf node
	        if (current > (size / 2) && current <= size+1)
	            return;
	
	        //check if a rotation is needed
	        if (comparator.compare(minHeap[current], minHeap[leftChild(current)]) == 1 || comparator.compare(minHeap[current], minHeap[rightChild(current)]) == 1) {
	
	            //replace parent with minimum child
	            if (comparator.compare(minHeap[leftChild(current)], minHeap[rightChild(current)]) == -1) {
	                rotate(current, leftChild(current));
	                //heapify children
	                heapify(leftChild(current));
	            } else {
	                rotate(current, rightChild(current));
	                //heapify is called children
	                heapify(rightChild(current));
	            }
	        }
	    }//end heapify
	
	    
	    //Method to insert new request
	    public void insert(FlyerInfo request) {
	        minHeap[++size] = request;
	        indexMap.put(request, size);
	
	        int current = size;
	        //Ensure heap properties maintained
	        while (comparator.compare(minHeap[current], minHeap[parent(current)]) == -1) {
	            rotate(current, parent(current));
	            current = parent(current);
	        }
	    }//end insert
	    
	    
	    //Method to remove request
	    public void remove(FlyerInfo request) {
	        int index = indexMap.get(request);
	        rotate(index, size--);
	        heapify(index);
	    }//end remove
	    

	    //Method to return minimum element (to populate upgrade list)
	    public FlyerInfo popMin() {
	    	FlyerInfo min = minHeap[1];
	
	        indexMap.remove(minHeap[1]);
	        indexMap.put(minHeap[size], 1);
	
	        minHeap[1] = minHeap[size--];
	        heapify(1);
	        minHeap[size + 1] = null;
	        return min;
	    }// end popMin
	
	    
	    //Method to return the size
	    public int getSize() {
	        return size;
	    }//end getSize
	    
}//end class
