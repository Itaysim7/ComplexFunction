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
	
	public ComplexFunction(function f) 
	{
		this.left=f;
		this.right=null;
		this.op=Operation.None;
	}
	
	public ComplexFunction(String s) 
	{
		if(this.parenthesisTest(s))
		{
			s=s.replaceAll("\\s+","");
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
	public function initFromString(String s) 
	{
		function f=new ComplexFunction(s);
		return f;
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
		for(int i=0;i<s.length();i++)
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
	public boolean equals(Object o)
	{
		if(o instanceof Polynom_able ) 
		{
			Polynom p=(Polynom)o;
			double EPSILON = 0.01;
			for(double x=-5;x<5;x+=EPSILON) 
			{
				if(Math.abs(this.f(x)-p.f(x))>0.0001)
					return false;
			}
			for(int i=0;i<=5;i++) 
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
			for(double x=-5;x<5;x+=EPSILON) 
			{
				if(Math.abs(this.f(x)-m.f(x))>0.0001)
					return false;
			}
			for(int i=0;i<=5;i++) 
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

			for(double x=-5;x<5;x+=EPSILON) 
			{
				if(Math.abs(this.f(x)-cf.f(x))>0.0001)
					return false;
			}
			for(int i=0;i<=5;i++) 
			{
				double x=Math.random()*20;
				System.out.println("****"+this.f(x));
				System.out.println("*****"+cf.f(x));
				if(Math.abs(this.f(x)-cf.f(x))>0.0001)
					return false;
			}
			return true;
		}
		else
			return false;
	}
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
