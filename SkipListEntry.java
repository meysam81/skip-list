/* here's my reference for further study:
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 * */
public class SkipListEntry {
	
	public Integer value; // value of the node
	public String key;
	
	public int pos; // to print the skip-list nicely
	
	public SkipListEntry up, down, left, right; // links to neighbors
	
	public static String negInf = "-oo", posInf = "+oo"; // -inf & +inf for list ends
	
	public SkipListEntry(String key, Integer value)
	{
		this.key = key;
		this.value = value;
		
		up = right = down = left = null;
	}
	
	
	/*
	 * we create a list like below:
	 * 				------------------
	 * 				|	key, value	 |
	 * 				------------------
	 * 				|	  upLink     |
	 * 				------------------
	 * 				|	 rightLink   |
	 * 				------------------
	 * 				|	 downLink    |
	 * 				------------------
	 * 				|	 leftLink    |
	 * 				------------------
	 * */
}
