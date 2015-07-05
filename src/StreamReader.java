import java.io.File ;
import java.io.FileNotFoundException ;

import java.util.Scanner ;

/**
 * A class which reads and parses a file of tweets into tweets and words. 
 * Each tweet is separated by a newline and each word is separated by whitespace.
 * 
 * @author Elizabeth Fong
 * @version 6th July 2015
 */
public class StreamReader
{
    // a scanner for the file - parses each tweet as a token
    private Scanner _fileScanner ;
    
    // scanner for each tweet - parses each word in the tweet as a token
    private Scanner _tweetScanner ;
    
    
    /* --- CONSTRUCTION ----------------------------------------------------- */
    
    /**
     * Constructor. Enables reading of the given file, using the given charset.
     * 
     * @param directory The name of the directory the file is located.
     * @param filename The name of the file to be read.
     * @param charset The character set of the given file.
     */
    public StreamReader( String directory , String filename , String charset )
    {
        try
        {
        	// directory not found
        	File file = new File( directory ) ;
        	
        	if( ! file.exists() )
        	{
        		file.mkdir() ;
        		System.out.println( "Please provide an input file named " + filename + " in the " + directory + " directory." ) ;
        		System.exit(0) ;
        	}
        	
        	// directory exists, try to open file
        	file = new File( directory + "/" + filename ) ;
            _fileScanner = new Scanner( file , charset ) ;
            _fileScanner.useDelimiter( System.lineSeparator() ) ;
        }
        catch( FileNotFoundException fnfe )
        {
            System.out.println( "Please provide an input file named " + filename + " in the " + directory + " directory." ) ;
            System.exit(0) ;
        }
    }
    
    
    /* --- FOR EACH TWEET --------------------------------------------------- */
    
    /**
     * Returns {@code true} if there is a next tweet, {@code false} otherwise.
     * 
     * @return {@code true} if there is a next tweet, {@code false} otherwise.
     */
    public boolean hasNextTweet()
    {
        return hasNext( _fileScanner ) ;
    }
    
    /**
     * Advances to the next tweet.
     */
    public void nextTweet()
    {
        _tweetScanner = new Scanner( _fileScanner.next() ) ;
    }
    
    
    /* --- FOR EACH WORD IN A TWEET ----------------------------------------- */
    
    /**
     * Returns {@code true} if there is a next word in the current tweet,
     * {@code false} otherwise.
     * 
     * @return {@code true} if there is a next word in the current tweet,
     *         {@code false} otherwise.
     */
    public boolean tweetHasNextWord()
    {
        return hasNext( _tweetScanner ) ;
    }
    
    /**
     * Returns the next word in the current tweet.
     * 
     * @return The next word in the current tweet.
     */
    public String tweetNextWord()
    {
        return _tweetScanner.next() ;
    }
    
    
    /* --- UTILITY METHODS -------------------------------------------------- */
    
    /**
     * Returns {@code true} if the given scanner has a next token, 
     * {@code false} otherwise.
     * 
     * @param scanner The scanner to be checked if it has a next token.
     * 
     * @return {@code true} if the given scanner has a next token, 
     *         {@code false} otherwise.
     */
    private boolean hasNext( Scanner scanner )
    {
        boolean hasNext = scanner.hasNext() ;
        
        // closes scanner if does not have next token
        if( ! hasNext )
        {
            scanner.close() ;
            
            if( scanner == _tweetScanner )
            {
                _tweetScanner = null ;
            }
            else
            {
                _fileScanner = null ;
            }
        }
        
        return hasNext ;
    }
}