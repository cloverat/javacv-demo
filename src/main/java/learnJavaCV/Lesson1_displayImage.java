package learnJavaCV;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;

/**
 * 展示图片的简单教程
 * <p>
 * 来源http://blog.csdn.net/qiao_198911/article/details/37693449 学习JAVACV之一：javacv0.7的开发环境搭建
 *
 * @author chenyujun
 * @date 17-9-18
 */
public class Lesson1_displayImage {

    // 整体上，可以认为javacv是以JNI的方式，实现了对opecv库文件的调用
    // JNI：
    // 是Java Native Interface的缩写
    // 它提供了若干的API实现了Java和其他语言的通信（主要是C&C++）
    // 从Java1.1开始，JNI标准成为java平台的一部分，它允许Java代码和其他语言写的代码进行交互
    // JNI一开始是为了本地已编译语言，尤其是C和C++而设计的，但是它并不妨碍你使用其他编程语言，只要调用约定受支持就可以了
    // 使用java与本地已编译的代码交互，通常会丧失平台可移植性
    // 但是，有些情况下这样做是可以接受的，甚至是必须的
    // 例如，使用一些旧的库，与硬件、操作系统进行交互，或者为了提高程序的性能
    // JNI标准至少要保证本地代码能工作在任何Java 虚拟机环境
    public static void main(String[] args) {
        //读取图像
        opencv_core.IplImage srcImg = cvLoadImage("src/main/Rice.jpeg");
        if (srcImg != null) {
            //新建窗体，并命名
            cvNamedWindow("test");
        }
        //展示图像
        cvShowImage("test", srcImg);
        //等待按键，以执行下一步
        cvWaitKey(0);
        //释放图像空间
        cvReleaseImage(srcImg);
        //销毁窗体
        cvDestroyWindow("test");
    }
}
