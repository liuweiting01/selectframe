package glassMirrorFromLiu;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.SVM;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
  
public class FaceDetector extends JPanel{
	  public BufferedImage mImg;
	
      
	  public BufferedImage mat2BI(Mat mat){  
	        int dataSize =mat.cols()*mat.rows()*(int)mat.elemSize();  
	        byte[] data=new byte[dataSize];  
	        mat.get(0, 0,data);  
	        int type=mat.channels()==1?  
	                BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_3BYTE_BGR;  
	          
	        if(type==BufferedImage.TYPE_3BYTE_BGR){  
	           for(int i=0;i<dataSize;i+=3){  
	              byte blue=data[i+0];  
	              data[i+0]=data[i+2];  
	              data[i+2]=blue;  
	            }  
	        }  
	        BufferedImage image=new BufferedImage(mat.cols(),mat.rows(),type);  
	        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);  
	          
	        return image;  
	    }  
	  
	    public void paintComponent(Graphics g){  
	        if(mImg!=null){  
	            g.drawImage(mImg, 0, 0, mImg.getWidth(),mImg.getHeight(),this);  
	        }  
	    }  
	      
	    public static void main(String[] args) {  
	        try{  
	            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
	             
	            Mat capImg=new Mat();  
	            VideoCapture capture=new VideoCapture(0);  
	            int height = (int)capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);  
	            int width = (int)capture.get(Videoio.CAP_PROP_FRAME_WIDTH);  
	            if(height==0||width==0){  
	                throw new Exception("camera cannot be found!");  
	            }  
	              
	            JFrame frame=new JFrame("camera");  
	            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
	            FaceDetector panel=new FaceDetector();
	           
	            frame.setContentPane(panel);  
	            frame.setVisible(true);  
	            frame.setSize(width+frame.getInsets().left+frame.getInsets().right,  
	                    height+frame.getInsets().top+frame.getInsets().bottom);  
	            int n=0;
	            Mat temp=new Mat();
	            while(frame.isShowing()&& n<500){
	                //System.out.println("第"+n+"张");
	                capture.read(capImg);
	                Imgproc.cvtColor(capImg, temp, Imgproc.COLOR_RGB2GRAY);
	                //Imgcodecs.imwrite("c:/User/apple/Desktop"+n+".png", temp);
	                panel.mImg=panel.mat2BI(detectFace(capImg));
	                panel.repaint();
	                //n++;
	                //break;
	            }  
	            capture.release();  
	            frame.dispose();  
	        }catch(Exception e){  
	            System.out.println("例外：" + e);  
	        }finally{  
	            System.out.println("--done--");  
	        }  
	  
	    }
	    //人脸部分
	    public static Mat detectFace(Mat img) throws Exception
	    {

	        System.out.println("Face detection completed ");
	        
	        CascadeClassifier faceDetector = new CascadeClassifier("C:\\Users\\apple\\Desktop\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");


	        // 在图片中检测人脸
	        MatOfRect faceDetections = new MatOfRect();

	        faceDetector.detectMultiScale(img, faceDetections);


	        Rect[] rects = faceDetections.toArray();
	        if(rects != null && rects.length >= 1){          
	            for (Rect rect : rects) {  
	                Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),  
	                        new Scalar(155, 0, 0), 2);  
	            } 
	        }
	        return img;
	    }
	    
	    
	    
	    public static Mat detectPeople(Mat img) {
	        
	        HOGDescriptor hog = new HOGDescriptor();
	        hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
	        System.out.println(HOGDescriptor.getDefaultPeopleDetector());
	        MatOfRect regions = new MatOfRect();  
	        MatOfDouble foundWeights = new MatOfDouble(); 
	        hog.detectMultiScale(img, regions, foundWeights);        
	        
	        return img;  
	    } 
	    
	}
