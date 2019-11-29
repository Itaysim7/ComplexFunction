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

	@Override
	public function initFromString(String s) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() 
	{
		function temp=new ComplexFunction(this.op,this.left,this.right);
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
