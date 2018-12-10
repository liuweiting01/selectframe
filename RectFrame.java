package glassMirrorFromLiu;

import java.awt.Graphics;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public  class RectFrame extends JPanel implements Runnable {
		int x,y;
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int n=sc.nextInt();
	public void paint (Graphics g)
	{
				super.paint(g);
				g.drawRect(x,y,m,n);
				g.drawRect(x+m+n-10,y,m,n);
				g.drawLine(x+m,y+n/2,x+m+n-10,y+n/2);
			
	}
	
	public void run() 
	{
		
		while (true) 
		{
			if(x>800)
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
		System.out.print("Enter the width and height of your favor rectangle glasses frame£º");
		int m;
		int n;
		RectFrame w=new RectFrame();
		Thread panelThread=new Thread(w);
		JFrame frame=new JFrame();//´°¿Ú
		frame.add(w);
		frame.setSize(800,800);
		frame.setName("rectangle glasses frame");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelThread.start();
	
	
	}}
	



