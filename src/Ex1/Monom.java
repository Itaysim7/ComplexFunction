package Ex1;

import java.util.Comparator;
import java.util.Iterator;

import javax.management.RuntimeErrorException;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function
{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	private double _coefficient; 
	private int _power;
	
	/**
	 * @return the Comparator of this monom
	 */
	public static Comparator<Monom> getComp() 
	{
		return _Comp;
	}

	/**
	 * init a Monom given double a and int b
	 * a represent the coefficient
	 * b represent the power
	 * @param double a, int b
	 */
	public Monom(double a, int b)
	{
		if(a==0) 
		{
			this.set_coefficient(0);
			this.set_power(0);
		}
		else
		{
			this.set_coefficient(a);
			this.set_power(b);
		}
	}
	/**
	 * init a deep copy of the Monom ot
	 * @param Monom ot
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * @return the coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * @return the power
	 */
	public int get_power() {
		return this._power;
	}
	/** 
	 * @return the derivative Monom of this.
	 */
	public Monom derivative()
	{
		if(this.get_power()==0) 
		{
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * @return the value of this Monom in the given x
	 * @param int x
	 */
	public double f(double x)/*hatzaba*/
	{
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	/**
	 * 
	 * @return if this is the Zero Monom
	 */
	public boolean isZero() 
	{
		return this.get_coefficient() == 0;
	}
	// ***************** add your code below **********************
	
	/**
	 * init a Polynom from a String such as:
	 * {"x","2x","x^2","3x^3","3"} 
	 * @param s: is a string represents a Polynom
	 */
	public Monom(String s)
	{
		boolean conX=s.contains("x");
		if(!conX) /*if the String isn't contains x,check if its legal double*/
		{
			boolean isD=s.matches("[-]?[0-9]*\\.?[0-9]*")&&!s.equals("-")&&!s.equals("")&&!s.equals(".");
			if(!isD) 
				throw new RuntimeException("ERR the string isnt legal ");
			else
			{ /*"Monom" of shape a*x^0(without x)*/
					double c=Double.parseDouble(s);
					this.set_coefficient(c);
					this.set_power(0);
			}
		}
		else
		{ /*the string contains x*/
			int xplace=s.indexOf('x');
			String str1=s.substring(0, xplace);
			boolean isD=str1.matches("[-]?[0-9]*\\.?[0-9]*");/*check if the coefficient is legal*/
			boolean isI=true;
			String str2="1";
			if(xplace+1!=s.length()) /*if x is not the last char at the string*/
			{
				str2=s.substring(xplace+2,s.length());
				isI=str2.matches("\\d+"); /*check if the power is legal*/
			}
			if((isD)&&(isI)&&((xplace+1==s.length()||s.charAt(xplace+1)=='^')))/*check if the string is from the shape ax^b*/
			{
				int p=Integer.parseInt(str2);
				if(str1.equals("-")||str1.equals("")) //"Monom" of shape  -/+x^b
				{
					if(str1.equals("-")) //"Monom" of shape -x^b
					{
						this.set_coefficient(-1);
						this.set_power(p);
					}
					else // "Monom" of shape x^b
					{
						this.set_coefficient(1);
						this.set_power(p);
					}
				}
				else
				{
					double c=Double.parseDouble(str1);
					if(c==0)/* "Monom" of shape  0*/
					{
						this.set_coefficient(0);
						this.set_power(0);
					}
					else
					{ /*"Monom" of shape a*x^b*/
						this.set_coefficient(c);
						this.set_power(p);
					}
				}
			}
			else
				throw new RuntimeException("ERR the string isnt legal ");
		}
	}
	/**
	 * Add Monom m to this Monom
	 * @param Monom m
	 */
	public void add(Monom m)
	{
		if(m.get_coefficient()!=0)/*if m is not 0,else add 0*/
		{
			if(this._coefficient==0) /*if this Monom is 0 add m*/
			{
				this._coefficient=m.get_coefficient();
				this._power=m.get_power();
			}
			else
			{
				if(m.get_power()!=this._power&&m.get_coefficient()!=0&&this._coefficient!=0) /*if the power of the Monom is different throw Exception*/
				{
					throw new RuntimeException("ERR the power of the Monoms must be the same");
				}
				else /*if the coefficient is the same set Monom to zero*/
					if(m.get_coefficient()+this._coefficient==0) 
					{
						this._power=0;
						this._coefficient=0;
					}
					else /*calculate the new Monom */
						this._coefficient=this._coefficient+m.get_coefficient();
			}
		}
	}
	/**
	 * Multiply this Monom by Monom p
	 * @param Monom p
	 */
	public void multipy(Monom d) 
	{
		if(d.get_coefficient()==0||this._coefficient==0)/*if d is zero or this Monom is zero return 0*/
		{
			this._power=0;
			this._coefficient=0;
		}
		else /*calculate the new Monom */
		{
			this._coefficient=this._coefficient*d.get_coefficient();
			this._power=this._power+d.get_power();
		}
	}
	/**
	 * @return a string that represent this Monom
	 * 
	 */
	public String toString()
	{
		String ans="";
		if(this._coefficient==0)/*"Monom" of shape 0*/
		{
			return "0";
		}
		if(this._power==0)/*"Monom" of shape a*x^0*/
		{
			ans=""+this._coefficient;
			return ans;
		}
		if(this._power==1)/*"Monom" of shape a*x^0*/
		{
			ans=""+this._coefficient+"x";
			return ans;
		}
		/*"Monom" of shape ax^b*/
		ans=""+this._coefficient+"x^"+this._power;
		return ans;
	}
	/**
	 * Test if this Monom is logically equals to Monom m.
	 * @param Monom m
	 * @return true if this Monom  represents the same function as Monom m
	 */
	public boolean equals(Object m1)
	{
		if(m1 instanceof Monom) 
		{
			Monom m=(Monom)m1;
			if(this._coefficient==m.get_coefficient()&&this._coefficient==0)
				return true;
			else
			{
				double e=Math.abs(this._coefficient-m.get_coefficient());
				return (e<=EPSILON&&this._power==m.get_power());
			}
		}
		else
			if(m1 instanceof Polynom_able) 
			{
				if(m1.toString().equals(this.toString()))
					return true;
				return false;
			}
		else
			return false;
		
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	
	/**
	 * set the Corfficient as a
	 * @param double a
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * set the power as p
	 * @param int p
	 */
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	/**
	 * @return a new zero Monom
	 */
	private static Monom getNewZeroMonom() 
	{
		return new Monom(ZERO);
	}
@Override
	public function initFromString(String s)
	{
		function f=new Monom(s);
		return f;
	}
	/**
	 * create function from the Monom(deep copy)
	 * @return function
	 */
	public function copy() 
	{
		function f=new Monom(this.toString());
		return f;
	}
}

    
