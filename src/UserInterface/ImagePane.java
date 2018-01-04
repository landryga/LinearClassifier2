package UserInterface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImagePane extends JPanel implements ActionListener {
		public static BufferedImage img;
		private JPanel controlImagePanel;
		private JPanel controlLogicPanel;
		private JLabel label;
		private JButton select_image;
		private JButton learn;
		private JButton test;
		private static BufferedImage testImage2;
		private static ImageIcon  icn;
		private static List<String> paths;
		private int image_index;
		
		@SuppressWarnings("deprecation")
		public ImagePane() {
			
			
			this.setLayout(new GridBagLayout());
			GridBagConstraints cnstr = new GridBagConstraints();
			cnstr.gridwidth = GridBagConstraints.REMAINDER;
			
			//used to select appropriate image
			int image_index = 0;
			
			controlImagePanel = new JPanel();
			
			controlImagePanel.setLayout(new FlowLayout());
			
			select_image = new JButton("choose image");
			
			select_image.addActionListener(this);
			
			select_image.setActionCommand("Select Image");
			
			controlImagePanel.add(select_image);
			
			cnstr.fill = GridBagConstraints.NORTH;
			
			this.add(controlImagePanel, cnstr);
			
			//TODO attach images to the application
			String path1 = "/obraz1.JPG";
			String path2 = "/obraz2.JPG";
			String path3 = "/obraz3.JPG";
			
			paths = new LinkedList();
			
			paths.add(path1);
			paths.add(path2);
			paths.add(path3);
			
			controlLogicPanel = new JPanel();
			controlLogicPanel.setLayout(new FlowLayout());
			
			learn = new JButton("Learn");
			learn.addActionListener(this);
			learn.setActionCommand("learn");
			test = new JButton("Test");
			
			test.setVisible(false);
			
			controlLogicPanel.add(learn);
			controlLogicPanel.add(test);
			
			cnstr.fill = GridBagConstraints.SOUTH;
			
			this.add(controlLogicPanel, cnstr);
			
			
			try {
				//File file = new File(paths.get(image_index));
				testImage2 = ImageIO.read(this.getClass().getResource(paths.get(image_index)));
				icn = new ImageIcon(testImage2);
				label = new JLabel();
				label .setIcon(icn);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			cnstr.fill = GridBagConstraints.CENTER;
			
			add(label, cnstr);
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Select Image")) {
				if (image_index==paths.size()-1) {
					image_index = 0;
				}
				else image_index++;
				
				try {
					testImage2 = ImageIO.read(this.getClass().getResource(paths.get(image_index)));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				icn = new ImageIcon(testImage2);
				label.setIcon(icn);
				
			}
			else if (e.getActionCommand().equals("learn")) {
				try {
					LearnFrame frame = new LearnFrame(paths.get(image_index));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}