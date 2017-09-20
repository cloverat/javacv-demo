package learnJavaCV;

import org.bytedeco.javacpp.opencv_core.*;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.cvPyrDown;

/**
 * 图像大小减半
 * <p>
 * 来源：http://blog.csdn.net/qiao_198911/article/details/37814167 学习javacv之三:图像的平滑处理、大小减半
 *
 * @author chenyujun
 * @date 17-9-18
 */
public class Lesson3_sizeHalf {
    public static void main(String[] args) {
        //读取图像
        IplImage srcInImage = cvLoadImage("src/main/a1.jpg");
        if (srcInImage != null) {
            //显示未处理的图像
            cvShowImage("srcInImage", srcInImage);
            //      if (srcInImage.width()%2 !=0&&srcInImage.height()%2 !=0) {
            //          return;
            //      }
            //为图像输出分配空间
            IplImage srcOutImage = cvCreateImage(cvSize(srcInImage.width() / 2, srcInImage.height() / 2), srcInImage
                    .depth(), srcInImage.nChannels());
            //减半
            cvPyrDown(srcInImage, srcOutImage);
            cvShowImage("srcOutImage", srcOutImage);
            //将处理结果保存
            cvSaveImage("src/main/a1_sizeHalf.jpg", srcOutImage);
            //等待按键
            cvWaitKey();
            //释放空间
            cvReleaseImage(srcInImage);
            cvReleaseImage(srcOutImage);
            cvDestroyAllWindows();
        }
    }
}
