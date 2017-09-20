package learnJavaCV;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_imgproc;

import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_core.flip;
import static org.bytedeco.javacpp.opencv_imgcodecs.IMREAD_COLOR;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.circle;


/**
 * @author chenyujun
 * @date 17-9-18
 */

/**
 * 功能说明：使用opencv读写图像，
 * 通过基本的翻转、在图像上做标记，加入文字等对图像处理有一个基本认识
 *
 * @author:linghushaoxia
 * @time:2016年4月18日下午9:57:25
 * @version:1.0 为中国孱弱的技术，
 * 撑起一片自立自强的天空
 */
public class Lesson8_loadAndSaveImage {
    public static void main(String[] args) {
        //以彩色模式读取图像
        Mat image = imread("src/main/a1.jpg", IMREAD_COLOR);
        if (image == null || image.empty()) {
            System.out.println("读取图像失败，图像为空");
            return;
        }

        System.out.println("图像宽x高" + image.cols() + " x " + image.rows());
        /**
         * 显示图像,opencv自带的显示方法，跨平台性能不好，转换为java2D显示图像
         * windows下可以使用如下代码进行显示
         * opencv_highgui.imshow("原始图像", image);
         */
        Lesson8_JavaCVUtil.imShow(image, "原始图像");
        //创建空mat，保存处理图像
        Mat result = new Mat();
        int flipCode = 1;
        /**
         * flipCode
         * >0  水平翻转
         * =0 垂直翻转
         * <0  同时翻转
         *
         */
        flip(image, result, flipCode);
        //显示处理过的图像
        Lesson8_JavaCVUtil.imShow(result, "水平翻转");
        /**
         * 保存图像
         * 也可使用opencv原生方法 opencv_imgcodecs. imwrite("output.bmp", result);
         */
        Lesson8_JavaCVUtil.imWrite(result, "data/javacv/lakeResult.jpg");

        //克隆图像
        Mat imageCircle = image.clone();
        /**
         * 在图像上画圆
         */
        circle(imageCircle, // 目标图像
                new Point(420, 150), // 圆心坐标
                65, // radius
                new Scalar(0, 200, 0, 0), // 颜色，绿色
                2, // 线宽
                8, // 8-connected line
                0); // shift

        opencv_imgproc.putText(imageCircle, //目标图像
                "Lake and Tower", // 文本内容(不可包含中文)
                new Point(460, 200), // 文本起始位置坐标
                FONT_HERSHEY_PLAIN, // 字体类型
                2.0, // 字号大小
                new Scalar(0, 255, 0, 3), //文本颜色，绿色
                1, // 文本字体线宽
                8, // 线形.
                false); //控制文本走向
        Lesson8_JavaCVUtil.imShow(imageCircle, "画圆mark");
    }
}
