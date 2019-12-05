package Ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.io.PrintWriter;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;


public class Functions_GUI implements functions {
	private ArrayList <function> f;
	
	/**
	 * Init a new collection of functions from a file
	 * @param file - the file name
	 * @throws IOException if the file does not exists of unreadable (wrong format)
	 */
	public void initFromFile(String file) throws IOException{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			ArrayList <function> temp=new ArrayList();
			String lineReader;
			lineReader=br.readLine();
			while(lineReader!=null) 
			{
			temp.add(new ComplexFunction(lineReader));
			lineReader=br.readLine();
			}
			this.f=temp;
			br.close();
			fr.close();
	}
	/**
	 * 
	 * @param file - the file name
	 * @throws IOException if the file is not writable
	 */
	public void saveToFile(String file) throws IOException{
			FileWriter fw = new FileWriter(file);
			PrintWriter outs = new PrintWriter(fw);
			for(int i=0;i<f.size();i++) 
				outs.println(f.get(i).toString());
			outs.close(); 
			fw.close();
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
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

	}
	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: is the file id not readable or in wrong format should use default values. 
	 */
	public void drawFunctions(String json_file) {
//		JSONParser jp = new JSONParser();
//		FileReader fr = new FileReader(fileName);
//		Object obj = jp.parse(fr);
//		JSONObject jo = (JSONObject) obj; 
//		int width=Integer.parseInt(jo.get("Width"));
//		int height=Integer.parseInt(jo.get("Height"));
//		int resolution=Integer.parseInt(jo.get("Resolution"));
//		double [] rangeX=jo.get("Range_X");
//		Range rx=new Range(rangeX[0],rangeX[1]);
//		double [] rangeY=jo.get("Range_Y");
//		Range ry=new Range(rangeX[0],rangeX[1]);
//		drawFunctions(width,height,rx,ry,resolution);	
	}	
	public function get(int i) {
		return this.f.get(i);
	}
	public boolean add(function e) {
		return this.f.add(e);
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
