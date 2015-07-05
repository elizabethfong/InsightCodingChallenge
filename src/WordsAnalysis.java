import java.io.BufferedWriter ;
import java.io.File ;
import java.io.FileWriter ;
import java.io.IOException ;

import java.util.Iterator ;
import java.util.TreeMap ;

/**
 * Part 1.
 * Keeps track of each unique word in a tweet or in a stream of tweets.
 * Also keeps track of how many times each of the unique words occur.
 * 
 * @author Elizabeth Fong
 * @version 6th July 2015
 */
public class WordsAnalysis
{
	// file for printing output
	private final String _directory ;
	private final String _filename ;
	
    // unique words stored in a map, as <Word,Occurrence> pairs.
    private TreeMap<String,Integer> _map ;
    
    
    /* --- CONSTRUCTION ----------------------------------------------------- */
    
    /**
     * Constructor. Initializes the map used for keeping track of each word and
     * their word counts.
     */
    public WordsAnalysis()
    {
    	this( null , null ) ;
    }
    
    /**
     * Constructor. Initializes the map used for keeping track of each word and
     * their word counts. This takes in a filename for printing output using the
     * {@code printWordCounts()} method.
     * 
     * @param directory The name of the directory the output file should be located.
     * @param filename The name of the output file.
     */
    public WordsAnalysis( String directory , String filename )
    {
    	_directory = directory ;
    	_filename = filename ;
    	_map = new TreeMap<String,Integer>() ;
    }
    
    
    /* --- ADD -------------------------------------------------------------- */
    
    /**
     * Add an extra occurrence of the given word to the map.
     * 
     * @param word The word to be added to the map.
     */
    public void addWord( String word )
    {
        addWord( word , 1 ) ;
    }
    
    /**
     * Add the specified word count of the given word to the map.
     * 
     * @param word The word to be added to the map.
     * @param count The number of times the given word has occurred.
     */
    private void addWord( String word , int count )
    {
        Integer original = _map.remove( word ) ;
        
        if( original == null )
        {
            _map.put( word , new Integer(count) ) ;
        }
        else
        {
            int o = original.intValue() ;
            _map.put( word , new Integer(o+count) ) ;
        }
    }
    
    /**
     * Adds all the words and their respective word counts in the given
     * {@code WordsAnalysis} object to this map.
     * 
     * @param words The {@code WordsAnalysis} object with the words and
     *              and word counts to be added to this map.
     */
    public void addWords( WordsAnalysis words )
    {
        Iterator<String> iterator = words._map.keySet().iterator() ;
        
        while( iterator.hasNext() )
        {
            String key = iterator.next() ;
            int val = words._map.get(key).intValue() ;
            
            addWord( key , val ) ;
        }
    }
    
    
    /* --- OUTPUT ----------------------------------------------------------- */
    
    /**
     * Returns the number of unique words in this tweet or this set of tweets.
     * 
     * @return The number of unique words in this tweet or this set of tweets.
     */
    public int numUniqueWords()
    {
        return _map.size() ;
    }
    
    
    /**
     * Prints each word and their respective word counts to the specified
     * output file.
     */
    public void printWordCounts()
    {
    	try
    	{
        	BufferedWriter writer = createWriter() ;
        	Iterator<String> iterator = _map.navigableKeySet().iterator() ;
        	
        	// print each key and its value from map to file
        	while( iterator.hasNext() )
        	{
        		String key = iterator.next() ;
        		int val = _map.get( key ).intValue() ;
        		
        		String formatted = String.format( "%-50s %d" , key , val ) ;
        		
        		writer.write( formatted ) ;
        		writer.newLine() ;
        		
        		System.out.println( formatted ) ;
        	}
        	
        	writer.close() ;
    	}
    	catch( IOException ioe )
    	{
    		System.out.println( "IOException on writing word counts to file - WordsAnalysis" ) ;
    		ioe.printStackTrace() ;
    	}
    }
    
    /**
     * Returns a {@code BufferedWriter} to write to the specified file.
     * If the file or directory does not exist, create them.
     * 
     * @return The created {@code BufferedWriter}.
     * 
     * @throws IOException Thrown when an IO Exception occurs.
     */
    private BufferedWriter createWriter() throws IOException
    {
    	// directory not found
    	File file = new File( _directory ) ;
    	
    	if( ! file.exists() )
    	{
    		file.mkdir() ;
    	}
    	
    	// file not found
    	file = new File( _directory + "/" + _filename ) ;
    	
    	if( ! file.exists() )
    	{
    		file.createNewFile() ;
    	}
    	
    	// init writing
    	return new BufferedWriter( new FileWriter(file.getAbsoluteFile(),false) ) ;
    }
}