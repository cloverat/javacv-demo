package learnJavaCV;

import org.bytedeco.javacpp.opencv_core.IplImage;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

/**
 * 图像的Alpha融合
 * <p>
 * 来源：http://blog.csdn.net/qiao_198911/article/details/37884831 学习javacv之五:图像的Alpha融合
 *
 * @author chenyujun
 * @date 17-9-18
 */
public class Lesson5_alphaBlend {
    public static void main(String[] args) {
        //原始图像
        IplImage srciImageOne = cvLoadImage("src/main/b1.jpg");
        //等待融合的图像
        IplImage srciImageTwo = cvLoadImage("src/main/a1.jpg");
        if (srciImageOne == null || srciImageTwo == null) {
            return;
        }
        //定义
        int x = 100;
        int y = 200;
        int height = srciImageOne.height();
        int width = srciImageOne.width();
        double alpha = 0.8;
        double beta = 1.0 - alpha;
        //设置图像的感兴趣区域：二者的感兴趣区域大小要一样
        cvSetImageROI(
                //源图像
                srciImageOne,
                //感兴趣区域：一个矩形,从左上角(x,y)到右下角(width, height)的区域
                cvRect(x, y, width, height));
        //展示图像srciImageOne的感兴趣区域
        cvShowImage("AlphaROIOne", srciImageOne);
        cvSaveImage("resources/srcImageOne.jpg", srciImageOne);
        //设置srciImageTwo感兴趣的区域
        cvSetImageROI(srciImageTwo, cvRect(x, y, width, height));
        //展示图像srciImageTwo的感兴趣区域
        cvShowImage("AlphaROITwo", srciImageTwo);
        cvSaveImage("resources/srcImageTwo.jpg", srciImageTwo);
        cvAddWeighted(
                //图像1，所占权重
                srciImageOne, alpha,
                //图像2，所占权重
                srciImageTwo, beta,
                //常数值对输出结果有影响关系为：dst(I)=src1(I)*alpha+src2(I)*beta+gamma
                0.6,
                //输出结果
                srciImageTwo);
        //将结果恢复为原来的大小
        cvResetImageROI(srciImageTwo);
        cvShowImage("AlphaBlend", srciImageTwo);
        cvSaveImage("resources/srcImageTwo.jpg", srciImageTwo);
        cvWaitKey();
    }
}
