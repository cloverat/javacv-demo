package KTV;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

/**
 * @author chenyujun
 * @date 17-9-22
 */
public class Enter {

    private static String IMAGEPATH = "src/main/lf1.jpg";//图像路径

    public static void main(String[] args) {
        // 读取图像
        opencv_core.Mat textImageSrc = imread(IMAGEPATH);
        PickupLetter p = new PickupLetter();
        p.detect(textImageSrc);

        return;
    }
}
