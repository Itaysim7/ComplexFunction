package Ex1;

import java.util.ArrayList;



import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;
import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able
{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	private	ArrayList<Monom> poly ;
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() 
	{
		poly=new ArrayList<Monom>() ;
	}
	/**
	 * init a Polynom from a String consisting of a sequence of Monoms
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) 
	{
		s=s.replaceAll("\\s+","");
		poly=new ArrayList<Monom>() ;
		int i=0;
		int start=i;
		boolean sign=false;
		while(i<s.length()) 
		{
			if((s.charAt(i)=='-'||s.charAt(i)=='+')&&((i==0)||s.charAt(i-1)!='+'))//check if its negative 
			{
				sign=true;
				i++;
			}
			start=i;
			while(i<s.length()&&s.charAt(i)!='-'&&s.charAt(i)!='+') 
				i++;
			if(sign==true) 
			{
				start--;//if its negative enter the minus to the string
				this.add(new Monom(s.substring(start,i)));
				sign=false;
			}
			else {
				String sub=s.substring(start,i);
				Monom m=new Monom(sub);
				this.add(m);
			}
			
			if(i<s.length()&&s.charAt(i)!='-')
				i++;
		}
	}
	/**
	 * @return the value of this polynom in the given x
	 * @param int x
	 */
	public double f(double x) 
	{
		// The method return the result of x in the Polynom
		double count=0;
		for(int i=0;i<poly.size();i++) 
		{
			count=count+poly.get(i).f(x);
		}
		return count;
	}
	/**
	 * @return if this Polynom contain the power p1
	 * @param int p1
	 */
	private int containsPower(int p1)
	{
		/* The method return the place of the power in the array if it is already
		in the polynom,else return -1*/
		int count=0;
		for(int i=0;i<poly.size();i++) 
		{
			if(poly.get(i).get_power()==p1)
					return count;
			count++;
		}
		return -1;
	}
	/**
	 * Add Monom m1 to this Polynom
	 * @param Monom m1
	 */
	public void add(Monom m1)
	{
		// The method add the Monom to the Polynom
		int exist=this.containsPower(m1.get_power());
		if(exist==(-1))//if the Polynom isn't contain the power of m1
		{
			if(!m1.equals(ZERO)) 
			{
				poly.add(m1);
				//numOfMonoms++;
			}
		}
		else 
		{//add Monom m1 to the Monom that matching in the Polynom
			poly.get(exist).add(m1);
			if(poly.get(exist).equals(ZERO))//if the Monom is zero remove him from the array
			{
				poly.remove(exist);
			//	numOfMonoms--;
			}
		}
	}
	/**
	 * Add Polynom_able p1 to this Polynom
	 * @param Polynom_able p1
	 */
	public void add(Polynom_able p1)
	{
		// The method add the Monom to the Polynom
		
		Iterator<Monom> iter1=p1.iteretor();
		while(iter1.hasNext())	
		{
			this.add(iter1.next());
		}
	}
	/**
	 * Subtract Polynom_able p1 from this Polynom
	 * @param Polynom_able p1
	 */
	public void substract(Polynom_able p1)
	{	// The method subtract the p1 from the Polynom
		Polynom copy=new Polynom(p1.toString());
		copy.multiply(MINUS1);
		this.add(copy);
	}
	/**
	 * Multiply this Polynom by Monom m1
	 * @param Monom m1
	 */
	public void multiply(Monom m1)
	{
		// The method multiply the Polynom with m1
		
		if(m1.equals(ZERO)) //if Monoms is zero initialize the polynom
		{
			for(int i=0;i<poly.size();) 
			{
				poly.remove(i);
				//numOfMonoms--;
			}
		//	numOfMonoms=0;
		}
		else
		{
			for(int i=0;i<poly.size();i++)
			{
				Monom m=new Monom(poly.get(i));
				m.multipy(m1);
				if(m.equals(ZERO))//if the monom equals to 0 remove him from the array
				{
					poly.remove(i);
					i--;
				//	this.numOfMonoms--;
				}
				else
					poly.set(i, m);
			}
		}
	}
	/**
	 * Multiply this Polynom by Polynom_able p1
	 * @param Polynom_able p1
	 */
	public void multiply(Polynom_able p1) 
	{
	//  The method multiply polynom with polynom
		Polynom_able copy=new Polynom(p1.toString());;//incase p1 us this polynom.
		Polynom_able tempChange=new Polynom(this.toString());
		Polynom_able saver=new Polynom(this.toString());
		this.multiply(ZERO);
		Iterator<Monom> iter1=copy.iteretor();
		while(iter1.hasNext())	
		{
			tempChange.multiply(iter1.next());
			this.add(tempChange);
			tempChange=new Polynom(saver.toString());
		}
	}
	/**
	 * Test if this Polynom is logically equals to Polynom_able p1.
	 * @param Polynom_able p1
	 * @return true if this polynom represents the same function as Polynom_able p1
	 */
	public boolean equals(Object p)
	{
		// The method return true if the Polynoms are equals
		if(p instanceof Polynom_able) 
		{
			Polynom_able p1=(Polynom_able)p;
			Iterator<Monom> iter1=p1.iteretor();
			int count=0;
			while(iter1.hasNext())	//calculate the number of the Monoms
			{
				iter1.next();
				count++;
			}
			if(count!=poly.size()) //if the number different return false
				return false;
			iter1=p1.iteretor();
			while(iter1.hasNext())	
			{
				Monom m=new Monom(iter1.next());
				int power=this.containsPower(m.get_power());
				if(power!=-1)
				{
					//if the power is the same check if the Monoms are equals
					if(!m.equals(poly.get(power)))
						return false;
				}
				else
					return false;
			}
			return true;
		}
		else
			if(p instanceof Monom) 
			{
				if(p.toString().equals(this.toString()))
					return true;
				return false;
			}
			else
				if(p instanceof ComplexFunction)
				{
					ComplexFunction cf=(ComplexFunction)p;
					return (cf.equals(this));
				}
			return false; 
	}
	/**
	 * Test if this is the Zero Polynom
	 * @return if the Polynom is zero
	 */
	public boolean isZero()
	{
		if(poly.size()==0)
			return true;
		return false;
	}
	/**
	 * @return the root of this Polynom between x0 and x1.
	 * @param double x0, double x1, double eps
	 */
	public double root(double x0, double x1, double eps) 
	{
		if(x0<=x1)
		{
			if (Math.abs(this.f(x0))<=eps)
				return x0;
			else if(Math.abs(this.f(x1))<=eps)
				return x1;
			double middleX=(x1+x0)/2;
			double middleY=f((x1+x0)/2);
			if(Math.abs(middleY)<=eps)
				return middleX;
			if(this.f(x0)>0&&this.f(x1)<0) 
			{
				if(middleY>0)
					return this.root(middleX, x1, eps);
				else
					return this.root(x0, middleX, eps);
			}			
			else if(this.f(x0)<0&&this.f(x1)>0) 
			{
				if(middleY>0)
					return this.root(x0, middleX, eps);
				else
					return this.root(middleX, x1, eps);
			}		
			else
				throw new RuntimeException("ERR f(x0)*f(x1)>0 ");
		}
		else
			throw new RuntimeException("ERR x0>x1 ");
	}
	/**
	 * create a deep copy of this Polynom
	 * @return the copy
	 **/
	public function copy()
	{
		function temp=new Polynom(this.toString());
		return temp;
	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return the derivative
	 */
	public Polynom_able derivative() 
	{
		// The method return the derivative of the polynom
		Polynom_able der=new Polynom();
		for(int i=0;i<poly.size();i++) 
		{
			Monom m1=new Monom(this.poly.get(i).derivative());
			der.add(m1);
		}
		return der;
	}
	/**
	 * @return the area of this Polynom between x0 and x1
	 * @param double x0, double x1, double eps
	 */
	public double area(double x0, double x1, double eps)
	{
		// The method calculate the area of the Polynom approximately of eps
		double sum=0;
		if(x0>=x1)
		{
			return 0;
		}
		while(x0<x1) 
		{
			if(f(x0)>0) 
			{
				sum=sum+eps*f(x0);
			}
			x0=x0+eps;
		}
		return sum;
	}
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	public Iterator<Monom> iteretor()
	{
		// Return an Iterator (of Monoms) over this Polynom
		Iterator<Monom> iter1=poly.iterator();
		return iter1;
	}
	/**
	 * @return a string that represent this Polynom 
	 */
	public String toString()
	{
		//The method print the Polynom
		Collections.sort(poly,new Monom_Comperator());//sort the Polynom
		if(poly.size()==0)
			return "0";
		String p=""+poly.get(0);
		for(int i=1;i<poly.size();i++)
		{ 
				p=p+poly.get(i);
		}
		return p;
	}
	/**
	 * @return a function type of polynom that Initialized from string
	 * @param String s
	 */
	public function initFromString(String s) 
	{
		function p=new Polynom(s);
		return p;
	}
}