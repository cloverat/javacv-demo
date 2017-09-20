import org.bytedeco.javacpp.opencv_core.CvMat;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;

public class JustDisplay {

    public static void main(String args[]) {

        IplImage rawImage = null;
        rawImage = cvLoadImage("src/main/Rice.jpeg");
        ShowImage(rawImage, "RawImage", 256);
    }

    public static void ShowImage(IplImage image, String caption, int size) {
        if (size < 128) size = 128;
        CvMat mat = image.asCvMat();
        int width = mat.cols();
        if (width < 1) width = 1;
        int height = mat.rows();
        if (height < 1) height = 1;
        double aspect = 1.0 * width / height;
        if (height != size) {
            height = size;
            width = (int) (height * aspect);
        }
        if (width != size) width = size;
        height = (int) (width / aspect);
        ShowImage(image, caption, width, height);
    }

    public static void ShowImage(IplImage image, String caption, int width, int height) {
        CanvasFrame canvas = new CanvasFrame(caption, 1);   // gamma=1
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.setCanvasSize(width, height);
        OpenCVFrameConverter converter = new OpenCVFrameConverter.ToIplImage();
        canvas.showImage(converter.convert(image));
    }
}
