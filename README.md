# Inisght Coding Challenge   
_Author:_ Elizabeth Fong   
_Submission Date:_ 10th July 2015   
_Language:_ Java   

## Program Files   
### TwitterAnalysis   
The main class. Executing this runs the program.   

__Complexity:__   
The complexity is _O(mn)_, where m is the number of tweets and n is the average number of words in each tweet.   

No library classes used.   

### StreamReader   
This uses two Scanners to read and parse the input file, _./tweet_input/tweets.txt_, into tweets, then into words. A Scanner used to return each tweet and another takes in the returned tweet to return each word when the method, _tweetNextWord()_, is called. I used two Scanners because I needed to count the unique words each tweet separately.     

__Library classes used:__
* java.io   
   * File
   * FileNotFoundException
* java.util
   * Scanner

### WordsAnalysis   
This is part 1. This uses a _TreeMap<String,Integer>_ to keep track of the word count for each unique word, for either a tweet, or the entire stream of tweets. I chose this data structure because adding and obtaining key-value pairs have a complexity of _O(log n)_. The methods _put(K,V)_ and _remove(K)_ are invoked each time a new word is added to the map, giving it a compelexity of _O(2 log n)_ per word.   

__Complexity:__    
O(log n) per word, where n is the number of keys currently in the map.   

__Library classes used:__
* java.io (for writing to ./tweet_output/ft1.txt)
   * BufferedWriter
   * File
   * FileWriter
   * IOException
* java.util
   * Iterator (to iterate through all the keys for printing)
   * TreeMap (the data structure)

### RunningMedian   
This is part 2. This calculates the running median using 2 heaps, a max heap and a min heap. The max heap stores values that are less than the current running median and the max heap stores the values that are greater than or equal to the current running median. I used this data structure because adding a value has a complexity of _O(2 log n + 1)_ as each heap is a priority queue and balancing both queues require an add and remove operation. Removing either the minimum or maximum has a complexity of _O(1)_, which makes the complexity for calculating the running median _O(1)_.   

__Complexity:__
* add: O(log n)
* remove: O(1)   

__Library classes used:__
* java.io (for writing to ./tweet_output/ft2.txt)
   * BufferedWriter
   * File
   * FileWriter
   * IOException
* java.util.PriorityQueue (for implementing the heaps)   