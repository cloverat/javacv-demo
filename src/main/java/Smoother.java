import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;

public class Smoother {
    public static void smooth(String filename) {
        IplImage image = cvLoadImage(filename);
        if (image != null) {
            cvSmooth(image, image);
            cvSaveImage(filename, image);
            cvReleaseImage(image);
        }
    }

    public static void main(String[] args){
        smooth("src/main/Rice.jpeg");
    }
}
