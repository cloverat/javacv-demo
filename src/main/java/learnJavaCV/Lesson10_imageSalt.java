package learnJavaCV;

import static org.bytedeco.javacpp.opencv_imgcodecs.IMREAD_COLOR;

import java.util.Random;

import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;

/**
 * 对图片添加噪点(加盐处理)
 * <p>
 * 来源：http://blog.csdn.net/qiao_198911/article/details/51194237
 *
 * @author chenyujun
 * @date 17-9-20
 */
public class Lesson10_imageSalt {

    public static void main(String[] args) {
        //图片路径
        String filePath = "src/main/a1.jpg";
        // 以彩色模式读取图像
        Mat image = Lesson8_JavaCVUtil.imRead(filePath, IMREAD_COLOR);
        //原始图像
        Lesson8_JavaCVUtil.imShow(image, "原始图片");
        //对图像加盐
        Mat dest = salt(image, 2000);
        // 显示图像
        Lesson8_JavaCVUtil.imShow(dest, "加盐处理");
    }

    /**
     * 功能说明:对图片加盐，添加噪点
     *
     * @param image 原始图片
     * @param n     噪点数量
     * @return Mat
     * @time:2016年4月19日下午8:40:27
     * @author:linghushaoxia
     * @exception:
     */
    public static Mat salt(Mat image, int n) {
        // 随机数生成对象
        Random random = new Random();
        /**
         * 无符号字节索引，访问Mat结构的元素
         * 访问Mat高效便捷
         */
        UByteIndexer indexer = image.createIndexer();
        //图像通道
        int nbChannels = image.channels();
        //加盐数量
        for (int i = 0; i < n; i++) {
            /**
             * 获取随机行、列
             * 噪点随机分布
             */
            int row = random.nextInt(image.rows());
            int col = random.nextInt(image.cols());
            //处理全部通道数据，均进行加盐，设置为最大值255
            for (int channel = 0; channel < nbChannels; channel++) {
                indexer.put(row, col, channel, 255);
            }
        }
        return image;
    }
}
