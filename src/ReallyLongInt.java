// CS 0445 Spring 2020
// This is a partial implementation of the ReallyLongInt class.  You need to
// complete the implementations of the remaining methods.  Also, for this class
// to work, you must complete the implementation of the LinkedListPlus class.
// See additional comments below.
/*
public class ReallyLongInt 	extends LinkedListPlus<Integer> 
							implements Comparable<ReallyLongInt>
{
	private ReallyLongInt()
	{
		super();
	}

	// Data is stored with the LEAST significant digit first in the list.  This is
	// done by adding all digits at the front of the list, which reverses the order
	// of the original string.  Note that because the list is doubly-linked and 
	// circular, we could have just as easily put the most significant digit first.
	// You will find that for some operations you will want to access the number
	// from least significant to most significant, while in others you will want it
	// the other way around.  A doubly-linked list makes this access fairly
	// straightforward in either direction.
	public ReallyLongInt(String s)
	{
		super();
		char c;
		int digit = -1;
		// Iterate through the String, getting each character and converting it into
		// an int.  Then make an Integer and add at the front of the list.  Note that
		// the add() method (from A2LList) does not need to traverse the list since
		// it is adding in position 1.  Note also the the author's linked list
		// uses index 1 for the front of the list.
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (('0' <= c) && (c <= '9'))
			{
				digit = c - '0';
				// Do not add leading 0s
				if (!(digit == 0 && this.getLength() == 0)) 
					this.add(1, new Integer(digit));
			}
			else throw new NumberFormatException("Illegal digit " + c);
		}
		// If number is all 0s, add a single 0 to represent it
		if (digit == 0 && this.getLength() == 0)
			this.add(1, new Integer(digit));
	}

	public ReallyLongInt(ReallyLongInt rightOp)
	{
		super(rightOp);
	}
	
	// Method to put digits of number into a String.  Note that toString()
	// has already been written for LinkedListPlus, but you need to
	// override it to show the numbers in the way they should appear.
	public String toString()
	{
	}

	// See notes in the Assignment sheet for the methods below.  Be sure to
	// handle the (many) special cases.  Some of these are demonstrated in the
	// RLITest.java program.

	// Return new ReallyLongInt which is sum of current and argument
	public ReallyLongInt add(ReallyLongInt rightOp)
	{
	}
	
	// Return new ReallyLongInt which is difference of current and argument
	public ReallyLongInt subtract(ReallyLongInt rightOp)
	{	
	}

	// Return -1 if current ReallyLongInt is less than rOp
	// Return 0 if current ReallyLongInt is equal to rOp
	// Return 1 if current ReallyLongInt is greater than rOp
	public int compareTo(ReallyLongInt rOp)
	{
	}

	// Is current ReallyLongInt equal to rightOp?
	public boolean equals(Object rightOp)
	{
	}

	// Mult. current ReallyLongInt by 10^num
	public void multTenToThe(int num)
	{
	}

	// Divide current ReallyLongInt by 10^num
	public void divTenToThe(int num)
	{
	}

}
*/