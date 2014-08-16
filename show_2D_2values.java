/*
reads a file, saves an image

blue-white-red scale for visualisation of 2D fields
fields must have at least 2 values, e.g. displacement U_x and displacement U_z, both are shown side by side

input:
1st line (dimensions): nx nz
nx*nz lines: U_x U_z ...(any other values)
*/

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.DataInputStream;

import javax.imageio.ImageIO; 

public class WavesBuffered {
	public static int color(int red,int green,int blue) {
		int rgb = red;
		rgb = (rgb << 8) + green;
		rgb = (rgb << 8) + blue;
		return rgb;
	}
	
	public static int get_color(double v, double mx) {
		//Blue - Red scale
		int val = (int)(255*Math.sqrt(Math.abs(v/mx)));
		if (val<256) {
			if (v<0)
				return color(255,255-val,255-val);
			return color(255-val,255-val,255);
		}
		//return GREEN when something went wrong
		return color(0,255,0);
	}
public static void main(String args[]) throws Exception {
	int nx=0,nz=0;

	double u[][]=new double[1000][1000];
	double w[][]=new double[1000][1000];
	
	double mx=0.00001;

	File f = new File("../pathtofile.txt");
	if (!f.isFile()) {
		System.out.println("file does not exist");
		return;
	}
	
	String s;
	String[] slovo;
	try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            s = br.readLine();
	    slovo = s.split(" ");
	    nx = new Integer(slovo[0]);
	    nz = new Integer(slovo[1]);
		
	    System.out.println(nx+" "+nz);
		
	    for (int i=0;i<nx;i++) {
	    for (int j=0;j<nz;j++) {	
		s = br.readLine();
		slovo = s.split(" ");
	
		u[i][j] = Double.parseDouble(slovo[0]);
		w[i][j] = Double.parseDouble(slovo[1]);

		if (Math.abs(u[i][j])>mx) mx=Math.abs(u[i][j]);
		if (Math.abs(w[i][j])>mx) mx=Math.abs(w[i][j]);	
		}
	    }
		
            br.close();		
        } catch (IOException e) { System.out.println("Citanie sa nepodarilo."); }
	catch (Exception e) { System.out.println("chyba vstupu"); }
	

	mx*=1.0001;

	System.out.println("drawing image, size: "+nx+" x "+nz+" max value: "+(int)(mx));
	
	BufferedImage img = new BufferedImage(2*nx+1, nz, BufferedImage.TYPE_INT_RGB );
	int rgb;
	for ( int j = 0; j < nz; j++ ) {
	  for ( int i = 0; i < nx; i++ ) {
		rgb = get_color(u[i][j],mx);
		img.setRGB(i, j, rgb);
	
		rgb = get_color(w[i][j],mx);
		img.setRGB(i+nx+1, j, rgb);
	  }
	}


	try {
		ImageIO.write(img, "PNG", new File("../pathtopic.PNG"));
	}
	catch (IOException ie) { ie.printStackTrace();}
    }
}
