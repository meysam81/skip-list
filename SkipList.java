/* here's my reference for further study:
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 * */


import java.util.Random;

public class SkipList {
	public SkipListEntry head, tail; // to hold the ends of a list in a height
	
	public int n; // number of nodes in the list
	
	public int h; // height of the list = height of the highest tower in list + 1
	
	public Random r; // to determine the height of a specific node | the size of its tower
	
	
	
	

	  /* -------------------------------------------------------------------------------------
	     Constructor: empty skiplist

	                              null                     null
	                               ^                         ^
	                               |                         |
	                        ---------------          ---------------
	                        |    "head"   |          |    "tail"   |
	              null <--  |    -inf     |  <---->  |    +inf     |  --> null
	                        |             |          |             |
	                        ---------------          --------------- 
	                               |                         |
	                               v                         v
	                              null        			   null
	     -------------------------------------------------------------------------------------- */
	
	
	public SkipList() // default constructor
	{
		SkipListEntry p1 = new SkipListEntry(SkipListEntry.negInf, null),
				p2 = new SkipListEntry(SkipListEntry.posInf, null); // define list ends with +inf & -inf
		
		p1.right = p2;
		p2.left = p1;   // pointing toward each other
		
		head = p1;
		tail = p2;
		
		
		// The initial list is at height 0 and has zero elements in it
		n = 0;
		h = 0;
		
		r = new Random();
		
		
	}
	
	
	
	/* The very basic operations needed for this list:
	 * 	-get
	 * 	-put
	 * 	-remove
	 * 
	 * so now we'll implement these babies!
	 * */
	/* We have a list of all elements in the last row, and also a copy of each element
	 * in a 'r' number of height above it which makes another list on top of the last*/
	
	
	
	// for use in my own class, and not outside
	private SkipListEntry findEntry(Integer k)
	{
		/* ------------------------------------------------------
	     findEntry(k): find the largest key x <= k
			   on the LOWEST level of the Skip List
	     ------------------------------------------------------ */
		SkipListEntry p = head;
		while (true)
		{
			/* --------------------------------------------
			   Search RIGHT until you find a LARGER entry

		           E.g.: k = 34

		                     10 ---> 20 ---> 30 ---> 40
		                                      ^
		                                      |
		                                      p stops here
				p.right.key = 40
			   -------------------------------------------- */
			
			// go right until it reaches an element greater than it self
			while (p.right.key != SkipListEntry.posInf && p.right.value.compareTo(k) <= 0)
				p = p.right;
			
			// if it is not the last line
			if (p.down != null)
				p = p.down;
			else 
				break;
			
		}
		return p;
	}
	
	
	// if found, return value
	public Integer get(Integer k)
	{
		SkipListEntry p = findEntry(k);
		if (p.value == k)
			return p.value;
		else
			return null;
		
	}
	public SkipListEntry insertRowAbove(SkipListEntry p, SkipListEntry q, String k)
	{
		SkipListEntry e = new SkipListEntry(k, null);
		
		e.left = p;
		e.right = p.right;
		e.down = q;
		
		p.left.right = e;
		p.right = e;
		q.up = e;
		
		return e;
	}
	public void put(String k, Integer v)
	{
		SkipListEntry p = findEntry(v),
				q = new SkipListEntry(k, v);
		// add q to the right of p & edit corresponding links
		q.left = p;
		q.right = p.right;
		p.right.left = q;
		p.right = q;
		
		// make a tower of the current element to add to upper lists
		int i = 0;
		while (r.nextDouble() > 0.5) // choose by a random between 0-1 if we should add another layer above the current
		{
			if (i >= h) // we should add another empty list above the top level to be able to increase our tower
			{
				
				SkipListEntry p1, p2;
		
			    h = h + 1;
	
	            p1 = new SkipListEntry(SkipListEntry.negInf,null);
	            p2 = new SkipListEntry(SkipListEntry.posInf,null);
	   
		   	    p1.right = p2;
			    p1.down  = head;
	
			    p2.left = p1;
			    p2.down = tail;
	
			    head.up = p1;
			    tail.up = p2;
	
			    head = p1;
			    tail = p2;
			}
			
			while (p.up == null) // find the first smaller element in the layer above the current 
				p = p.left;
			
			
			p = p.up; // get to it then!
			
			// add the node & initialize the links correctly
			SkipListEntry e = new SkipListEntry(v.toString(), v);
			
			e.left = p;
			e.right = p.right;
			e.down = q;
			
			p.right.left = e;
			p.right = e;
			q.up = e;
			
			q = e; // for next iteration
			
			i++;
		}
		
		n++; // number of list elements
		
	}
	
	
	public void printHorizontal()
	  {
	     String s = "";
	     int i;

	     SkipListEntry p;

	     /* ----------------------------------
		Record the position of each entry
		---------------------------------- */
	     p = head;

	     while ( p.down != null )
	     {
	        p = p.down;
	     }

	     i = 0;
	     while ( p != null )
	     {
	        p.pos = i++;
	        p = p.right;
	     }

	     /* -------------------
		Print...
		------------------- */
	     p = head;

	     while ( p != null )
	     {
	        s = getOneRow( p );
		System.out.println(s);

	        p = p.down;
	     }
	  }

	  public String getOneRow( SkipListEntry p )
	  {
	     String s;
	     int a, b, i;

	     a = 0;

	     s = "" + p.key;
	     p = p.right;


	     while ( p != null )
	     {
	        SkipListEntry q;

	        q = p;
	        while (q.down != null)
		   q = q.down;
	        b = q.pos;

	        s = s + " <-";


	        for (i = a+1; i < b; i++)
	           s = s + "--------";
	 
	        s = s + "> " + p.key;

	        a = b;

	        p = p.right;
	     }

	     return(s);
	  }

	  public void printVertical()
	  {
	     String s = "";

	     SkipListEntry p;

	     p = head;

	     while ( p.down != null )
	        p = p.down;

	     while ( p != null )
	     {
	        s = getOneColumn( p );
		System.out.println(s);

	        p = p.right;
	     }
	  }


	  public String getOneColumn( SkipListEntry p )
	  {
	     String s = "";

	     while ( p != null )
	     {
	        s = s + " " + p.key;

	        p = p.up;
	     }

	     return(s);
	  }
	
	
}