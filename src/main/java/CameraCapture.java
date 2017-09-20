import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;

//import com.googlecode.javacv.CanvasFrame;
//import com.googlecode.javacv.OpenCVFrameGrabber;
//import com.googlecode.javacv.cpp.opencv_core.IplImage;
//import static com.googlecode.javacv.cpp.opencv_core.cvReleaseImage;

/**
 * 本程序通过JFrame实时显示本机摄像头图像，并将图像存储到一个缓冲区
 * 当用户用鼠标点击JFrame中任何区域时，显示抓取图像的简单动画，同时保存缓冲区的图像到磁盘文件中
 * 点击JFrame关闭按钮可以退出程序。
 */

/**
 * Use JavaCV/OpenCV to capture camera images
 * <p>
 * There are two functions in this demo:
 * 1) show real-time camera images
 * 2) capture camera images by mouse-clicking anywhere in the JFrame,
 * the jpg file is saved in a hard-coded path.
 *
 * @author ljs
 *         2011-08-19
 */
public class CameraCapture {
    public static String savedImageFile = "src/main/my.jpg";

    //timer for image capture animation
    static class TimerAction implements ActionListener {
        private Graphics2D g;
        private CanvasFrame canvasFrame;
        private int width, height;

        private int delta = 10;
        private int count = 0;

        private Timer timer;

        public void setTimer(Timer timer) {
            this.timer = timer;
        }

        public TimerAction(CanvasFrame canvasFrame) {
            this.g = (Graphics2D) canvasFrame.getCanvas().getGraphics();
            this.canvasFrame = canvasFrame;
            this.width = canvasFrame.getCanvas().getWidth();
            this.height = canvasFrame.getCanvas().getHeight();
        }

        public void actionPerformed(ActionEvent e) {
            int offset = delta * count;
            if (width - offset >= offset && height - offset >= offset) {
                g.drawRect(offset, offset, width - 2 * offset, height - 2 * offset);
                canvasFrame.repaint();
                count++;
            } else {
                //when animation is done, reset count and stop timer.
                timer.stop();
                count = 0;
            }
        }
    }

    static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

    public static void main(String[] args) throws Exception {
        //open camera source
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();

        //create a frame for real-time image display
        CanvasFrame canvasFrame = new CanvasFrame("Camera");

        // 将Frame转为IplImage
        opencv_core.IplImage image = converter.convertToIplImage(grabber.grab());
        //opencv_core.IplImage image2 = converter.convert(frame);

        //IplImage image = grabber.grab();
        int width = image.width();
        int height = image.height();
        canvasFrame.setCanvasSize(width, height);

        //onscreen buffer for image capture
        final BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D bGraphics = bImage.createGraphics();

        //animation timer
        TimerAction timerAction = new TimerAction(canvasFrame);
        final Timer timer = new Timer(10, timerAction);
        timerAction.setTimer(timer);

        //click the frame to capture an image
        canvasFrame.getCanvas().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                timer.start(); //start animation
                try {
                    ImageIO.write(bImage, "jpg", new File(savedImageFile));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //real-time image display
        Frame image2 = null;
        while (canvasFrame.isVisible() && (image2 = grabber.grab()) != null) {
            if (!timer.isRunning()) { //when animation is on, pause real-time display
                canvasFrame.showImage(image2);
                //draw the onscreen image simutaneously
                //TODO 这里要怎么处理
                //bGraphics.drawImage(image2.getBufferedImage(), null, 0, 0);
            }
        }

        //release resources
        cvReleaseImage(image);
        grabber.stop();
        canvasFrame.dispose();
    }
}
