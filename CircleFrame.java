package glassMirrorFromLiu;


import java.awt.BasicStroke;
import java.awt.Graphics;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;


public  class CircleFrame extends JPanel implements Runnable 
{
		int x,y;
		Scanner sc=new Scanner(System.in);
		int z=sc.nextInt();
	public void paint (Graphics g)
	{
				super.paint(g);
				g.drawOval(x,y,z,z);
				g.drawOval(x+z+15,y,z,z);
				g.drawLine(x+z,y+z/2,x+z+15,y+z/2);
			
	}
	
	public void run() 
	{
		
		while (true) 
		{
			if(x>263) 
			{
				x=0;	
			}else {
					x=x+10;
					y=2*x;
					this.repaint();
			}
			try 
			{
				Thread.sleep(1000);	
			}
			catch(InterruptedException e) 
			{
				e.printStackTrace();
			} 
		}
	}
	public static void main(String[]args)
	{
		System.out.print("Enter the radius of your favor circle glasses frame(less than 100):");
		CircleFrame w=new CircleFrame();
		Thread panelThread=new Thread(w);
		JFrame frame=new JFrame();//´°¿Ú
		frame.add(w);
		frame.setSize(658,527);
		frame.setName("circle glasses frame");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelThread.start();
	
	
	}}
	
