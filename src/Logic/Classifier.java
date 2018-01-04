package Logic;

import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import Samples.Sample;

//this class is used to define the parameters of the plane that separates samples from class1 and class2 (rest of samples)
public class Classifier {

	public double[] getClassifier (double[] param1, double[] param2) {
		
		//find two points that are located on line that belongs to both planes
		double[] vector1 = {-param1[2]+1, -param2[2]+1};
		double[] vector2 = {-param1[2]+2, -param2[2]+2};
		
		double[][]matrix = {{param1[0], param1[1]}, {param2[0], param2[1]}};
		
		RealMatrix coefficients1 = new Array2DRowRealMatrix(matrix, false);
		DecompositionSolver solver1 = new LUDecomposition(coefficients1).getSolver();
		RealVector constants1 = new ArrayRealVector(vector1, false);
		RealVector solution1 = solver1.solve(constants1);
		
		double[] output1 = solution1.toArray();
		
		RealMatrix coefficients2 = new Array2DRowRealMatrix(matrix, false);
		DecompositionSolver solver2 = new LUDecomposition(coefficients2).getSolver();
		RealVector constants2 = new ArrayRealVector(vector2, false);
		RealVector solution2 = solver2.solve(constants2);
		
		double[] output2 = solution2.toArray();
		
		//find third point that is located between two planes and has the same distance to both of them

		double x_1 = 0;
		double y_1 = 0;
		double z_1 = 0;
		
		double x_2 = 0;
		double y_2 = 0;
		double z_2 = 0;
		
		double y_final = 0;
		double x_final = 0;
		
		//samples
		double x = 5;
		double y = 5;
		double z = 5;
		
		boolean check =false;
		
		//first case
		y_1 = param1[0]*x_1-z+param1[2];
		y_2 = param2[0]*x_1-z+param2[2];
		
		
			
			if(y_1!=y_2) {
				if(y_1>y_2) {
					y_final = y_2+((y_1-y_2)/2);
				}
				else {
					y_final = y_1+((y_2-y_1)/2);
				}
				
			}
			else if(x_1!=x_2) {
				if(x_1<x_2) {
					x_final = x_2+((x_1-x_2)/2);
				}
				else {
					x_final = x_1+((x_2-x_1)/2);
					
					check=true;
				}
				
			}
		
		System.out.println(x_final);
		System.out.println(y_final);
		
		
		double[][] matrix_final = {{output1[0], output1[1], 1}, {output2[0], output2[1], 1}, {x, y_final, 1}};
		
		if(check) {
			matrix_final[3][1] = x_final;
			matrix_final[3][2] = y;
		}
		
		
		double[] vector3 = {1, 2, z};
		
		RealMatrix coefficients3 = new Array2DRowRealMatrix(matrix_final, false);
		DecompositionSolver solver3 = new LUDecomposition(coefficients3).getSolver();
		RealVector constants3 = new ArrayRealVector(vector3, false);
		RealVector solution3 = solver3.solve(constants3);
		
		double[] output = solution3.toArray();
		
		return output;
	}
}
