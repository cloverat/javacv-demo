package learnJavaCV;

import org.bytedeco.javacpp.opencv_core.CvMat;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImageM;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_core.*;

/**
 * 图像平滑处理
 * <p>
 * 来源：http://blog.csdn.net/qiao_198911/article/details/37814167 学习javacv之三:图像的平滑处理、大小减半
 *
 * @author chenyujun
 * @date 17-9-18
 */
public class Lesson2_smooth {
    public static void main(String[] args) {
        //因为Mat处理图像有问题，暂时使用cvMat
        CvMat srciInImage = cvLoadImageM("src/main/a1.jpg");
        if (srciInImage != null) {
            //显示待处理图像
            cvShowImage("srcInImage", srciInImage);
            //处理图像
            cvSmooth(srciInImage, srciInImage);
            CvMat srciOutImage = srciInImage;
            //显示处理完毕的图像
            cvShowImage("srcOutImage", srciOutImage);
            //保存处理过的图像
            cvSaveImage("src/main/beauty_smooth.jpg", srciOutImage);
            //等待按键响应
            cvWaitKey(0);
            //释放资源
            cvReleaseMat(srciInImage);
            //销毁窗口
            cvDestroyWindow("srcInImage");
            cvDestroyWindow("srcOutImage");
        }
    }
}
