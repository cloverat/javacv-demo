package learnJavaCV;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_highgui;

import static org.bytedeco.javacpp.opencv_core.cvRect;
import static org.bytedeco.javacpp.opencv_highgui.CV_EVENT_LBUTTONDOWN;
import static org.bytedeco.javacpp.opencv_highgui.CV_EVENT_LBUTTONUP;
import static org.bytedeco.javacpp.opencv_highgui.CV_EVENT_MOUSEMOVE;

/**
 * <p>
 * 来源：http://blog.csdn.net/qiao_198911/article/details/37932417
 *
 * @author chenyujun
 * @date 17-9-18
 */
public class Lesson7_JCVMouseCallBack extends opencv_highgui.CvMouseCallback {
    /**
     * @功能说明：重写call(...)方法,实现函数回调
     * @time:2014年7月18日下午4:30:06
     * @version:1.0
     */

    //初始化box
    opencv_core.CvRect box = cvRect(-1, -1, 0, 0);
    //是否在画图的标识
    boolean drawBox = false;

    @Override
        /*
         * 参数说明(non-Javadoc)
         * @see org.bytedeco.javacpp.opencv_highgui.CvMouseCallback#call(int, int, int, int, org.bytedeco.javacpp
         * .Pointer)
         * envent:鼠标事件代码0(鼠标移动),1(左键按下),4(左键放开),还有别的。
         * x:鼠标在画布上的x坐标
         * y:鼠标在画布上的y坐标
         * flags:是否有鼠标事件
         */
    public void call(int event, int x, int y, int flags, Pointer pointer) {
        //处理鼠标事件
        switch (event) {
            //鼠标左键按下
            case CV_EVENT_LBUTTONDOWN: {
                drawBox = true;
                //以鼠标按下的点为左上角的定点，在画布上画矩形
                box = cvRect(x, y, 0, 0);
            }
            break;
            //鼠标移动
            case CV_EVENT_MOUSEMOVE: {
                //鼠标左键被按下,开始画图
                if (drawBox) {
                    box.width(x - box.x());
                    box.height(y - box.y());
                }
            }
            break;
            //鼠标左键放开
            case CV_EVENT_LBUTTONUP: {
                drawBox = false;
            }
            break;
        }
    }

    public opencv_core.CvRect getBox() {
        return box;
    }

    public void setBox(opencv_core.CvRect box) {
        this.box = box;
    }

    public boolean isDrawBox() {
        return drawBox;
    }

    public void setDrawBox(boolean drawBox) {
        this.drawBox = drawBox;
    }
}
