package Plot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import Samples.Sample;

import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;
import org.math.plot.components.LegendPanel;
import org.math.plot.plots.GridPlot3D;

import Logic.Classifier;
import Logic.DecisionProcedure;

//TODO add legend and change axis labels
public class PlotDrawer {

	public PlotDrawer(List<Sample> samples, double[] params1, double[] params2, double[] errors, boolean classifier_flag) {
		
	    double[] class1_R;
	    double[] class1_G;
	    double[] class1_B;
	    
	    double[] class2_R;
	    double[] class2_G;
	    double[] class2_B;
	    
	    double[] class3_R;
	    double[] class3_G;
	    double[] class3_B;
	    
	    class1_R = new double[255];
		class1_G = new double[255];
		class1_B = new double[255];
    
        class2_R = new double[255];
        class2_G = new double[255];
        class2_B = new double[255];
    
        class3_R = new double[255];
        class3_G = new double[255];
        class3_B = new double[255];
	    
	    int helper = 0;
	    
	    for(Sample sample:samples) {
	    	if (sample.getClass_helper().equals("Class1")) {
	    		class1_R[helper] = sample.getRed();
	    		class1_G[helper] = sample.getGreen();
	    		class1_B[helper] = sample.getBlue();
	    	} else if (sample.getClass_helper().equals("Class2")) {
	    		class2_R[helper] = sample.getRed();
	    		class2_G[helper] = sample.getGreen();
	    		class2_B[helper] = sample.getBlue();
	    	}
	    	else if (sample.getClass_helper().equals("Class3")) {
	    		class3_R[helper] = sample.getRed();
	    		class3_G[helper] = sample.getGreen();
	    		class3_B[helper] = sample.getBlue();
	    	}
	    	helper ++;
	    }
	    
	    // create your PlotPanel (you can use it as a JPanel)
	    Plot3DPanel plot = new Plot3DPanel();
	    
	    // add a line plot to the PlotPanel
	    plot.addScatterPlot("Class 1", Color.BLUE, class1_R, class1_G, class1_B);
	    plot.addScatterPlot("Class 2", Color.RED, class2_R, class2_G, class2_B);
	    plot.addScatterPlot("Class 3", Color.GREEN, class3_R, class3_G, class3_B);
	    
	    double[] x_plane_test;
	    double[] y_plane_test;
	    
	    x_plane_test = new double[52];
	    y_plane_test = new double[52];
	    int k = 0;
	    
	    for(int i = 0; i < x_plane_test.length; i++) {
	    	x_plane_test[i] = k;
	    	y_plane_test[i] = k;
	    	k+=5;
	    }
	    
	    double[][] z_points_class1 = getZpoints(params1, x_plane_test, y_plane_test);
	    double[][] z_points_rest = getZpoints(params2, x_plane_test, y_plane_test);
	    
	    Classifier classifier = new Classifier();
	    
	    double[] fin_params = classifier.getClassifier(params1, params2);
	    
	    double[][] z_points_classifier = getZpoints(fin_params, x_plane_test, y_plane_test);
	    
	    if(classifier_flag) {
	    	plot.addGridPlot("test", x_plane_test, y_plane_test, z_points_classifier);
	    }
	    else {
	    	plot.addGridPlot("Class function", x_plane_test, y_plane_test, z_points_class1);
		    plot.addGridPlot("rest function", x_plane_test, y_plane_test, z_points_rest);
	    }
	    
	    //TODO remove it
	    //plot.addCloudPlot("test", testPlot, 1, 2, 3);
	    
	    plot.addLegend("TEST");
	    
	    plot.setFixedBounds(0, 0, 255);
	    plot.setFixedBounds(1, 0, 255);
	    plot.setFixedBounds(2, 0, 255);
	    
	    
	    
	    // put the PlotPanel in a JFrame like a JPanel
	    JFrame frame = new JFrame("a plot panel");
	    frame.setSize(600, 600);
	    frame.setContentPane(plot);
	    
	    DecisionProcedure proc = new DecisionProcedure();
	    
	    if (!classifier_flag) {
	    	JOptionPane.showMessageDialog(frame," Total Errors for Class1 and Class2 \n" + errors[0] + ", \n" + errors[1]);
	    }
	    frame.setVisible(true);
	}
	
	public double[][] getZpoints(double[] params, double[] x, double[] y) {
		
		//start with 2 dimensions
		double[] x_post = x;
		double[] y_post = y;
		double[][] z_post;
		
		z_post = new double[255][255];
		
		for( int i=0; i < x_post.length; ++i) {
			for(int j=0; j<x_post.length; ++j) {
				z_post[i][j] = params[0]*x_post[j] + params[1]*y_post[i] +params[2];
			}
		}
		
		return z_post;
		
	}
	
	public double[] getClassifier(double[] params1, double[] params2) {
		double[] final_params;
		
		final_params = new double[3];
		
		for(int i=0; i<3; i++) {
			final_params[i] = (params1[i] - params2[i])/2;
		}
		
		return final_params;
	}
	
}
