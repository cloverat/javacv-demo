import org.bytedeco.javacpp.opencv_core.IplImage;

import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;

/**
 * 仅仅是展示图片
 * <p>
 * 来源：学习javacv之二:javacv0.8开发环境的搭建
 * url: http://blog.csdn.net/qiao_198911/article/details/37694081
 */
public class ReadImage {
    public static void main(String[] args) {
        //读取图像
        IplImage srcImg = cvLoadImage("src/main/Rice.jpeg");
        if (srcImg != null) {
            //新建窗体，并命名
            cvNamedWindow("test0.8");
            //展示图像
            cvShowImage("test0.8", srcImg);
            //等待按键，以执行下一步
            cvWaitKey(0);
            //释放图像空间
            cvReleaseImage(srcImg);
            //销毁窗体
            cvDestroyWindow("test0.8");
        }
    }
}
