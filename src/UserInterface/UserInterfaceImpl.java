package UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserInterfaceImpl {

	public JFrame MainFrame;
	//public JLabel label;
	public BufferedImage testImage2;
	private ImageIcon icn;
	public String path = null;
	
	
	public UserInterfaceImpl() {
		
		MainFrame = new JFrame("Linear classifiers");
		MainFrame.setLayout(new BorderLayout());
		MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension d = new Dimension();
		d.setSize(500, 500);
		MainFrame.setSize(d);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.add(new ImagePane());
		MainFrame.setVisible(true);
	}
	
}
