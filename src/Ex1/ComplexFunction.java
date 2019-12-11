package Ex1;
/**
 * This class represents a complex function of shape operation(f(x),g(x)) ,the operation is an enom that contains {Plus, Times, Divid, Max, Min, Comp , None, Error}
 *  the functions can be Monom, Polynom or ComplexFunction.  
 * The class implements complex_function and support simple operations as: construction, value at x, plus,multiply,divide,min,max and comp
 * @author itay and lilach
 *
 */
public class ComplexFunction implements complex_function
{
	private function right;
	private function left;
	private Operation op;
	
	/**
	 * Initialized a ComplexFunction given Operation function left and right
	 * @param Operation op2,function left2, function right2
	 */
	public ComplexFunction( Operation op2,function left2, function right2) 
	{
		if(right2!=null&&op2!=Operation.None)
		{
			this.left=left2;
			this.right=right2;
			this.op=op2;		
		}
		else
			if(op2==Operation.None&&right2==null)
			{
				this.left=left2;
				this.right=null;
				this.op=op2;
			}
			else
				throw new RuntimeException("ERR right function can't be null if the Operation is diffrent from none");
	}
	/**
	 * Initialized a ComplexFunction given string function left and right
	 * op2 is a string that represent Operation
	 * @param String op2,function left2, function right2
	 */
	public ComplexFunction( String op2,function left2, function right2) 
	{
		ComplexFunction temp=null;
		switch(op2)
		{//case for each operation
			case "plus":
			{
				temp= new ComplexFunction(Operation.Plus,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
				break;
			}
			case "mul":
			{
				temp= new ComplexFunction(Operation.Times,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
				break;
			}
			case "div":
			{
				temp= new ComplexFunction(Operation.Divid,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
				break;
			}
			case "max":
			{
				temp= new ComplexFunction(Operation.Max,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
				break;
			}
			case "min": 
			{
				temp= new ComplexFunction(Operation.Min,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
				break;
			}
			case "comp":
			{
				temp= new ComplexFunction(Operation.Comp,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
				break;
			}
			case "none":
			{
				if(right2==null) 
				{//if its none so there is just a left function
					temp= new ComplexFunction(Operation.None,left2,null);
					this.op=temp.getOp();
					this.left=temp.left();
					this.right=temp.right();
					break;
				}
				else
					throw new RuntimeException("ERR for none f2 must be null ");
			}
			default :
			{
				throw new RuntimeException("ERR the operation is not legal");
			}
		}
	}
	/**
	 * Initialized a ComplexFunction given function, the type of the complex function will be none(f,)
	 * @param function f
	 */
	public ComplexFunction(function f) 
	{
		this.left=f;
		this.right=null;
		this.op=Operation.None;
	}
	/**
	 * Initialized a ComplexFunction given string
	 * @param String s
	 */
	public ComplexFunction(String s) 
	{
		if(this.parenthesisTest(s))//check if the structure of the parenthesis and comma is legal
		{
			s=s.replaceAll("\\s+","");//delete all the spacing
			char c=s.charAt(0);
			if(c!='m'&&c!='c'&&c!='d'&&c!='n'&&c!='p')//if its not a complex function
			{
				function p=new Polynom(s);
				ComplexFunction complex=new ComplexFunction(Operation.None,p,null);
				this.op=complex.op;
				this.left=complex.left;
				this.right=complex.right;
			}
			else
				if(s.length()>3)
				{
					switch(s.substring(0,3))//if it is still a complex function continue to split
					{
						case "mul":
						{
							if(s.charAt(3)=='('&&s.charAt(s.length()-1)==')')
							{
								int split=this.findComma(s);	
								ComplexFunction complex=new ComplexFunction(Operation.Times,new ComplexFunction(s.substring(4,split)),new ComplexFunction(s.substring(split+1,s.length()-1)));
								this.op=complex.op;
								this.left=complex.left;
								this.right=complex.right;
								break;
							}
							else
								throw new RuntimeException("ERR the string is not a ComplexFunction");
						}
						case "min":
						{
							if(s.charAt(3)=='('&&s.charAt(s.length()-1)==')')
							{
								int split=this.findComma(s);	
								ComplexFunction complex=new ComplexFunction(Operation.Min,new ComplexFunction(s.substring(4,split)),new ComplexFunction(s.substring(split+1,s.length()-1)));
								this.op=complex.op;
								this.left=complex.left;
								this.right=complex.right;
								break;
							}
							else
								throw new RuntimeException("ERR the string is not a ComplexFunction");
						}
						case "max":
						{
							if(s.charAt(3)=='('&&s.charAt(s.length()-1)==')')
							{
								int split=this.findComma(s);	
								ComplexFunction complex=new ComplexFunction(Operation.Max,new ComplexFunction(s.substring(4,split)), new ComplexFunction(s.substring(split+1,s.length()-1)));
								this.op=complex.op;
								this.left=complex.left;
								this.right=complex.right;
								break;
							}
							else
								throw new RuntimeException("ERR the string is not a ComplexFunction");
						}
						case "div":
						{
							if(s.charAt(3)=='('&&s.charAt(s.length()-1)==')')
							{
								int split=this.findComma(s);
								ComplexFunction complex=new ComplexFunction(Operation.Divid,new ComplexFunction(s.substring(4,split)),new ComplexFunction(s.substring(split+1,s.length()-1)));
								this.op=complex.op;
								this.left=complex.left;
								this.right=complex.right;
								break;
							}
							else
								throw new RuntimeException("ERR the string is not a ComplexFunction");
						}
						case "com":
						{
							if(s.charAt(3)=='p'&&s.charAt(4)=='('&&s.charAt(s.length()-1)==')')
							{
								int split=this.findComma(s);	
								ComplexFunction complex=new ComplexFunction(Operation.Comp,new ComplexFunction(s.substring(5,split)),new ComplexFunction(s.substring(split+1,s.length()-1)));
								this.op=complex.op;
								this.left=complex.left;
								this.right=complex.right;
								break;
							}
							else
								throw new RuntimeException("ERR the string is not a ComplexFunction");
						}
						case "plu":
						{
							if(s.charAt(3)=='s'&&s.charAt(4)=='('&&s.charAt(s.length()-1)==')')
							{
								int split=this.findComma(s);	
								ComplexFunction complex=new ComplexFunction(Operation.Plus,new ComplexFunction(s.substring(5,split)),new ComplexFunction(s.substring(split+1,s.length()-1)));
								this.op=complex.op;
								this.left=complex.left;
								this.right=complex.right;
								break;
							}
							else
								throw new RuntimeException("ERR the string is not a ComplexFunction");
						}
						case "non":
						{
							if(s.charAt(3)=='e'&&s.charAt(4)=='('&&s.charAt(s.length()-1)==')')
							{
								int split=this.findComma(s);	
								if(s.substring(split+1,s.length()-1).isEmpty()) 
								{
									ComplexFunction complex=new ComplexFunction(Operation.None,new ComplexFunction(s.substring(5,split)),null);
									this.op=complex.op;
									this.left=complex.left;
									this.right=complex.right;
									break;
								}
								else
									throw new RuntimeException("ERR the string is not a ComplexFunction");

							}
							else
								throw new RuntimeException("ERR the string is not a ComplexFunction");
						}
						default :
							throw new RuntimeException("ERR the string is not a ComplexFunction");
					}
				}
				else
					throw new RuntimeException("ERR the string is not a ComplexFunction");
		}
		else
			throw new RuntimeException("ERR the string is not a ComplexFunction");
	}
	/**
	 * @return a function that create a complexfunction from string
	 * @param String s
	 */
	public function initFromString(String s) 
	{
		function f=new ComplexFunction(s);
		return f;
	}
	/**
	 * @return the "split" place in the string for function left and  function right
	 * @param String s
	 */
	private int findComma(String s)
	{
		int open=0;
		int comma=0;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='(')
				open++;
			if(s.charAt(i)==',')
				comma++;
			if(comma==open&&comma!=0)//the first place in the string that comma and open are equals and different from zero
				return i;
		}
		return -1;
	}
	/**
	 * @return true if the structure of parenthesis and comma in the string is legal
	 * @param String s
	 */
	private boolean parenthesisTest(String s) 
	{
		int open=0;
		int close=0;
		int comma=0;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='(')
				open++;
			if(s.charAt(i)==')')
				close++;
			if(s.charAt(i)==',')
				comma++;
			if(comma>open||close>comma)//the structure is illegal
				return false;
		}
		if(open==close&&open==comma)//if the string is legal the number of the open and close parenthesis and comma is equals
			return true;
		return false;		
	}
	/**
	 * @return the value of function f at x
	 * @param double x
	 */
	public double f(double x) 
	{
		double sum=0;
		switch(this.op)
		{ //Recursively check for each action
			case Plus:
			{
				sum=this.left.f(x)+this.right.f(x);
				return sum;
			}
			case Times:
			{
				sum=this.left.f(x)*this.right.f(x);
				return sum;
			}
			case Divid:
			{
					sum=this.left.f(x)/this.right.f(x);
					return sum;
			}
			case Max:
			{
				sum=this.left.f(x);
				if(sum<this.right.f(x))
					return this.right.f(x);
				else
					return sum;
			}
			case Min: 
			{
				sum=this.left.f(x);
				if(sum>this.right.f(x))
					return this.right.f(x);
				else
					return sum;
			}
			case Comp:
			{
				return this.left.f(this.right.f(x));
			}
			case None:
			{
				return this.left.f(x);
	
			}
			default :
			{
				throw new RuntimeException("ERR operation");
			}
		}
	}
	/**
	 * create function from the complexfunction(deep copy)
	 * @return function
	 */
	public function copy() 
	{
		function temp=new ComplexFunction(this.toString());
		return temp;
	}
	/**
	 * change the structure of the complex function f1 will be the right function
	 * and the previous one will be the left function,the operation will change to plus
	 * @param function f1
	 */
	public void plus(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		if(this==f1)
		{
			ComplexFunction copy=(ComplexFunction) f1.copy();
			this.left=temp;
			op=Operation.Plus;
			this.right=copy;
		}
		else
		{
			this.left=temp;
			op=Operation.Plus;
			this.right=f1;
		}
	}
	/**
	 * change the structure of the complex function f1 will be the right function
	 * and the previous one will be the left function,the operation will change to mul
	 * @param function f1
	 */
	public void mul(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		if(this==f1)
		{
			ComplexFunction copy=(ComplexFunction) f1.copy();
			this.left=temp;
			op=Operation.Times;
			this.right=copy;
		}
		else
		{
			this.left=temp;
			op=Operation.Times;
			this.right=f1;
		}

	}
	/**
	 * change the structure of the complex function f1 will be the right function
	 * and the previous one will be the left function,the operation will change to div
	 * @param function f1
	 */
	public void div(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		if(this==f1)
		{
			ComplexFunction copy=(ComplexFunction) f1.copy();
			this.left=temp;
			op=Operation.Divid;
			this.right=copy;
		}
		else
		{
			this.left=temp;
			op=Operation.Divid;
			this.right=f1;
		}
	}
	/**
	 * change the structure of the complex function f1 will be the right function
	 * and the previous one will be the left function,the operation will change to max
	 * @param function f1
	 */
	public void max(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		if(this==f1)
		{
			ComplexFunction copy=(ComplexFunction) f1.copy();
			this.left=temp;
			op=Operation.Max;
			this.right=copy;
		}
		else
		{
			this.left=temp;
			op=Operation.Max;
			this.right=f1;
		}
	}
	/**
	 * change the structure of the complex function f1 will be the right function
	 * and the previous one will be the left function,the operation will change to min
	 * @param function f1
	 */
	public void min(function f1)
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		if(this==f1)
		{
			ComplexFunction copy=(ComplexFunction) f1.copy();
			this.left=temp;
			op=Operation.Min;
			this.right=copy;
		}
		else
		{
			this.left=temp;
			op=Operation.Min;
			this.right=f1;
		}
	}
	/**
	 * change the structure of the complex function f1 will be the right function
	 * and the previous one will be the left function,the operation will change to comp
	 * @param function f1
	 */
	public void comp(function f1)
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		if(this==f1)
		{
			ComplexFunction copy=(ComplexFunction) f1.copy();
			this.left=temp;
			op=Operation.Comp;
			this.right=copy;
		}
		else
		{
			this.left=temp;
			op=Operation.Comp;
			this.right=f1;
		}
	}
	/**
	 * @return the left function
	 */
	public function left() 
	{
		return this.left;
	}
	/**
	 * @return the right function
	 */
	public function right() 
	{
		if(this.right!=null)
		{
			return this.right;
		}
		else 
			return null;
	}
	/**
	 * @return the Operation
	 */
	public Operation getOp() 
	{
		return this.op;
	}
	/**
	 * Test if this complexfunction is logically equals to Object o, The test is not completely correct,
	 *  it checks if between 10 to 10 the functions are logically equals and for 5 random values 
	 * @param Object o
	 * @return true if this complexfunction  represents the same function as object o
	 */
	public boolean equals(Object o)
	{
		if(o instanceof Polynom_able ) 
		{
			Polynom p=(Polynom)o;
			double EPSILON = 0.01;
			for(double x=-5;x<5;x+=EPSILON) //check from -5 to 5 if the function are equals by epsilon
			{
				if(Math.abs(this.f(x)-p.f(x))>0.0001)
					return false;
			}
			for(int i=0;i<=5;i++) //check if the function are equals at 5 random numbers
			{
				double x=Math.random()*100;
				if(Math.abs(this.f(x)-p.f(x))>0.0001)
					return false;
			}
			return true;
		}
		else if(o instanceof Monom)
		{
			if(this.right!=null)
				return false;
			if(this.op!=Operation.None)
				return false;
			Monom m=(Monom)o;
			double EPSILON = 0.01;
			for(double x=-5;x<5;x+=EPSILON) //check from -5 to 5 if the function are equals by epsilon
			{
				if(Math.abs(this.f(x)-m.f(x))>0.0001)
					return false;
			}
			for(int i=0;i<=5;i++) //check if the function are equals at 5 random numbers
			{
				double x=Math.random()*100;
				if(Math.abs(this.f(x)-m.f(x))>0.0001)
					return false;
			}
			return true;
		}
		else if(o instanceof ComplexFunction) 
		{
			ComplexFunction cf=(ComplexFunction)o;
			double EPSILON = 0.01;

			for(double x=-5;x<5;x+=EPSILON) //check from -5 to 5 if the function are equals by epsilon
			{
				if(Math.abs(this.f(x)-cf.f(x))>0.0001)
					return false;
			}
			for(int i=0;i<=5;i++) //check if the function are equals at 5 random numbers
			{
				double x=Math.random()*100;
				if(Math.abs(this.f(x)-cf.f(x))>0.0001)
					return false;
			}
			return true;
		}
		else
			return false;
	}
	/**
	 * @return a string that represent this complexfunction
	 * 
	 */
	public String toString() 
	{
		if(this.op==Operation.Comp) {
			return "comp("+this.left.toString()+","+this.right.toString()+")";
		}
		else if(this.op==Operation.Divid) {
			return  "div("+this.left.toString()+","+this.right.toString()+")";
		}
		else if(this.op==Operation.Max) {
			return "max("+this.left.toString()+","+this.right.toString()+")";
		}
		else if(this.op==Operation.Min) {
			return "min("+this.left.toString()+","+this.right.toString()+")";
		}
		else if(this.op==Operation.None) {
			return this.left.toString();
		}
		else if(this.op==Operation.Plus) {
			return "plus("+this.left.toString()+","+this.right.toString()+")";
		}
		else //if(this.op==Operation.Times) 
			return "mul("+this.left.toString()+","+this.right.toString()+")";
	}
}
