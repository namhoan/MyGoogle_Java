import java.net.URL;
import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.net.MalformedURLException;
import java.io.IOException;
/**
hyung! you only need to write a 5 bodies where professor suggests to write //YOU HAVE TO WIRTE THE BODY OF THIS METHOD
You are to write a program that crawls the web looking for a word or phrase that you enter. You will tell it where to start the search (e.g. http://www.brandeis.edu) and it will first look for the word or phrase on that page.  
If it doesn't find it, it will look for links on the page and pick one at random, print it on the console, and then repeat the process with this new web address.   Links have the form
     <a href="http://........">
If it reads a page with no links it should stop and say it failed to find your word.
this one is hard to figure out the algorithm of the body but it will take short when you understand the main method.
*/


/**
* MyGoogle is an webspider that searches for a phrase on the web with a simple algorithm
* It starts with a particular webpage (e.g. http://www.brandeis.edu) and then takes the following steps
* <ul><li> read the webpage into an array of Strings (but only read the first 10000 lines if it is longer than 10000 lines)
* </li><li> print out each line (if any) that contains an occurrence of the searchPhrase and end the search if at least one was found
* </li><li> if no lines contain the search phrase then create an array of all the links on the page and pick one at random and continue
* </li><li> if there are no links then end the search
* </li><li> if it searches 1000 pages then give up (you might be in a loop)
* </li></ul>
* You can make this fancier by having it try multiple links
*
* @author Timothy Hickey
* @version 1.0.0
**/
public class MyGoogle {
    
   /**
   get the URL and the searchPhrase from the command line arguments and searchs for that phrase starting at that URL.
   Gives an error message if there are not two arguments.
   **/
   public static void main(String[] args){
      if (args.length!=2){System.out.println("Usage: java MyGoogle URL searchPhrase"); return;}
       
      String URLname = args[0];
      String searchPhrase = args[1];
      searchForPhrase(searchPhrase,URLname);
   }
   
   /**
   Searches the web for the searchPhrase starting at the specified URLname.
   
   The method first looks for the phrase on the page and if found, it prints out
   each occurrence (including 50 characters before and after the phrase).
   If the phrase does not appear on the page, then it picks a random link on the page
   and repeats the process.
   If it comes to a page with no links, it gives up.
   If it searches 1000 pages unsuccessfully, it gives up, 
   If it tries to follow a malformed URL, it gives up
   If it tries to follow a link to a page that doesn't exist, it gives up.
   Also, it only looks at the first 10,000 lines at most of a page
   and it only looks for one link on each line, any other links on that line are ignored.
   
   @param searchPhrase the string to be found on the web
   @param URLname the webpage at which to start searching
   
   **/
   public static void searchForPhrase(String searchPhrase, String URLname){
      
      boolean stillSearching = true;
      int numPagesSearched = 0;
      int maxNumPagesToSearch = 1000;
      
      while(stillSearching){
         System.out.println("\n       >>> searched "+numPagesSearched+" pages for '"+searchPhrase+"' on \n"+URLname);
         String[] lines = readWebPage(URLname);
         String[] matches = findMatches(searchPhrase,lines);
         if (matches.length>0){
            printMatches(searchPhrase, matches);
            stillSearching = false;
         }else {
            String[] links = findLinks(lines);
            if (links.length==0){
               stillSearching = false;
               System.out.println("no links on page ... giving up");
            }else {
               System.out.println("  found "+links.length+" links on "+lines.length+" lines.");
               URLname = getRandomLink(links);
            }
            
         }
         numPagesSearched++;
         if (numPagesSearched >= maxNumPagesToSearch){
            System.out.println("searched "+numPagesSearched+" .... giving up");
         }
         
      }
   }
   
   
   /**
   Reads the webpage specified by the URLname into an array of at most 10000 Strings.
   
   If the URLname is malformed or corresponds to an empty array, it returns the empty array
   (thereby giving up the search!)
   
   @param URLname the webpage to read
   @return an array containing the first 10000 lines on the webpage
   **/
   public static String[] readWebPage(String URLname){

      // first we try to create a URL from the name, but it might be malformed!
      // this is a good candidate for refactoring into its own method!
      URL webURL = null;     
      try {
         webURL = new URL(URLname);
      } catch(MalformedURLException e){
         System.out.println("found a malformed URL '"+URLname+"' ... giving up");
         return new String[0];
      }
      
      // next we try creating a scanner to read the page, but the page might not exist!
      // this is a good candidate for refactoring into its own method!
      Scanner scanPage;
      try{
         scanPage = new Scanner(webURL.openStream());
      } catch(IOException e){
         System.out.println("webpage doesn't exist: '"+URLname+"' ... giving up");
         return new String[0];
      }
      
      // finally we create an array to store the lines and start reading the page into the array
      // this is a good candidate for refactoring into its own method!
      int maxSize = 10000;
      String[] lines = new String[maxSize];
      int nextIndex = 0;
      
      while (scanPage.hasNext() && nextIndex < maxSize){
         lines[nextIndex++] = scanPage.nextLine();
      }
      
      // if we read 10000 lines then print a message to the user
      if (nextIndex == maxSize){System.out.println("long page: only reading first "+maxSize+" lines");}
      
      // finally, we return a trimmed down version of the array of just the right size
      return  Arrays.copyOfRange(lines,0,nextIndex);
   }
   
   
   /**
   returns an array of Strings consisting of all Strings in lines containing the searchPhrase.
   
   @param searchPhrase the String to be found
   @param lines the array of Strings to be searched
   @return the subarray of matching Strings
   **/
   public static String[] findMatches(String searchPhrase, String[] lines){
       // YOU HAVE TO WRITE THE BODY OF THIS METHOD
       String[] emptyArray = {};
       return emptyArray;
   }
   
   
   /**
   returns an array of URLnames that appear in HTML anchors in the array lines.
   
   @param lines an array of lines from a webpage
   @return an array of URLnames harvested from that webpage
   
   **/
   public static String[] findLinks(String[] lines){
       // YOU HAVE TO WRITE THE BODY OF THIS METHOD
       String[] emptyArray = {};
       return emptyArray;
   }
   
   
   /**
   returns the first URL name appearing in the String.
   
   It looks for the starting position s of the substring &lt;a href="http://
   and uses that to get the starting position s+9 of the URL:  http://
   It then looks for the position t of the first (") appearing after position s+9
   and it returns the substring between positions s and t.
   If there is no double quote, then it returns the entire rest of the line.
   
   @param line the string to be searched for a link
   @return a URL found on the line
   **/
   public static String getLink(String line){
       // YOU HAVE TO WRITE THE BODY OF THIS METHOD
       return "";
   }
   
   
   /**
   prints each of the matches on the screen with 50 characters of context on either side.
   
   @param searchPhrase the phrase appearing in each match
   @param matches the array of Strings containing the search phrase
   **/
   public static void printMatches(String searchPhrase, String[] matches){
       // YOU HAVE TO WRITE THE BODY OF THIS METHOD
      
   }
   
   
   /**
   returns a random element in the array of Strings.
   We assume that the array is not empty.
   
   @param links an array of Strings representing links on a page
   @return one of the Strings in the array, chosen at random.
   **/
   public static String getRandomLink(String[] links){
       // YOU HAVE TO WRITE THE BODY OF THIS METHOD
      Random r = new Random();
      
      return ""; 
   }
}
