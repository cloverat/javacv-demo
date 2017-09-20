package learnJavaCV;

import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.IplImage;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.cvRectangle;

/**
 * 学习javacv之七：在画布上，画矩形边框
 * <p>
 * 来源：http://blog.csdn.net/qiao_198911/article/details/37932417
 *
 * @author chenyujun
 * @date 17-9-18
 */
public class Lesson7_drawRect {
    //JCVMouseCallBack:继承类CvMouseCallback,重写call方法,以便于在cvSetMouseCallback(...)进行回调
    static Lesson7_JCVMouseCallBack jcvMouseCallBack = new Lesson7_JCVMouseCallBack();

    public static void main(String[] args) {
        //初始化画图区域
        IplImage image = cvCreateImage(cvSize(640, 360), IPL_DEPTH_8U, 3);
        //将画图区域置为0(矩阵)
        cvZero(image);
        IplImage temImage = cvCloneImage(image);
        //新建窗口显示画图区域
        cvNamedWindow("DrawRebBox");
        //设置鼠标回调函数，以响应鼠标事件
        cvSetMouseCallback("DrawRebBox", jcvMouseCallBack, image);
        while (true) {
            cvCopy(image, temImage);
            if (jcvMouseCallBack.isDrawBox()) {
                //在temImage画布内，显示鼠标事件所画的图像
                drawBox(temImage, jcvMouseCallBack.getBox());
                //将图片保存到文件
                cvSaveImage("resources/drawRect.jpg", temImage);
            }
            cvShowImage("DrawRebBox", temImage);
            //按ESC键退出(ESC键对应的ASCII值为27)
            if (cvWaitKey(15) == 27) {
                break;
            }
        }
        //释放资源
        cvReleaseImage(image);
        cvReleaseImage(temImage);
        cvDestroyWindow("DrawRebBox");
    }

    public static void drawBox(IplImage image, CvRect cvRect) {
        cvRectangle(
                //image作为画布显示矩形
                image,
                //矩形的左上角坐标位置
                cvPoint(jcvMouseCallBack.getBox().x(), jcvMouseCallBack.getBox().y()),
                //矩形右下角坐标位置
                cvPoint(jcvMouseCallBack.getBox().x() + jcvMouseCallBack.getBox().width(), jcvMouseCallBack.getBox()
                        .y() + jcvMouseCallBack.getBox().height()),
                //边框的颜色
                CV_RGB(255, 0, 0),
                //线条的宽度:正值就是线宽，负值填充矩形,例如CV_FILLED，值为-1
                1,
                //线条的类型(0,8,4)
                4,
                //坐标的小数点位数
                0
        );
    }
}
