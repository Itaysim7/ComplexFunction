package Ex1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolynomTest
{
	Polynom p0,p1,p2,p3,p4;
	Polynom [] pol;
	String[] monoms=new String[3];

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception 
	{
		pol=new Polynom[5];
		p0=new Polynom();
		p1=new Polynom();
		p2=new Polynom();
		p3=new Polynom();
		p4=new Polynom();
		monoms[0] = "1";monoms[1]="x";monoms[2]="x^2";
		for(int i=0;i<monoms.length;i++)
		{
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		monoms[0] = "-2.5x";monoms[1]="x";monoms[2]="x^5";
		for(int i=0;i<monoms.length;i++)
		{
			Monom m = new Monom(monoms[i]);
			p2.add(m);
		}
		monoms[0] = "-5x^3";monoms[1]="2";monoms[2]="7";
		for(int i=0;i<monoms.length;i++)
		{
			Monom m = new Monom(monoms[i]);
			p3.add(m);
		}
		monoms[0] = "1";monoms[1]="0";monoms[2]="0";
		for(int i=0;i<monoms.length;i++)
		{
			Monom m = new Monom(monoms[i]);
			p4.add(m);
		}
		pol[0]=p0;
		pol[1]=p1;
		pol[2]=p2;
		pol[3]=p3;
		pol[4]=p4;
	}

	@AfterEach
	public void tearDown() throws Exception 
	{
		for(int i=0;i<pol.length;i++) 
		{
			pol[i]=null;
		}
	}

	@Test
	public void testPolynomString()
	{
		Polynom[] actualArr= {new Polynom("0"),new Polynom("1x^2+1x+1"),new Polynom("1x^5-1.5x"),new Polynom("-5x^3+9"),new Polynom("1.0")};
		assertArrayEquals(pol,actualArr);
		assertThrows( RuntimeException.class,() -> new Polynom("x^2-+2x"));
		assertThrows( RuntimeException.class,() -> new Polynom("x^2.2-2x"));
		assertThrows( RuntimeException.class,() -> new Polynom("x^2+2xy"));
		assertThrows( RuntimeException.class,() -> new Polynom("x^2+-2x"));
	}

	@Test
	public void testF()
	{
		double[] expectedArr= {0,7,29,-31,1};
		double[] actualArr= new double[5];
		for(int i=0;i<pol.length;i++) 
		{
			actualArr[i]=pol[i].f(2);
			assertEquals("testing f:",expectedArr[i],actualArr[i],0.00001);
		}
	}
	@Test
	public void testAddMonom() 
	{
		Polynom[] expectedArr= {new Polynom("x"),new Polynom("1.0x^2+2.0x+1.0"),new Polynom("1.0x^5-0.5x"),new Polynom("-5.0x^3+9.0+x"),new Polynom("x+1")};
		Monom m=new Monom("x");
		for(int i=0;i<pol.length;i++) 
		{
			pol[i].add(m);
		}
		assertArrayEquals(expectedArr,pol);
	}

	@Test
	 public void testAddPolynom_able() 
	{
		Polynom[] expectedArr= {new Polynom("1.0x^2+1.0x+1.0"),new Polynom("1.0x^2+1.0x+1.0"),new Polynom("2.0x^5-3x"),new Polynom("-5.0x^3+2.0x^5-3x+9.0"),new Polynom("1.0x^2+1.0x+2.0")};
		pol[1].add(pol[0]);
		pol[0].add(pol[1]);
		pol[2].add(pol[2]);
		pol[3].add(pol[2]);
		pol[4].add(pol[1]);
		assertArrayEquals(expectedArr,pol);
	}

	@Test
	public void testSubstract() 
	{
		Polynom[] expectedArr= {new Polynom("-1.0x^2-1.0x-1.0"),new Polynom("1.0x^2+1.0x+1.0"),new Polynom("0"),new Polynom("-5.0x^3+8.0-1.0x^2-1.0x"),new Polynom("1.0")};
		pol[1].substract(pol[0]);
		pol[0].substract(pol[1]);
		pol[2].substract(pol[2]);
		pol[3].substract(pol[1]);
		pol[4].substract(pol[2]);
		assertArrayEquals(expectedArr,pol);
	}

	@Test
	public void testMultiplyMonom()
	{
		Polynom[] expectedArr= {new Polynom("0"),new Polynom("1.0x^3+1.0x^2+1.0x"),new Polynom("1.0x^6-1.5x^2"),new Polynom("-5.0x^4+9.0x"),new Polynom("x")};
		Monom m=new Monom("x");
		for(int i=0;i<pol.length;i++) 
		{
			pol[i].multiply(m);
		}
		assertArrayEquals(expectedArr,pol);
	}

	@Test
	public void testMultiplyPolynom_able() 
	{
		Polynom[] expectedArr= {new Polynom("0"),new Polynom("0"),new Polynom("1.0x^5-1.5x"),new Polynom("25x^6-90x^3+81"),new Polynom("1.0x^5-1.5x")};
		pol[0].multiply(pol[1]);
		pol[1].multiply(pol[0]);
		pol[2].multiply(pol[4]);
		pol[3].multiply(pol[3]);
		pol[4].multiply(pol[2]);
		assertArrayEquals(expectedArr,pol);
	}
	@Test
	public void testEqualsPolynom_able()
	{
		Polynom p1=new Polynom("3x^2+5x");
		Polynom p2=new Polynom("3x^2+5x");
		Polynom p3=new Polynom("3x^2+5");
		Polynom p4=new Polynom("3x^2");
		Object m=new Monom("3x^2");
		assertTrue(p4.equals(m));
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
	}
	@Test
	public void testIsZero() 
	{

		boolean[] expectedArr= {true,false,false,false,false};
		boolean[] actualArr= new boolean[5];
		for(int i=0;i<pol.length;i++) 
		{
			actualArr[i]=pol[i].isZero();
		}
		assertArrayEquals(expectedArr,actualArr);

	}

	@Test
	public void testRoot() 
	{
		Polynom p1=new Polynom("x^2");
		assertEquals(0,p1.root(-1, 1, 0.0000001),0.0000001);
		Polynom p2=new Polynom("x^2-1");
		assertEquals(-1,p2.root(-1, 1, 0.0000001),0.0000001);
		assertThrows( RuntimeException.class,() -> p2.root(1, -1, 0.0000001));
		assertThrows( RuntimeException.class,() -> p2.root(2, 3, 0.0000001));
	}

	@Test
	public void testCopy()
	{
		Polynom_able [] actualArr=new Polynom_able[5];
		
		for(int i=0;i<pol.length;i++) 
		{
			actualArr[i]=pol[i].copy();
		}
		assertArrayEquals(pol,actualArr);
		
	}

	@Test
	public void testDerivative() 
	{
		Polynom[] expectedArr= {new Polynom("0"),new Polynom("2x+1"),new Polynom("5x^4-1.5"),new Polynom("-15x^2"),new Polynom("0")};
		Polynom_able[] actualArr=new Polynom_able[5];
		for(int i=0;i<pol.length;i++) 
		{
			actualArr[i]=pol[i].derivative();
		}
		assertArrayEquals(expectedArr,actualArr);
	}

	@Test
	public void testArea() 
	{
		double[] expectedArr= {0,2.66,0.58,18.05,2};
		double[] actualArr= new double[5];
		for(int i=0;i<pol.length;i++) 
		{
			actualArr[i]=pol[i].area(-1,1,0.01);
			System.out.println(actualArr[i]);
			assertEquals("testing f:",expectedArr[i],actualArr[i],0.01);
		}
	}

	@Test
	public void testToString() 
	{
		assertEquals("0",pol[0].toString());
		assertEquals("1.0x^2+1.0x+1.0",pol[1].toString());
		assertEquals("1.0x^5-1.5x",pol[2].toString());
		assertEquals("-5.0x^3+9.0",pol[3].toString());
		assertEquals("1.0",pol[4].toString());
	}

//	@Test
//	public void testInitFromString() {
//		fail("Not yet implemented");
//	}

}
