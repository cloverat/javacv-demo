//package learnJavaCV;
//
//import org.bytedeco.javacpp.opencv_core.CvSize;
//import org.bytedeco.javacpp.opencv_core.IplImage;
//import org.bytedeco.javacpp.opencv_highgui.CvCapture;
//
//import static org.bytedeco.javacpp.opencv_highgui.*;
//import static org.bytedeco.javacpp.opencv_core.*;
//import static org.bytedeco.javacpp.opencv_imgproc.*;
//import static org.bytedeco.javacpp.opencv_videoio.CV_CAP_PROP_FPS;
//
///**
// * @author chenyujun
// * @date 17-9-18
// */
//public class lesson4_readWhiteVideo {
//    public static void main(String[] args) {
//        //新建文件捕获器
//        CvCapture cvCapture = cvCreateFileCapture("resources/beautiful.avi");
//        if (cvCapture != null) {
//            //获取视频的帧率
//            double fps = cvGetCaptureProperty(cvCapture, CV_CAP_PROP_FPS);
//            //设置矩阵框的尺寸
//            CvSize cvSize = cvSize(
//                    (int) cvGetCaptureProperty(cvCapture, CV_CAP_PROP_FRAME_HEIGHT),
//                    (int) cvGetCaptureProperty(cvCapture, CV_CAP_PROP_FRAME_WIDTH)
//            );
//            //初始化视频输出
//            CvVideoWriter cvVideoWriter = cvCreateVideoWriter(
//                    //输出视频文件名，文件路径
//                    "resources/beautifulOut.avi",
//                    //编码格式：
//                    CV_FOURCC((byte) 'M', (byte) 'J', (byte) 'P', (byte) 'G'),
//                    //帧率
//                    fps,
//                    //帧尺寸
//                    cvSize);
//            //创建视频输出帧的图像大小、位深度、通道数
//            IplImage polarImage = cvCreateImage(cvSize, IPL_DEPTH_8U, 3);
//            IplImage bgrImage;
//            while ((bgrImage = cvQueryFrame(cvCapture)) != null) {
//                //对每帧图像进行处理
//                cvLogPolar(
//                        //原始图像
//                        bgrImage,
//                        //输出图像
//                        polarImage,
//                        //以图像中心为原点进行变换
//                        cvPoint2D32f(bgrImage.width() / 2, bgrImage.height() / 2),
//                        //缩放比例100
//                        100,
//                        //
//                        CV_INTER_LINEAR | CV_WARP_FILL_OUTLIERS | CV_WARP_INVERSE_MAP);
//                //将每一帧图像保存到视频流中
//                cvWriteFrame(cvVideoWriter, polarImage);
//            }
//            //释放资源
//            cvReleaseVideoWriter(cvVideoWriter);
//            cvReleaseImage(polarImage);
//            cvReleaseCapture(cvCapture);
//        }
//
//
//    }
//}
