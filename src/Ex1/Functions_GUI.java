package Ex1;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.PrintWriter;

/**
 * This class represents a collection of mathematical functions  
 * which can be presented on a GUI window and can be saved (and load) to file. 
 *
 */
public class Functions_GUI implements functions 
{
	private ArrayList <function> f;
	
	public Functions_GUI() 
	{
		f=new ArrayList<function>() ;
	}
	/**
	 * Init a new collection of functions from a file
	 * @param file - the file name
	 * @throws IOException if the file does not exists of unreadable (wrong format)
	 */
	public void initFromFile(String file)
	{
			try 
			{ 
				FileReader fr = new FileReader(file); 
				BufferedReader br = new BufferedReader(fr);
			//	ArrayList <function> temp=new ArrayList<function>();
				String lineReader;
				lineReader=br.readLine();
				while(lineReader!=null) 
				{

						this.f.add(new ComplexFunction(lineReader));
						lineReader=br.readLine();
				}
			//	this.f=temp;
				br.close();
				fr.close();   
			}
			catch(Exception e)
			{  
				System.err.print("Error reading file\n" +e.getMessage());
			}
	}
	/**
	 * 
	 * @param file - the file name
	 * @throws IOException if the file is not writable
	 */
	public void saveToFile(String file) throws IOException
	{
		try 
		{
			FileWriter fw = new FileWriter(file);
			PrintWriter outs = new PrintWriter(fw);
			for(int i=0;i<f.size();i++) 
				outs.println(f.get(i).toString());
			outs.close(); 
			fw.close();	
		}
		catch(IOException e)
		{  
			System.err.print("Error file is not writable\n" +e.getMessage());
		}
			
	}
	/**
	 * Draws all the functions in the collection in a GUI window using the
	 * given parameters for the GUI windo and the range & resolution
	 * @param width - the width of the window - in pixels
	 * @param height - the height of the window - in pixels
	 * @param rx - the range of the horizontal axis
	 * @param ry - the range of the vertical axis
	 * @param resolution - the number of samples with in rx: the X_step = rx/resulution
	 */
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution)
	{
		Color[] colors= {Color.blue,Color.cyan,Color.darkGray,Color.gray,Color.green,Color.magenta,Color.orange,Color.pink};
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(),rx.get_max());		// rescale the coordinate system
		StdDraw.setYscale(ry.get_min(),ry.get_max());		// rescale the coordinate system
		double rangex=Math.abs(rx.get_max())+Math.abs(rx.get_min());
		double rangey=Math.abs(ry.get_max())+Math.abs(ry.get_min());
		if(rangex<31&&rangey<31)
		{
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			for (int j = (int) rx.get_min(); j <= rx.get_max(); j++)
			{		// vertical lines
				StdDraw.line(j, ry.get_min(), j, ry.get_max());
			}
			for (double i = ry.get_min(); i <= ry.get_max(); i=i+1)
			{		// horizontal lines
				StdDraw.line(rx.get_min(), i,rx.get_max(), i);
			}
		}	
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.01);
		StdDraw.line(rx.get_min(), 0,rx.get_max(),0);// y axis
		StdDraw.line(0, ry.get_min(),0, ry.get_max());	// x axis
		double[] x = new double[resolution+1];
		double[] y = new double[resolution+1];
		int color=0;
		for(int i=0;i<f.size();i++)
		{
			for (int j = 0; j <= resolution; j++) // sampled at resolution points in the y value of the function in the array  for rx range
			{
				  x[j] = rx.get_min()+j*rangex/resolution;
				  y[j] = this.f.get(i).f(x[j]);
			}
			StdDraw.setPenColor(colors[i]);
			StdDraw.setPenRadius(0.005);
			if(color==8)
				color=0;
			// plot the approximation to the function
			for (int j = 0; j < resolution; j++)
			{
				StdDraw.line(x[j], y[j], x[j+1], y[j+1]);
			}
			color++;		
		}
	}
	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: is the file id not readable or in wrong format should use default values. 
	 */
	public void drawFunctions(String json_file) 
	{
		
		Gson gson = new Gson();
		try
		{
			FileReader reader = new FileReader(json_file);
			Graph graph = gson.fromJson(reader,Graph.class);
			try
			{
				double totalx=Math.abs(graph.Range_X[0])+Math.abs(graph.Range_X[1]);
				double totaly=Math.abs(graph.Range_Y[0])+Math.abs(graph.Range_Y[1]);
				if(graph.Height>0&&graph.Width>0&&graph.Resolution>0&&totalx!=0&&totaly!=0)
				{
					Range rx=new Range(graph.Range_X[0],graph.Range_X[1]);
					Range ry=new Range(graph.Range_Y[0],graph.Range_Y[1]);
					this.drawFunctions(graph.Width, graph.Height, rx, ry, graph.Resolution);
				}
				else//default value
					this.drawFunctions(512, 512, new Range(-10,10), new Range(-10,10), 100);
				
			}
			catch(NullPointerException e)//default value
			{
				this.drawFunctions(512, 512, new Range(-10,10), new Range(-10,10), 100);
			}			
		} 
		catch ( FileNotFoundException e) //default value
		{
			this.drawFunctions(512, 512, new Range(-10,10), new Range(-10,10), 100);
		}
		catch ( JsonSyntaxException  e) //default value
		{
			this.drawFunctions(512, 512, new Range(-10,10), new Range(-10,10), 100);
		}
		
	}	
	public function get(int i) 
	{
		return this.f.get(i);
	}
	public boolean add(function e) 
	{
		f.add(e);
		return true;
	}

	public boolean addAll(Collection<? extends function> c) {
		return this.f.addAll(c);
	}
	public void clear() {
		this.f.clear();

	}
	public boolean contains(Object o) {
		return this.f.contains(o);
	}
	public boolean containsAll(Collection<?> c) {
		return this.f.containsAll(c);
	}
	public boolean isEmpty() {
		return this.f.isEmpty();
	}

	public Iterator<function> iterator() {
		return this.f.iterator();
	}

	public boolean remove(Object o) {
		return this.f.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return this.f.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return this.f.retainAll(c);
	}

	public int size() {
		return this.f.size();
	}

	public Object[] toArray() {
		return this.f.toArray();
	}
	public <T> T[] toArray(T[] a) {
		return this.f.toArray(a);
	}



}
