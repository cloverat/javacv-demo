package leanrnJavaCV2;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.swing.*;

/**
 * 调用本地摄像头窗口视频
 * <p>
 * 来源: http://blog.csdn.net/jiejie11080/article/details/54579430
 *
 * @author chenyujun
 * @date 17-9-20
 */
public class Lesson1_runCamera {

    public static void main(String[] args) throws Exception, InterruptedException {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();   // 开始获取摄像头数据
        CanvasFrame canvas = new CanvasFrame("摄像头");// 新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);

        while (true) {
            if (!canvas.isDisplayable()) {// 窗口是否关闭
                grabber.stop();// 停止抓取
                System.exit(2);// 退出
            }
            canvas.showImage(grabber.grab());// 获取摄像头图像并放到窗口上显示
            // 这里的Frame frame=grabber.grab(); frame是一帧视频图像

            Thread.sleep(50);// 50毫秒刷新一次图像
        }
    }
}
