// CS 0445 Spring 2020
// This is a partial implementation of the ReallyLongInt class.  You need to
// complete the implementations of the remaining methods.  Also, for this class
// to work, you must complete the implementation of the LinkedListPlus class.
// See additional comments below.

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
	    StringBuilder out = new StringBuilder();
	    Node temp = firstNode.prev;
	    int length = getLength();

	    for (int i = 0; i <length; i++)
		{
			out.append(temp.data);
			temp = temp.prev;
		}

	    return out.toString();
	}

	// See notes in the Assignment sheet for the methods below.  Be sure to
	// handle the (many) special cases.  Some of these are demonstrated in the
	// RLITest.java program.

	// Return new ReallyLongInt which is sum of current and argument
	public ReallyLongInt add(ReallyLongInt rightOp)
	{
	    ReallyLongInt longer;
	    ReallyLongInt shorter;
	    ReallyLongInt total = new ReallyLongInt();

	    if (getLength()>rightOp.getLength())
        {
            longer = new ReallyLongInt(this);
            shorter = new ReallyLongInt(rightOp);
        }
	    else if (getLength()<rightOp.getLength())
        {
            longer = new ReallyLongInt(rightOp);
            shorter = new ReallyLongInt(this);
        }
	    else
        {
            longer = new ReallyLongInt(this);
            shorter = new ReallyLongInt(rightOp);
        }

	   //-----------------------------Same length code------------------------------------
			Node longNode = longer.firstNode;
			Node shortNode = shorter.firstNode;

			int carry = 0;

			for (int i = 0;i<shorter.getLength();i++)
			{
				int column = longNode.data+shortNode.data+carry;

				if (column >=10)
				{carry = 1;
				 total.add((column-10)); }
				else
				{carry = 0;
				 total.add(column); }

				longNode = longNode.next;
				shortNode = shortNode.next;
			}
		//--------------------------------------------------------------------------------
		//------------------------------unequal length code-------------------------------
			int remainingColumns = longer.getLength()-shorter.getLength();

			if (remainingColumns >0) {
				for (int i = 0; i < remainingColumns; i++) {
					int column = longNode.data + carry;
					if (column >= 10) {
						carry = 1;
						total.add((column - 10));
					} else {
						carry = 0;
						total.add(column);
					}
					longNode = longNode.next;
					shortNode = shortNode.next;
				}
				if (carry > 0) {
					total.add(carry);
				}
			}
	    return total;
	}
	
	// Return new ReallyLongInt which is difference of current and argument
	public ReallyLongInt subtract(ReallyLongInt rightOp)
	{
		//dont even bother subtracting if we'd end up with a negative number. Just throw an error
	    if (this.compareTo(rightOp) < 0)
		{
			throw new ArithmeticException("Subtraction cannot result in a negative");
		}

	    //copy of leftOp to do mathematics on
	    ReallyLongInt tempThis = new ReallyLongInt(this);

	    //start at lowest significant number of both vars
	    Node leftNode = tempThis.firstNode;
		Node rightNode = rightOp.firstNode;

		//carry flag
		boolean borrow_flag=false;

		//our result variable
		ReallyLongInt result = new ReallyLongInt();

		//iterate once for every digit in the rightOP
		for (int i=0;i<rightOp.numberOfEntries;i++)
		{
			if (borrow_flag)
			{
				leftNode.data -=1;
			}

			if (leftNode.data >= rightNode.data) {
				//just subtract
				int column = leftNode.data - rightNode.data;
				result.add(column);

				borrow_flag = false;

				leftNode = leftNode.next;
				rightNode = rightNode.next;
			} else {
				leftNode.data += 10;
				int column = leftNode.data - rightNode.data;
				result.add(column);

				borrow_flag = true;

				leftNode = leftNode.next;
				rightNode = rightNode.next;
				}

		}

		int remainingColumns = tempThis.numberOfEntries-rightOp.numberOfEntries;
		for (int i = 0;i<remainingColumns;i++)
		{
			if (borrow_flag&&(leftNode.data != 0))
			{
				leftNode.data -=1;
			}
			else if (borrow_flag&&(leftNode.data == 0))
			{
				leftNode.data=+9;
				borrow_flag = true;
			}

			if (leftNode.data !=0) {
				result.add(leftNode.data);
			}
			leftNode = leftNode.next;
		}


	    return result;

	}

	// Return -1 if current ReallyLongInt is less than rOp
	// Return 0 if current ReallyLongInt is equal to rOp
	// Return 1 if current ReallyLongInt is greater than rOp
	public int compareTo(ReallyLongInt rOp)
	{
		if (this.equals(rOp))
		{
			return 0;
		}
		else if (getLength() > rOp.getLength())
		{
			return 1;
		}
		else if (getLength() < rOp.getLength())
		{
			return -1;
		}
		else
		{
			Node thisNode = firstNode.prev;
			Node thatNode = rOp.firstNode.prev;

			for (int i =0;i<numberOfEntries;i++)
			{
				if (thisNode.data > thatNode.data)
				{
					return 1;
				}
				else if (thatNode.data > thisNode.data)
				{
					return -1;
				}

				thisNode = thisNode.prev;
				thatNode = thatNode.prev;
			}

			//we should never reach this
			return 999;
		}
	}

	// Is current ReallyLongInt equal to rightOp?
	public boolean equals(Object rightOp)
	{
		ReallyLongInt temp = (ReallyLongInt)rightOp;

		if (getLength()!=temp.getLength() )
		{
			return false;
		}
		else
		{
			Node thisNode = firstNode;
			Node thatNode = ((ReallyLongInt) rightOp).firstNode;

			for (int i = 0; i < numberOfEntries; i++)
			{
				if (thisNode.getData().equals(thatNode.getData())) {

					thisNode = thisNode.prev;
					thatNode = thatNode.prev;

				} else {
					return false;
				}
			}
			return true;
		}
	}

	// Mult. current ReallyLongInt by 10^num
	public void multTenToThe(int num)
	{
		//special case for 0
		if (firstNode.prev.data ==0)
		{
			return;
		}

		for (int i = 0; i< num; i++)
		{
			add(1,0);
		}

	}

	// Divide current ReallyLongInt by 10^num
	public void divTenToThe(int num)
	{
		//special case for 0
		if (firstNode.prev.data ==0)
		{
			return;
		}

		if(num>this.numberOfEntries)
		{
			this.clear();
			add(0);
		}
		else {
			leftShift(num);
		}
	}

}
