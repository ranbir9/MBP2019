package lab01;

/**
 * This class contains a utility method for finding the smallest difference.
 * 
 * @author Erin Parker & ??
 * @version January 21, 2022
 */
public class DiffUtil {

	/**
	 * Computes and returns the smallest difference (absolute value of subtraction) 
	 * among every pair of integers in the input array.
	 * @param arr - input array of integers
	 * @throws IllegalArgumentException - if the array contains less than two items
	 */
	public static int findSmallestDiff(int[] a) {
		  if (a.length < 2)
		    throw new IllegalArgumentException("Array must be > 1 element");
				
		  int diff = a[0] - a[1];
		  	if(diff < 0)
		  		diff *= -1; 
				
		  for (int i = 0; i < a.length; i++)
		    for (int j = i + 1; j < a.length; j++) {
		    	int tmp_diff;
		    	
		    	if(a[i] < 0)
		    		a[i] *= -1; 
		    	if(a[j] < 0)
		    		a[j] *= -1;
		    	
		    	tmp_diff = a[i] - a[j];
		    	if(tmp_diff < 0)
		    		tmp_diff *= -1; 
		      
						
		    	if (tmp_diff < diff)
		    		diff = tmp_diff;
		    }
		  return diff;
		}	
}
