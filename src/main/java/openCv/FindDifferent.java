package openCv;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

/**
 * OpenCV：选出两幅图像中不同的地方
 * 来源：http://blog.csdn.net/cooelf/article/details/24663097
 *
 * @author chenyujun
 * @date 17-9-20
 */
public class FindDifferent {
    public static void main(String[] args) {
        opencv_core.IplImage pSrc = null;
        pSrc = cvLoadImage("src/main/lf1.jpg");
        //IplImage * pSrc = cvLoadImage("Image.jpg", 1);
        //ShowImage(rawImage, "RawImage", 256);

        opencv_core.CvSize size = cvSize(600, 376);//区域大小
        cvSetImageROI(pSrc, cvRect(0, 0, size.width(), size.height()));//设置源图像ROI
        IplImage pDest1 = cvCreateImage(size, pSrc.depth(), pSrc.nChannels());
        //IplImage * pDest1 = cvCreateImage(size, pSrc -> depth, pSrc -> nChannels);//创建目标图像
        cvCopy(pSrc, pDest1); //复制图像
        cvResetImageROI(pDest1);//源图像用完后，清空ROI
        cvSaveImage("src/main/lf2.jpg", pDest1);//保存目标图像
        cvSetImageROI(pSrc, cvRect(0, 393, size.width(), size.height()));//设置源图像ROI
        IplImage pDest2 = cvCreateImage(size, pSrc.depth(), pSrc.nChannels());
        //IplImage * pDest2 = cvCreateImage(size, pSrc -> depth, pSrc -> nChannels);//创建目标图像
        cvCopy(pSrc, pDest2); //复制图像
        cvResetImageROI(pDest2);//源图像用完后，清空ROI
        cvSaveImage("lf3.jpg", pDest2);//保存目标图像
        IplImage dst;
        //IplImage * dst;
        dst = cvCreateImage(cvGetSize(pDest1), pDest1.depth(), pDest1.nChannels());
        cvAbsDiff(pDest1, pDest2, dst);
        cvNamedWindow("compareLf", 1);
        cvShowImage("compareLf", dst);
        cvWaitKey();

        return;
    }
}
