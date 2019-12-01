package Ex1;

public class ComplexFunction implements complex_function
{
	private function right;
	private function left;
	private Operation op;
	public ComplexFunction( Operation op2,function left2, function right2) 
	{
		this.left=left2;
		this.right=right2;
		this.op=op2;
	}
	public ComplexFunction( String op2,function left2, function right2) 
	{
		ComplexFunction temp=null;;
		switch(op2)
		{
			case "plus":
			{
				temp= new ComplexFunction(Operation.Plus,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
			}
			case "mul":
			{
				temp= new ComplexFunction(Operation.Times,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
			}
			case "div":
			{
				temp= new ComplexFunction(Operation.Divid,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
			}
			case "max":
			{
				temp= new ComplexFunction(Operation.Max,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
			}
			case "min": 
			{
				temp= new ComplexFunction(Operation.Min,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
			}
			case "comp":
			{
				temp= new ComplexFunction(Operation.Comp,left2,right2);
				this.op=temp.getOp();
				this.left=temp.left();
				this.right=temp.right();
			}
			case "none":
			{
				if(right2==null) 
				{
					temp= new ComplexFunction(Operation.Max,left2,null);
					this.op=temp.getOp();
					this.left=temp.left();
					this.right=temp.right();
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

	@Override
	public function initFromString(String s) 
	{
		function f=new ComplexFunction(s);
		return f;
	}
	private ComplexFunction helpComplexFunction(String s) 
	{
		char c=s.charAt(0);
		System.out.println(c);
		if(c!='m'&&c!='c'&&c!='d'&&c!='n'&&c!='p')//if its not a complex function
		{
			System.out.println(s);
			function p=new Polynom(s);
			ComplexFunction complex=new ComplexFunction(Operation.None,p,null);
			return complex;
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
							System.out.println(split);
							System.out.println(s.substring(4,split));
							System.out.println(s.substring(split+1,s.length()-1));
							ComplexFunction complex=new ComplexFunction(Operation.Times,helpComplexFunction(s.substring(4,split)),helpComplexFunction(s.substring(split+1,s.length()-1)));
							return complex;
						}
						else
							throw new RuntimeException("ERR the string is not a ComplexFunction");
					}
					case "min":
					{
						if(s.charAt(3)=='('&&s.charAt(s.length()-1)==')')
						{
							int split=this.findComma(s);	
							System.out.println(split);
							System.out.println(s.substring(4,split));
							System.out.println(s.substring(split+1,s.length()-1));
							ComplexFunction complex=new ComplexFunction(Operation.Min,helpComplexFunction(s.substring(4,split)),helpComplexFunction(s.substring(split+1,s.length()-1)));
							return complex;
						}
						else
							throw new RuntimeException("ERR the string is not a ComplexFunction");
					}
					case "max":
					{
						if(s.charAt(3)=='('&&s.charAt(s.length()-1)==')')
						{
							int split=this.findComma(s);	
							System.out.println(split);
							System.out.println(s.substring(4,split));
							System.out.println(s.substring(split+1,s.length()-1));
							ComplexFunction complex=new ComplexFunction(Operation.Max,helpComplexFunction(s.substring(4,split)),helpComplexFunction(s.substring(split+1,s.length()-1)));
							return complex;
						}
						else
							throw new RuntimeException("ERR the string is not a ComplexFunction");
					}
					case "div":
					{
						if(s.charAt(3)=='('&&s.charAt(s.length()-1)==')')
						{
							int split=this.findComma(s);	
							System.out.println(split);
							System.out.println(s.substring(4,split));
							System.out.println(s.substring(split+1,s.length()-1));
							ComplexFunction complex=new ComplexFunction(Operation.Divid,helpComplexFunction(s.substring(4,split)),helpComplexFunction(s.substring(split+1,s.length()-1)));
							return complex;
						}
						else
							throw new RuntimeException("ERR the string is not a ComplexFunction");
					}
					case "com":
					{
						if(s.charAt(3)=='p'&&s.charAt(4)=='('&&s.charAt(s.length()-1)==')')
						{
							int split=this.findComma(s);	
							System.out.println(split);
							System.out.println(s.substring(5,split));
							System.out.println(s.substring(split+1,s.length()-1));
							ComplexFunction complex=new ComplexFunction(Operation.Comp,helpComplexFunction(s.substring(5,split)),helpComplexFunction(s.substring(split+1,s.length()-1)));
							return complex;
						}
						else
							throw new RuntimeException("ERR the string is not a ComplexFunction");
					}
					case "plu":
					{
						if(s.charAt(3)=='s'&&s.charAt(4)=='('&&s.charAt(s.length()-1)==')')
						{
							int split=this.findComma(s);	
							System.out.println(split);
							System.out.println(s.substring(5,split));
							System.out.println(s.substring(split+1,s.length()-1));
							ComplexFunction complex=new ComplexFunction(Operation.Plus,helpComplexFunction(s.substring(5,split)),helpComplexFunction(s.substring(split+1,s.length()-1)));
							return complex;
						}
						else
							throw new RuntimeException("ERR the string is not a ComplexFunction");
					}
					case "none":
					{
						if(s.charAt(3)=='e'&&s.charAt(4)=='('&&s.charAt(s.length()-1)==')')
						{
							int split=this.findComma(s);	
							System.out.println(split);
							System.out.println(s.substring(5,split));
							System.out.println(s.substring(split+1,s.length()-1));
							if(s.substring(split+1,s.length()-1).isEmpty()) 
							{
								ComplexFunction complex=new ComplexFunction(Operation.None,helpComplexFunction(s.substring(5,split)),null);
								return complex;
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
	//find the "split" place in the string for left and right 
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
			if(comma==open&&comma!=0)
				return i;
		}
		return -1;
	}
	//check if the structure of parenthesis and comma in the string is legal 
	private boolean parenthesisTest(String s) 
	{
		int open=0;
		int close=0;
		int comma=0;
		System.out.println(s.charAt(5));
		for(int i=5;i<s.length();i++)
		{
			if(s.charAt(i)=='(')
				open++;
			if(s.charAt(i)==')')
				close++;
			if(s.charAt(i)==',')
				comma++;
			if(comma>open||close>comma)
				return false;
		}
		if(open==close&&open==comma)
			return true;
		return false;		
	}
	
	public ComplexFunction(String s) 
	{
		if(s.length()>=5&&s.substring(0, 5).equals("f(x)=")&&this.parenthesisTest(s))
		{
			System.out.println(s.substring(0, 5));
			System.out.println(s.substring(5, s.length()));
			ComplexFunction com=helpComplexFunction(s.substring(5,s.length()));
			this.op=com.getOp();
			this.left=com.left;
			this.right=com.right;
		}
		else
			throw new RuntimeException("ERR the string is not a ComplexFunction");
	}
	public double f(double x) 
	{
		double sum=0;
		switch(this.op)
		{
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
				if(this.right.f(x)!=0)
				{
					sum=this.left.f(x)/this.right.f(x);
					return sum;
				}
				else
					throw new RuntimeException("ERR denominator was set to 0");
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


	public function copy() 
	{
		function temp=new ComplexFunction(this.toString());
		return temp;
	}
	public void plus(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		this.left=temp;
		this.right=f1;
		op=Operation.Plus;
	}
	public void mul(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		this.left=temp;
		this.right=f1;
		op=Operation.Times;
	}
	public void div(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		this.left=temp;
		this.right=f1;
		op=Operation.Divid;
	}
	public void max(function f1) 
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		this.left=temp;
		this.right=f1;
		op=Operation.Max;
	}
	public void min(function f1)
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		this.left=temp;
		this.right=f1;
		op=Operation.Min;
	}
	public void comp(function f1)
	{
		ComplexFunction temp=new ComplexFunction(this.op,this.left,this.right);
		this.left=temp;
		this.right=f1;
		op=Operation.Comp;
	}
	public function left() 
	{
		return this.left;
	}
	public function right() 
	{
		if(this.right!=null)
		{
			return this.right;
		}
		else 
			return null;
	}
	public Operation getOp() 
	{
		return this.op;
	}

}
