package glassMirrorFromLiu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public  class Start extends JFrame 
{
	
		
		JFrame frame=new JFrame();
		JLabel label=new JLabel();
		JLabel title=new JLabel();
		JButton start=new JButton("start!");
		
		
		 void start()
		{
		frame.setSize(600,600);
		frame.setTitle("Frame");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(title);
		frame.add(label);
		frame.add(start);
		
		label.setSize(600,600);
		label.setIcon(new ImageIcon("C:\\Users\\apple\\Desktop\\3.jpg"));
		label.add(start);
		title.setSize(600, 50);
		title.setText("Choose your Frame!");
		
		title.setFont(new Font("Century Gothic",Font.BOLD,50));
		title.setForeground(Color.GREEN);
		
		start.setBounds(250,250,100,100);//left pot weight height
		start.setContentAreaFilled(true);
		start.setFont(new Font("Century Gothic",Font.BOLD,20));
		start.setForeground(Color.RED);
		
		start.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent e)
			{
			}
			public void mouseExited(MouseEvent e)
			{
			}
			public void mouseReleased(MouseEvent e)
			{
			}
			public void mousePressed(MouseEvent e) 
			{
			}
			@SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) 
			{
		//	FaceDetector face=new FaceDetector();
		//	face.main(null);
		//	face.paintComponent(null);
			//face.mat2BI(null);
		
			frame.setVisible(false);
				
			}}
		);
		}
		public static void main(String[]args)
		{
			Start s=new Start();
			s.start();
			

			
		}
		}
		
		
	