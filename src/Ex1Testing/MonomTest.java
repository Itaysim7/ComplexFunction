package Ex1Testing;

import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Ex1.Monom;
import Ex1.Polynom;

public class MonomTest
{

	Monom m0,m1,m2,m3,m4;
	Monom [] mon;

	@Before
	public void setUp() throws Exception 
	{
		mon=new Monom[5];
		m0=new Monom(0,0);
		m1=new Monom(1,1);
		m2=new Monom(3,0);
		m3=new Monom(-2.5,2);
		m4=new Monom(3,4);
		mon[0]=m0;
		mon[1]=m1;
		mon[2]=m2;
		mon[3]=m3;
		mon[4]=m4;
		
	}
	@After
	public void tearDown() throws Exception 
	{
		for(int i=0;i<mon.length;i++) 
		{
			mon[i]=null;
		}
	}
	@Test
	public void testMonomMonom()
	{
		for(int i=0;i<mon.length;i++) 
		{
			Monom actual=new Monom(mon[i]);
			assertEquals(mon[i],actual);
		}
	}

	@Test
	public void testGet_coefficient() 
	{
		double[] expectedArr= {0,1,3,-2.5,3};
		double[] actualArr= new double[5];
		for(int i=0;i<mon.length;i++) 
		{
			actualArr[i]=mon[i].get_coefficient();
			assertEquals("testing get coefficent:",expectedArr[i],actualArr[i],0.00001);
		}
	}
	@Test
	public void testGet_power() 
	{
		int[] expectedArr= {0,1,0,2,4};
		int[] actualArr= new int[5];
		for(int i=0;i<mon.length;i++) 
		{
			actualArr[i]=mon[i].get_power();
			assertEquals("testing f:",expectedArr[i],actualArr[i],0.00001);
		}
	}
	@Test
	public void testDerivative()
	{
		Monom[] expectedArr= {new Monom("0"),new Monom("1"),new Monom("0"),new Monom("-5x"),new Monom("12x^3")};
		Monom[] actualArr=new Monom[5];
		for(int i=0;i<mon.length;i++) 
		{
			actualArr[i]=new Monom(mon[i].derivative());
		}
		assertArrayEquals(expectedArr,actualArr);
	}

	@Test
	public void testF() 
	{
		double[] expectedArr= {0,2,3,-10,48};
		double[] actualArr= new double[5];
		for(int i=0;i<mon.length;i++) 
		{
			actualArr[i]=mon[i].f(2);
			assertEquals("testing f:",expectedArr[i],actualArr[i],0.00001);
		}
	}
	@Test
	public void testIsZero() 
	{
		boolean[] expectedArr= {true,false,false,false,false};
		boolean[] actualArr= new boolean[5];
		for(int i=0;i<mon.length;i++) 
		{
			actualArr[i]=mon[i].isZero();
		}
		assertArrayEquals(expectedArr,actualArr);
	}
	@Test
	public void testMonomString()
	{
		Monom[] actualArr= {new Monom("0"),new Monom("x"),new Monom("3"),new Monom("-2.5x^2"),new Monom("3x^4")};
		assertArrayEquals(mon,actualArr);
		assertThrows( RuntimeException.class,() -> new Monom("jdk"));
		assertThrows( RuntimeException.class,() -> new Monom("3x^"));
		assertThrows( RuntimeException.class,() -> new Monom("3xf"));
		assertThrows( RuntimeException.class,() -> new Monom("3x^-2"));
	}

	@Test
	public void testAdd()
	{
		Monom[] expectedArr= {new Monom("x"),new Monom("2x"),new Monom("6"),new Monom("-5x^2"),new Monom("6x^4")};
		mon[0].add(mon[1]);
		mon[1].add(mon[1]);
		mon[2].add(mon[2]);
		mon[3].add(mon[3]);
		mon[4].add(mon[4]);
		assertArrayEquals(expectedArr,mon);
		assertThrows( RuntimeException.class,() -> mon[1].add(m2));
	}

	@Test
	public void testMultipy() 
	{
		Monom[] expectedArr= {new Monom("0"),new Monom("x^2"),new Monom("9"),new Monom("0"),new Monom("3x^6")};
		mon[0].multipy(mon[1]);
		mon[1].multipy(mon[1]);
		mon[2].multipy(mon[2]);
		mon[3].multipy(mon[0]);
		mon[4].multipy(mon[1]);
		assertArrayEquals(expectedArr,mon);
	}
	@Test
	public void testToString() 
	{
		assertEquals("0",mon[0].toString());
		assertEquals("+1.0x",mon[1].toString());
		assertEquals("+3.0",mon[2].toString());
		assertEquals("-2.5x^2",mon[3].toString());
		assertEquals("+3.0x^4",mon[4].toString());
	}
	@Test
	public void testEqualsMonom()
	{
		Monom m1=new Monom("3x^2");
		Monom m2=new Monom("3x^2");
		Monom m3=new Monom("3");
		Object p=new Polynom("3");
		assertTrue(m3.equals(p));
		assertTrue(m1.equals(m2));
		assertFalse(m1.equals(m3));
	}

//	@Test
//	public void testInitFromString()
//	{
//		fail("Not yet implemented");
//	}

//	@Test
////	public void testCopy()
////	{
////		fail("Not yet implemented");
////	}

}
