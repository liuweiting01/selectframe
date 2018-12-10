package glassMirrorFromLiu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class RectFrameInDetector extends JPanel implements Runnable
{
	 public BufferedImage mImg;
	 public BufferedImage mat2BI(Mat mat)//ת��ͼƬ�ķ���
	 {  
	        int dataSize =mat.cols()*mat.rows()*(int)mat.elemSize();  
	        byte[] data=new byte[dataSize];  
	        mat.get(0, 0,data);  
	        int type=mat.channels()==1?  
	                BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_3BYTE_BGR;  
	          
	        if(type==BufferedImage.TYPE_3BYTE_BGR)
	        {  
	           for(int i=0;i<dataSize;i+=3)
	           {  
	              byte blue=data[i+0];  
	              data[i+0]=data[i+2];  
	              data[i+2]=blue;  
	            }  
	        }  
	        BufferedImage image=new BufferedImage(mat.cols(),mat.rows(),type);  
	        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);  
	          
	        return image;  
	    } 
	 public void paintComponent(Graphics g){  //��graphics����������ͷ���滭����
	        if(mImg!=null){  
	            g.drawImage(mImg, 0, 0, mImg.getWidth(),mImg.getHeight(),this);  
	        }  
	    }  
	 	
		int x,y;//����
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();//���ÿ���̨����Ĳ���
	public void paint (Graphics g)//����С�۾��ķ���
	{
				super.paint(g);
				super.paint(g);
				g.drawRect(x,y,h,w);
				g.drawRect(x+h+w-10,y,h,w);
				g.drawLine(x+h,y+w/2,x+h+w-10,y+w/2);
			
	}
	public void run() 
	{
		
		while (true) 
		{
			if(x>263)//��ֹ�۾���������
			{
				x=0;	
			}else {
					x=x+10;
					y=2*x;//���Ͻ�����λ�ù�ϵ
					this.repaint();//������ͼ�ؼ�
			}
			try 
			{
				Thread.sleep(1000);	
			}
			catch(InterruptedException e) //����
			{
				e.printStackTrace();
			} 
		}
	}
	public static void main(String[] args) 
	{  
        try
        {  
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
             
            Mat capImg=new Mat();  
            VideoCapture capture=new VideoCapture(0);  
            int height = (int)capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);  
            int width = (int)capture.get(Videoio.CAP_PROP_FRAME_WIDTH);  
            if(height==0||width==0)
            {  
                throw new Exception("camera cannot be found");  
            }  //������ͷ
            //
            
            System.out.print("Enter the width and height of your favor rectangle glasses frame��");//����̨����
    		int h;
    		int w;
            JFrame frame=new JFrame("rectangle glasses frame"); 
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
            RectFrameInDetector c=new  RectFrameInDetector();
            Thread panelThread=new Thread(c);
            frame.setContentPane(c);  
            frame.setVisible(true);  
            panelThread.start();
            frame.setSize(658, 527);
            int n=0;
            Mat temp=new Mat();
            while(frame.isShowing()&& n<500)
            {
                //System.out.println("��"+n+"��");
                capture.read(capImg);
                Imgproc.cvtColor(capImg, temp, Imgproc.COLOR_RGB2GRAY);
                //Imgcodecs.imwrite("c:/User/apple/Desktop"+n+".png", temp);
               // c.mImg=c.mat2BI(detectFace(capImg));//������������
                c.mImg=c.mat2BI(detectEye(capImg));
                c.repaint();
                //n++;
                //break;
            }  
            capture.release();  //�ͷ�
            frame.dispose();  
        }catch(Exception e)
        {  
            System.out.println("���⣺" + e);  
        }finally
        {  
            System.out.println("--done--");  
        }  
  
    }
	 public static Mat detectFace(Mat img) throws Exception//�沿
	    {

	        System.out.println("Face detection completed ");
	        CascadeClassifier faceDetector = new CascadeClassifier("C:\\Users\\apple\\Desktop\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
//xml����
	        MatOfRect faceDetections = new MatOfRect();//�½�

	        faceDetector.detectMultiScale(img, faceDetections);
	        Rect[] rects = faceDetections.toArray();
	        if(rects != null && rects.length >= 1)
	        {          
	            for (Rect rect : rects)
	            {  
	                Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),  
	                        new Scalar(155, 0, 0), 2);  
	            } 
	        }
	        return img;//ֻreturn mat����
	    }
	 public  static Mat detectEye(Mat img) throws Exception
	    {

	    	
	        System.out.println("Eye detection completed ");
	        CascadeClassifier eyeDetector = new CascadeClassifier("C:\\Users\\apple\\Desktop\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_eye.xml");
	        // ��ͼƬ�м������
	        MatOfRect faceDetections = new MatOfRect();

	        eyeDetector.detectMultiScale(img, faceDetections,2,0,1,new Size(50,50));


	        Rect[] rects = faceDetections.toArray();   
	       if(rects != null && rects.length >= 1){          
	          for (Rect rect :faceDetections.toArray()) {  
	            	Imgproc.rectangle(img, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(255,255,255));
	            	//Imgproc.rectangle(img, new Point(rects[1].x,rects[1].y), new Point(rects[1].x+rects[1].width,rects[1].y+rects[1].height),new Scalar(255,255,255));
	     // Imgproc.rectangle(img,new Point(rects[0].x,rects[0].y,new Point(rects[1].x+rect.width,rects[1].y+rect.height), new Scalar(0,0,255));
	            } 
	          
	            Rect a=rects[0];//��
		        Rect b=rects[1];//��
		         int m0=a.x;
		       int n0=a.y;
		        int p0=b.x;
		      int   q0=b.y;
		      
		        
		        System.out.print("a point:"+m0+"and"+n0);
		        System.out.print("b point:"+p0+"and"+q0);
		      
		        //return m0;
		        
	        
	        }return img;
	        }
	
	 
	public static Mat detectPeople(Mat img) 
	  {
	        
	        HOGDescriptor hog = new HOGDescriptor();
	        hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
	        System.out.println(HOGDescriptor.getDefaultPeopleDetector());
	        MatOfRect regions = new MatOfRect();  
	        MatOfDouble foundWeights = new MatOfDouble(); 
	        hog.detectMultiScale(img, regions, foundWeights);        
	        
	        return img;  
	    } 
	  

	
		
	}
	
	
	
	
	