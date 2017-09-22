package KTV;

import org.bytedeco.javacpp.opencv_core;

import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.CV_8U;
import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * @author chenyujun
 * @date 17-9-22
 */
public class PickupLetter {

    private opencv_core.Mat preprocess(opencv_core.Mat gray) {
        //1.Sobel算子，x方向求梯度
        opencv_core.Mat sobel = new opencv_core.Mat();
        //Sobel(gray, sobel, CV_8U, 1, 0, 3);
        Sobel(gray, sobel, CV_8U, 1, 0);

        //2.二值化
        opencv_core.Mat binary = new opencv_core.Mat();
        threshold(sobel, binary, 0, 255, THRESH_OTSU + THRESH_BINARY);

        //3.膨胀和腐蚀操作核设定
        // TODO size的定义对不对
        opencv_core.Size size1 = new opencv_core.Size();
        size1.height(30);
        size1.width(9);

        opencv_core.Mat element1 = getStructuringElement(MORPH_RECT, size1);
        //控制高度设置可以控制上下行的膨胀程度，例如3比4的区分能力更强,但也会造成漏检

        opencv_core.Size size2 = new opencv_core.Size();
        size2.height(24);
        size2.width(4);
        opencv_core.Mat element2 = getStructuringElement(MORPH_RECT, size2);

        //4.膨胀一次，让轮廓突出
        opencv_core.Mat dilate1 = new opencv_core.Mat();
        dilate(binary, dilate1, element2);

        //5.腐蚀一次，去掉细节，表格线等。这里去掉的是竖直的线
        opencv_core.Mat erode1 = new opencv_core.Mat();
        erode(dilate1, erode1, element1);

        //6.再次膨胀，让轮廓明显一些
        opencv_core.Mat dilate2 = new opencv_core.Mat();
        dilate(erode1, dilate2, element2);

        //7.存储中间图片
        imwrite("binary.jpg", binary);
        imwrite("dilate1.jpg", dilate1);
        imwrite("erode1.jpg", erode1);
        imwrite("dilate2.jpg", dilate2);

        return dilate2;
    }


    List<opencv_core.RotatedRect> findTextRegion(opencv_core.Mat img) {
        List<opencv_core.RotatedRect> rects = new ArrayList<>();
        //1.查找轮廓
        opencv_core.MatVector contours = new opencv_core.MatVector();

        //List<Vec4i> hierarchy;
        opencv_core.Mat hierarchy = new opencv_core.Mat();
        opencv_core.Point point = new opencv_core.Point();
        point.x(0);
        point.y(0);
        findContours(img, contours, hierarchy, RETR_CCOMP, CHAIN_APPROX_SIMPLE, point);

        //2.筛选那些面积小的
        for (int i = 0; i < contours.size(); i++) {
            //计算当前轮廓的面积
            double area = contourArea(contours.get(i));

            //面积小于1000的全部筛选掉
            if (area < 1000)
                continue;

            //轮廓近似，作用较小，approxPolyDP函数有待研究
            double epsilon = 0.001 * arcLength(contours.get(i), true);
            opencv_core.Mat approx = new opencv_core.Mat();
            approxPolyDP(contours.get(i), approx, epsilon, true);

            //找到最小矩形，该矩形可能有方向
            opencv_core.RotatedRect rect = minAreaRect(contours.get(i));

            //计算高和宽
            int m_width = rect.boundingRect().width();
            int m_height = rect.boundingRect().height();

            //筛选那些太细的矩形，留下扁的
            if (m_height > m_width * 1.2)
                continue;

            //符合条件的rect添加到rects集合中
            rects.add(rect);
        }

        return rects;
    }

    void detect(opencv_core.Mat img) {
        //1.转化成灰度图
        opencv_core.Mat gray = new opencv_core.Mat();
        cvtColor(img, gray, CV_BGR2GRAY);

        //2.形态学变换的预处理，得到可以查找矩形的轮廓
        opencv_core.Mat dilation = preprocess(gray);

        //3.查找和筛选文字区域
        List<opencv_core.RotatedRect> rects = findTextRegion(dilation);

        //4.用绿线画出这些找到的轮廓
        for (opencv_core.RotatedRect rect : rects) {
            //opencv_core.Point[] toArray = new opencv_core.Point[4];

            opencv_core.Point2f p = new opencv_core.Point2f();
            rect.points(p);

            for (int j = 0; j <= 3; j++) {
                opencv_core.Scalar scalar = new opencv_core.Scalar();
                scalar.put(0);
                scalar.put(255);
                scalar.put(0);

                opencv_core.Point2f p2fj = p.position(j);
                opencv_core.Point a = new opencv_core.Point();
                a.x((int) p2fj.x());
                a.y((int) p2fj.y());
                //P[j], P[(j + 1) % 4]

                opencv_core.Point2f p2fj1 = p.position((j + 1) % 4);
                opencv_core.Point b = new opencv_core.Point();
                b.x((int) p2fj1.x());
                b.y((int) p2fj1.y());
                //P[j], P[(j + 1) % 4]

                //line(img, a, b, scalar, 2);
                line(img, a, b, scalar);
            }
        }

        //5.显示带轮廓的图像
        imshow("img", img);
        //imwrite("imgDrawRect.jpg", img);

        waitKey(0);
    }


    //private 
    //
    //
    //
    //def preprocess(gray):
    //        # 1. Sobel算子，x方向求梯度
    //        sobel = cv2.Sobel(gray, cv2.CV_8U, 1, 0, ksize = 3)
    //# 2. 二值化
    //        ret, binary = cv2.threshold(sobel, 0, 255, cv2.THRESH_OTSU+cv2.THRESH_BINARY)
    //
    //# 3. 膨胀和腐蚀操作的核函数
    //        element1 = cv2.getStructuringElement(cv2.MORPH_RECT, (30, 9))
    //element2 = cv2.getStructuringElement(cv2.MORPH_RECT, (24, 6))
    //
    //        # 4. 膨胀一次，让轮廓突出
    //        dilation = cv2.dilate(binary, element2, iterations = 1)
    //
    //# 5. 腐蚀一次，去掉细节，如表格线等。注意这里去掉的是竖直的线
    //        erosion = cv2.erode(dilation, element1, iterations = 1)
    //
    //# 6. 再次膨胀，让轮廓明显一些
    //        dilation2 = cv2.dilate(erosion, element2, iterations = 3)
    //
    //# 7. 存储中间图片 
    //cv2.imwrite("binary.png", binary)
    //        cv2.imwrite("dilation.png", dilation)
    //        cv2.imwrite("erosion.png", erosion)
    //        cv2.imwrite("dilation2.png", dilation2)
    //
    //        return dilation2
    //
    //
    //def findTextRegion(img):
    //region = []
    //
    //        # 1. 查找轮廓
    //        contours, hierarchy = cv2.findContours(img, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    //
    //# 2. 筛选那些面积小的
    //for i in range(len(contours)):
    //cnt = contours[i]
    //        # 计算该轮廓的面积
    //        area = cv2.contourArea(cnt) 
    //
    //    # 面积小的都筛选掉
    //    if(area < 1000):
    //        continue
    //
    //        # 轮廓近似，作用很小
    //        epsilon = 0.001 * cv2.arcLength(cnt, True)
    //approx = cv2.approxPolyDP(cnt, epsilon, True)
    //
    //        # 找到最小的矩形，该矩形可能有方向
    //        rect = cv2.minAreaRect(cnt)
    //print "rect is: "
    //print rect
    //
    //    # box是四个点的坐标
    //        box = cv2.cv.BoxPoints(rect)
    //box = np.int0(box)
    //
    //        # 计算高和宽
    //        height = abs(box[0][1] - box[2][1])
    //width = abs(box[0][0] - box[2][0])
    //
    //    # 筛选那些太细的矩形，留下扁的
    //    if(height > width * 1.2):
    //        continue
    //
    //        region.append(box)
    //
    //        return region
    //
    //
    //def detect(img):
    //        # 1.  转化成灰度图
    //        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    //
    //# 2. 形态学变换的预处理，得到可以查找矩形的图片
    //        dilation = preprocess(gray)
    //
    //# 3. 查找和筛选文字区域
    //        region = findTextRegion(dilation)
    //
    //# 4. 用绿线画出这些找到的轮廓
    //for box in region:
    //        cv2.drawContours(img, [box], 0, (0, 255, 0), 2)
    //
    //        cv2.namedWindow("img", cv2.WINDOW_NORMAL)
    //        cv2.imshow("img", img)
    //
    //        # 带轮廓的图片
    //cv2.imwrite("contours.png", img)
    //
    //        cv2.waitKey(0)
    //        cv2.destroyAllWindows()
    //
    //
    //        if __name__ == '__main__':
    //        # 读取文件
    //        imagePath = sys.argv[1]
    //img = cv2.imread(imagePath)
    //detect(img)
}
