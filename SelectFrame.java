package glassMirrorFromLiu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SelectFrame extends JFrame
	{
		public static void main(String[]args)
		{
			JFrame frame =new JFrame("Please choose the frame which you prefer");
			JLabel label=new JLabel();
			JButton circlebutton=new JButton("Circle");
			JButton rectbutton=new JButton("Rectangle");
			
			frame.setSize(600,600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(rectbutton);
			frame.add(circlebutton);
			frame.add(label);
			
			label.setSize(600,600);
			label.setIcon(new ImageIcon("C:\\Users\\apple\\Desktop\\3.jpg"));
			
			
			circlebutton.setBounds(0,200,300,50);//left pot weight height
			circlebutton.setContentAreaFilled(true);
			circlebutton.setFont(new Font("Century Gothic",Font.BOLD,40));
			circlebutton.setForeground(Color.RED);//需要设置鼠标单击事件
			
			
			rectbutton.setBounds(300,200,300,50);//left pot weight height
			rectbutton.setContentAreaFilled(true);
			rectbutton.setFont(new Font("Century Gothic",Font.BOLD,40));
			rectbutton.setForeground(Color.RED);
		
		}
	}
		

