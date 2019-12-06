package Ex1;

public class Graph 
{
	public int Width;
	public int Height;
	public int Resolution;
	public double []Range_X;
	public double []Range_Y;

	public Graph(int width,int height,int resolution,double []Range_X,double []Range_Y)
	{
		Range_Y=new double[2];
		this.Range_X[0]=Range_X[0];
		this.Range_X[1]=Range_X[1];
		this.Range_Y[0]=Range_Y[0];
		this.Range_Y[1]=Range_Y[1];
		this.Height=height;
		this.Width=width;
		this.Resolution=resolution;
	}

}
