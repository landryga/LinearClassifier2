package Logic;

import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import Samples.Sample;

public class DecisionProcedure {
	
	public double[] getParameters(List<Sample> samples) {
		// plane function is in z = Ax + By + C form
		int A;
		int B;
		int C;
		
		int x_A = 0;
		int y_A = 0;
		int z_A = 0;
		int d_A = 0;
		
		int x_B = 0;
		int y_B = 0;
		int z_B = 0;
		int d_B = 0;
		
		int x_C = 0;
		int y_C = 0;
		int z_C = 0;
		int d_C = 0;
		
		for(Sample sample: samples) {
			//A derivative
			x_A += 2*sample.getRed()*sample.getRed();
			y_A += 2*sample.getRed()*sample.getGreen();
			z_A += 2*sample.getRed()*sample.getBlue();
			d_A += 2*sample.getRed();
			
			//B derivative
			x_B += 2*sample.getGreen()*sample.getRed();
			y_B += 2*sample.getGreen()*sample.getGreen();
			z_B += 2*sample.getGreen()*sample.getBlue();
			d_B += 2*sample.getGreen();
			
			//C derivative
			x_C += 2*sample.getRed();
			y_C += 2*sample.getGreen();
			z_C += 2*sample.getBlue();
			d_C += 2;
			
		}
		
		double[] A_derivative_equation  = {x_A, y_A, d_A};
		double[] B_derivative_equation  = {x_B, y_B, d_B};
		double[] C_derivative_equation  = {x_C, y_C, d_C};
		
		double[] vector = {z_A, z_B, z_C};
		
		double[][] paramsVector = {A_derivative_equation, B_derivative_equation, C_derivative_equation};
		
		double[] output = matrixSolver(paramsVector, vector);
		
		
		return output;
		
	}
	
	public double[] matrixSolver(double[][] matrix, double[] vector) {
		RealMatrix coefficients = new Array2DRowRealMatrix(matrix, false);
		DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
		RealVector constants = new ArrayRealVector(vector, false);
		RealVector solution = solver.solve(constants);
		
		double[] output = solution.toArray();
		
		return output;
	}
	
	public double getError(double[] params, List<Sample> samples) {
		
		double e = 0;
		
		for(Sample sample : samples) {
			e += Math.abs(sample.getBlue())-Math.abs((sample.getRed()*params[0]+sample.getGreen()*params[1]+params[2]));
		}
		
		return e;
	}
	
}
