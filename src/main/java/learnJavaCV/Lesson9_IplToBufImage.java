//package learnJavaCV;
//
//
//import org.bytedeco.javacpp.BytePointer;
//import org.bytedeco.javacpp.opencv_core.IplImage;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//import java.awt.image.WritableRaster;
//import java.io.File;
//
//import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
//
///**
// * 将javacv的IplImage图像转为java 2d自身的BufferedImage
// * <p>
// * 来源：http://blog.csdn.net/qiao_198911/article/details/52135013
// *
// * @author chenyujun
// * @date 17-9-20
// */
//public class Lesson9_IplToBufImage {
//
//    /**
//     * 功能说明:将javacv的IplImage图像转为java 2d自身的BufferedImage
//     *
//     * @param iplImage javacv图像
//     * @return BufferedImage
//     * java 2d图像
//     * @time:2016年8月3日下午12:03:05
//     * @author:linghushaoxia
//     * @exception:
//     */
//    public static BufferedImage iplToBufferedImage(IplImage iplImage) {
//        if (iplImage.height() > 0 && iplImage.width() > 0) {
//            BufferedImage image = new BufferedImage(iplImage.width(), iplImage.height(), BufferedImage
// .TYPE_3BYTE_BGR);
//            iplImage.(image);
//
//            return image;
//        }
//
//        return null;
//    }
//
//    /**
//     * 功能说明:将javacv的IplImage图像转为java 2d自身的BufferedImage
//     *
//     * @param mat javacv图像
//     * @return BufferedImage
//     * java 2d图像
//     * @time:2016年8月3日下午12:24:50
//     * @author:linghushaoxia
//     * @exception:
//     */
//    public static BufferedImage iplToBufImgData(IplImage mat) {
//        if (mat.height() > 0 && mat.width() > 0) {
//            BufferedImage image = new BufferedImage(mat.width(), mat.height(),
//                    BufferedImage.TYPE_3BYTE_BGR);
//            WritableRaster raster = image.getRaster();
//            DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
//            byte[] data = dataBuffer.getData();
//            BytePointer bytePointer = new BytePointer(data);
//            mat.imageData(bytePointer);
//
//            return image;
//        }
//
//        return null;
//    }
//
//    public static void main(String[] args) {
//        try {
//            // 读取
//            IplImage iplImage = cvLoadImage("src/main/a1.jpg");
//            // 转换
//            BufferedImage bufferedImage = iplToBufImgData(iplImage);
//            // 保存
//            ImageIO.write(bufferedImage, "jpg", new File("src/main/c1.jpg"));
//            System.out.println("保存成功。");
//        } catch (Exception e) {
//            System.out.println("转换数据类型出现异常");
//            e.printStackTrace();
//        }
//    }
//}
