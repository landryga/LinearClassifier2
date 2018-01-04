package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Logic.DecisionProcedure;
import Plot.PlotDrawer;
import Samples.Sample;

public class LearnFrame extends JFrame {
	
	private static BufferedImage thisImage;
	private static ImageIcon  icn;
	
	LearnFrame(String path) throws IOException {
		this.setLayout(new BorderLayout()) ;
		Dimension d = new Dimension();
		d.setSize(500, 500);
		this.setSize(d);
		this.setLocationRelativeTo(null);
		this.add(new LearnPane(path));
		this.setVisible(true);
	}
	
	public class LearnPane extends JPanel implements ActionListener, MouseListener {

		private JPanel controlsPanel;
		private JPanel bottomPanel;
		private JLabel imageLabel;
		private JButton class1Button;
		private JButton class2Button;
		private JButton class3Button;
		private JButton class1OK;
		private JButton class2OK;
		private JButton class3OK;
		private JButton resetButton;
		private JButton class1functions;
		private JButton class2functions;
		private JButton class3functions;
		private JButton class1Classifier;
		private JButton class2Classifier;
		private JButton class3Classifier;
		public List<Sample> samples;
		private boolean canSelect;
		String class_helper;
		Graphics g;
		String this_path;
		int class1_counter = 0;
		int class2_counter = 0;
		int class3_counter = 0;
		
		
		LearnPane(String path) throws IOException {
			
			
			this_path = path;
			
			canSelect = false;
			
			samples = new LinkedList<Sample>();
			
			thisImage = ImageIO.read(this.getClass().getResource(this_path)); 
			icn = new ImageIcon(thisImage);
			this.setLayout(new GridBagLayout());
			GridBagConstraints cnstr = new GridBagConstraints();
			cnstr.gridwidth = GridBagConstraints.REMAINDER;
			
			controlsPanel = new JPanel();
			GridLayout Layout = new GridLayout(3, 2);
			controlsPanel.setLayout(Layout);
			class1Button = new JButton("Select samples for 1st class");
			class1Button.addActionListener(this);
			class1Button.setActionCommand("cls1select");
			class1Button.setEnabled(true);
			class1OK = new JButton("OK");
			class1OK.addActionListener(this);
			class1OK.setActionCommand("cls1OK");
			class1OK.setEnabled(true);
			class2Button = new JButton("Select samples for 2nd class");
			class2Button.addActionListener(this);
			class2Button.setActionCommand("cls2select");
			class2Button.setEnabled(false);
			class2OK = new JButton("OK");
			class2OK.setActionCommand("cls2OK");
			class2OK.addActionListener(this);
			class2OK.setEnabled(false);
			class3Button = new JButton("Select samples for 3rd class");
			class3Button.addActionListener(this);
			class3Button.setActionCommand("cls3select");
			class3Button.setEnabled(false);
			class3OK = new JButton("OK");
			class3OK.addActionListener(this);
			class3OK.setEnabled(false);
			class3OK.setActionCommand("cls3OK");
			
			controlsPanel.add(class1Button);
			controlsPanel.add(class1OK);
			controlsPanel.add(class2Button);
			controlsPanel.add(class2OK);
			controlsPanel.add(class3Button);
			controlsPanel.add(class3OK);
			
			cnstr.fill = GridBagConstraints.NORTH;
			
			add(controlsPanel, cnstr);
			
			imageLabel = new JLabel();
			
			imageLabel.setIcon(icn);
			
			cnstr.fill = GridBagConstraints.CENTER;
			
			imageLabel.addMouseListener(this);
			
			add(imageLabel, cnstr);
			
			resetButton = new JButton("Reset");
			
			resetButton.addActionListener(this);
			resetButton.setActionCommand("Reset");
			resetButton.setEnabled(true);
			
			class1functions = new JButton("Class 1 analysis");
			class1functions.addActionListener(this);
			class1functions.setActionCommand("class1 analysis");
			class1functions.setEnabled(false);
			class2functions = new JButton("Class 2 analysis");
			class2functions.addActionListener(this);
			class2functions.setActionCommand("class2 analysis");
			class2functions.setEnabled(false);
			class3functions = new JButton("Class 3 analysis");
			class3functions.addActionListener(this);
			class3functions.setActionCommand("class3 analysis");
			class3functions.setEnabled(false);
			
			class1Classifier = new JButton("Class 1 classifier");
			class1Classifier.addActionListener(this);
			class1Classifier.setActionCommand("class1 classifier");
			class1Classifier.setEnabled(false);
			class2Classifier = new JButton("Class 2 classifier");
			class2Classifier.addActionListener(this);
			class2Classifier.setActionCommand("class2 classifier");
			class2Classifier.setEnabled(false);
			class3Classifier = new JButton("Class 3 classifier");
			class3Classifier.addActionListener(this);
			class3Classifier.setActionCommand("class3 classifier");
			class3Classifier.setEnabled(false);
			
			bottomPanel = new JPanel();
			GridLayout bottomLayout = new GridLayout(3, 3);
			
			bottomPanel.setLayout(bottomLayout);
			
			bottomPanel.add(class1functions);
			bottomPanel.add(class2functions);
			bottomPanel.add(class3functions);
			bottomPanel.add(class1Classifier);
			bottomPanel.add(class2Classifier);
			bottomPanel.add(class3Classifier);
			bottomPanel.add(resetButton);
			
			cnstr.fill = GridBagConstraints.SOUTH;
			
			add(bottomPanel, cnstr);
			
		}
		
		private void setButtonsActive(boolean b) {
			if (b==true) {
				class1Button.setEnabled(true);
				class2Button.setEnabled(true);
				class3Button.setEnabled(true);
				class1OK.setEnabled(true);
				class2OK.setEnabled(true);
				class3OK.setEnabled(true);
				class1functions.setEnabled(true);
				class2functions.setEnabled(true);
				class3functions.setEnabled(true);
				class1Classifier.setEnabled(true);
				class2Classifier.setEnabled(true);
				class3Classifier.setEnabled(true);
			}
			else {
				class1Button.setEnabled(false);
				class2Button.setEnabled(false);
				class3Button.setEnabled(false);
				class1OK.setEnabled(false);
				class2OK.setEnabled(false);
				class3OK.setEnabled(false);
				class1functions.setEnabled(false);
				class2functions.setEnabled(false);
				class3functions.setEnabled(false);
				class1Classifier.setEnabled(false);
				class2Classifier.setEnabled(false);
				class3Classifier.setEnabled(false);
			}
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(canSelect) {
				
				int colorNumber = thisImage.getRGB(e.getX(), e.getY());
				Color color_sample = new Color(colorNumber, true);
				Sample sample = new Sample(class_helper, color_sample.getRed(), color_sample.getBlue(),  color_sample.getGreen());
				samples.add(sample);
				Color color = new Color(1);
				g = thisImage.getGraphics();
				
				g.setColor(color.RED);
				
				switch(class_helper) {
					case "Class1": g.drawOval(e.getX(), e.getY(), 5, 5); 
						class1_counter++;
						break;
					case "Class2": g.drawRect(e.getX(), e.getY(), 5, 5); 
						class2_counter++;
						break;
					case "Class3": g.fillOval(e.getX(), e.getY(), 5, 5); 
						class3_counter++;
						break;
				}
				
				icn = new ImageIcon(thisImage);
				imageLabel.setIcon(icn);
				System.out.println("Sample clas = " + sample.getClass_helper() + ", sample red: " + sample.getRed() + ", sample green: " +sample.getGreen() + ", sample blue: " + sample.getBlue());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
				case "cls1select":
					canSelect = true;
					class_helper = "Class1";
					class1Button.setEnabled(false);
					class2Button.setEnabled(false);
					class3Button.setEnabled(false);
					class2OK.setEnabled(false);
					class3OK.setEnabled(false);
					break;
				case "cls2select":
					canSelect = true;
					class_helper = "Class2";
					class1Button.setEnabled(false);
					class2Button.setEnabled(false);
					class3Button.setEnabled(false);
					class1OK.setEnabled(false);
					class3OK.setEnabled(false);
					break;
				case "cls3select":
					canSelect = true;
					class_helper = "Class3";
					class1Button.setEnabled(false);
					class2Button.setEnabled(false);
					class3Button.setEnabled(false);
					class1OK.setEnabled(false);
					class2OK.setEnabled(false);
					break;
				case "cls1OK":
					if(samples.size()<3) {
						JOptionPane.showMessageDialog(this,"select at least 3 samples!" );
						break;
					}
					canSelect = false;
					if ( class1Button.isEnabled()==true) {
						break;
					}
					class2Button.setEnabled(true);
					class2OK.setEnabled(true);
					class1OK.setEnabled(false);
					break;
				case "cls2OK":
					if(class2_counter<3) {
						JOptionPane.showMessageDialog(this,"select at least 3 samples!" );
						break;
					}
					canSelect = false;
					if ( class2Button.isEnabled()==true) {
						break;
					}
					class3Button.setEnabled(true);
					class3OK.setEnabled(true);
					class2OK.setEnabled(false);
					break;
				case "cls3OK":
					if(class3_counter<3) {
						JOptionPane.showMessageDialog(this,"select at least 3 samples!" );
						break;
					}
					canSelect = false;
					if ( class3Button.isEnabled()==true) {
						break;
					}
					setButtonsActive(false);
					class1functions.setEnabled(true);
					class2functions.setEnabled(true);
					class3functions.setEnabled(true);
					class1Classifier.setEnabled(true);
					class2Classifier.setEnabled(true);
					class3Classifier.setEnabled(true);
					break;
				case "Reset":
					setButtonsActive(false);
					class1Button.setEnabled(true);
					class1OK.setEnabled(true);
					try {
						File file = new File(this_path);
						thisImage = ImageIO.read(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					icn = new ImageIcon(thisImage);
					imageLabel.setIcon(icn);
					break;
				case "class1 analysis":
					
					
					List<Sample> class1Samples = new LinkedList<Sample>();
					
					List<Sample> rest1Samples = new LinkedList<Sample>();
					
					for(Sample sample: samples) {
						if(sample.getClass_helper().equals("Class1")) {
							class1Samples.add(sample);
						}
						else {
							rest1Samples.add(sample);
						}
					}
					
					DecisionProcedure proc1_1 = new DecisionProcedure();
					DecisionProcedure proc1_2 = new DecisionProcedure();
					
					double[] params1_1 = proc1_1.getParameters(class1Samples);
					double[] params1_2 = proc1_2.getParameters(rest1Samples);
					
					double[] errors1 = {proc1_1.getError(params1_1, class1Samples), proc1_2.getError(params1_2, rest1Samples)};
					
					PlotDrawer plt1 = new PlotDrawer(samples, params1_1, params1_2, errors1, false);
					
					break;
				
				case "class2 analysis":
					
					
					List<Sample> class2Samples = new LinkedList<Sample>();
					
					List<Sample> rest2Samples = new LinkedList<Sample>();
					
					for(Sample sample: samples) {
						if(sample.getClass_helper().equals("Class2")) {
							class2Samples.add(sample);
						}
						else {
							rest2Samples.add(sample);
						}
					}
					
					DecisionProcedure proc2_1 = new DecisionProcedure();
					DecisionProcedure proc2_2 = new DecisionProcedure();
					
					double[] params2_1 = proc2_1.getParameters(class2Samples);
					double[] params2_2 = proc2_1.getParameters(rest2Samples);
					
					double[] errors2 = {proc2_1.getError(params2_1, class2Samples), proc2_2.getError(params2_2, rest2Samples)};
					
					PlotDrawer plt2 = new PlotDrawer(samples, params2_1, params2_2, errors2, false);
					
					break;
					
					
				case "class3 analysis":
					
					
					List<Sample> class3Samples = new LinkedList<Sample>();
					
					List<Sample> rest3Samples = new LinkedList<Sample>();
					
					for(Sample sample: samples) {
						if(sample.getClass_helper().equals("Class3")) {
							class3Samples.add(sample);
						}
						else {
							rest3Samples.add(sample);
						}
					}
					
					DecisionProcedure proc3_1 = new DecisionProcedure();
					DecisionProcedure proc3_2 = new DecisionProcedure();
					
					double[] params3_1 = proc3_1.getParameters(class3Samples);
					double[] params3_2 = proc3_1.getParameters(rest3Samples);
					
					double[] errors3 = {proc3_1.getError(params3_1, class3Samples), proc3_2.getError(params3_2, rest3Samples)};
					
					PlotDrawer plt3 = new PlotDrawer(samples, params3_1, params3_2, errors3, false);
					
					break;
					
				case "class1 classifier":
					
					
					List<Sample> class4Samples = new LinkedList<Sample>();
					
					List<Sample> rest4Samples = new LinkedList<Sample>();
					
					for(Sample sample: samples) {
						if(sample.getClass_helper().equals("Class1")) {
							class4Samples.add(sample);
						}
						else {
							rest4Samples.add(sample);
						}
					}
					
					DecisionProcedure proc4_1 = new DecisionProcedure();
					DecisionProcedure proc4_2 = new DecisionProcedure();
					
					double[] params4_1 = proc4_1.getParameters(class4Samples);
					double[] params4_2 = proc4_1.getParameters(rest4Samples);
					
					double[] test = {2};
					
					PlotDrawer plt4 = new PlotDrawer(samples, params4_1, params4_2, test, true);
					
					break;
					
				case "class2 classifier":
					
					
					List<Sample> class5Samples = new LinkedList<Sample>();
					
					List<Sample> rest5Samples = new LinkedList<Sample>();
					
					for(Sample sample: samples) {
						if(sample.getClass_helper().equals("Class2")) {
							class5Samples.add(sample);
						}
						else {
							rest5Samples.add(sample);
						}
					}
					
					DecisionProcedure proc5_1 = new DecisionProcedure();
					DecisionProcedure proc5_2 = new DecisionProcedure();
					
					double[] params5_1 = proc5_1.getParameters(class5Samples);
					double[] params5_2 = proc5_1.getParameters(rest5Samples);
					
					double[] test1 = {2};
					
					PlotDrawer plt5 = new PlotDrawer(samples, params5_1, params5_2, test1, true);
					
					break;
					
				case "class3 classifier":
					
					
					List<Sample> class6Samples = new LinkedList<Sample>();
					
					List<Sample> rest6Samples = new LinkedList<Sample>();
					
					for(Sample sample: samples) {
						if(sample.getClass_helper().equals("Class3")) {
							class6Samples.add(sample);
						}
						else {
							rest6Samples.add(sample);
						}
					}
					
					DecisionProcedure proc6_1 = new DecisionProcedure();
					DecisionProcedure proc6_2 = new DecisionProcedure();
					
					double[] params6_1 = proc6_1.getParameters(class6Samples);
					double[] params6_2 = proc6_1.getParameters(rest6Samples);
					
					double[] test7 = {2};
					
					PlotDrawer plt6 = new PlotDrawer(samples, params6_1, params6_2, test7, true);
					
					break;
					
			}
				
		}
		
	}

}
