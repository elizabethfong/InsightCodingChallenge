import java.util.PriorityQueue ;

/**
 * A class which calculates the running median of unique words in each tweet.
 * 
 * @author Elizabeth Fong
 * @version 6th July 2015
 */
public class RunningMedian
{
    // calculates running median using 2 heaps - a max heap and a min heap
    private PriorityQueue<Integer> _maxHeap ;   // values < median
    private PriorityQueue<Integer> _minHeap ;   // values >= median
    
    // the median
    private double _median ;
    
    
    /* --- CONSTRUCTION ----------------------------------------------------- */
    
    /**
     * Constructor. Initialises the queues used for calculating the running
     * median.
     */
    public RunningMedian()
    {
        _maxHeap = new PriorityQueue<Integer>() ;
        _minHeap = new PriorityQueue<Integer>() ;
        
        _median = 0 ;
    }
    
    
    /* --- ADD VALUE AND CALCULATE MEDIAN ----------------------------------- */
    
    /**
     * Adds the given value and calculates the resulting running median.
     * 
     * @param value The number of unique words in a tweet to be added.
     */
    public void add( int value )
    {
        // add value to a heap
        if( value < _median )
        {
            _maxHeap.add( new Integer(0-value) ) ;
        }
        else
        {
            _minHeap.add( new Integer(value) ) ;
        }
        
        // balance heaps & calculate median
        balanceHeaps() ;
        calculateMedian() ;
        
    }
    
    /**
     * Balances both min and max heaps so that the maximum size difference 
     * between them is 1.
     */
    private void balanceHeaps()
    {
        // max heap -> min heap
        if( _maxHeap.size() > _minHeap.size() + 1 )
        {
            int val = 0 - _maxHeap.poll().intValue() ;
            _minHeap.add( new Integer(val) ) ;
        }
        
        // min heap -> max heap
        else if( _minHeap.size() > _maxHeap.size() + 1 )
        {
            int val = _minHeap.poll().intValue() ;
            _maxHeap.add( new Integer(0-val) ) ;
        }
    }
    
    /**
     * Calculates the median from the balanced min and max heaps.
     */
    private void calculateMedian()
    {
        int sizeMax = _maxHeap.size() ;
        int sizeMin = _minHeap.size() ;
        
        // mean = average of middle 2 values
        if( sizeMax == sizeMin )
        {
            int valMax = 0 - _maxHeap.peek().intValue() ;
            int valMin = _minHeap.peek().intValue() ;
            
            _median = (valMax + valMin) / 2.0 ;
        }
        
        // median is in max heap
        else if( sizeMax > sizeMin )
        {
            _median = 0 - _maxHeap.peek().intValue() ;
        }
        
        // median is in min heap
        else
        {
            _median = _minHeap.peek().intValue() ;
        }
    }
    
    
    /* --- OUTPUT ----------------------------------------------------------- */
    
    /**
     * Prints the running median to the specified file.
     * 
     * ###### tmp prints to std out ######
     */
    public void printRunningMedian()
    {
        System.out.println( _median + "" ) ;
    }
}