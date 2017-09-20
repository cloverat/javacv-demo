package openCv;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.Loader.sizeof;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_core.cvCreateMemStorage;
import static org.bytedeco.javacpp.opencv_core.cvGetSize;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * 获取图像重心
 * 来源：http://blog.csdn.net/cooelf/article/details/25153085
 *
 * @author chenyujun
 * @date 17-9-20
 */
public class FindHeart {

    public static void main(String[] args) {
        double m00, m10, m01, x, y;        //定义x,y为重心坐标。

        opencv_core.IplImage img1 = null;
        img1 = cvLoadImage("src/main/a1.jpg");

        //IplImage * img1 = cvLoadImage("tyre.jpg", 1);
        opencv_core.IplImage img1_gray = cvCreateImage(cvGetSize(img1), 8, 1);
        cvCvtColor(img1, img1_gray, CV_BGR2GRAY);
        opencv_core.IplImage img_edge1 = cvCreateImage(cvGetSize(img1), 8, 1);
        cvThreshold(img1_gray, img_edge1, 240, 255, CV_THRESH_BINARY);
        opencv_core.CvMemStorage storage1 = cvCreateMemStorage();
        opencv_core.CvSeq contour1 = null;
        // CvContour
        //int outline = cvFindContours(img_edge1, storage1, contour1, sizeof(opencv_core.CvContour), CV_RETR_LIST);  //获取轮廓数
        //System.out.println("轮廓数：%d\n" + outline);
        //轮廓的矩
        CvMoments moments1 = new CvMoments();
        cvMoments(contour1, moments1, 0);
        //Hu矩
        CvHuMoments huMonents1 = new CvHuMoments();
        cvGetHuMoments(moments1, huMonents1);
        System.out.println("遍历三阶矩：\n");
        for (int xOrder = 0; xOrder <= 3; xOrder++)
            for (int yOrder = 0; yOrder <= 3; yOrder++) {
                if (xOrder + yOrder <= 3) {
                    double spatialMoment = cvGetSpatialMoment(moments1, xOrder, yOrder);
                    //System.out.println("  %d,%d ： 空间距-%.2f\n", xOrder +","+ yOrder+"： 空间距-"+  spatialMoment);
                }
            }
        m00 = cvGetSpatialMoment(moments1, 0, 0);
        m10 = cvGetSpatialMoment(moments1, 1, 0);
        m01 = cvGetSpatialMoment(moments1, 0, 1);
        x = (int) (m10 / m00);
        y = (int) (m01 / m00);
        System.out.println("轮胎中心坐标为：" + x + "," + y);
    }
}
