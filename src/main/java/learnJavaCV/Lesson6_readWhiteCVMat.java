package learnJavaCV;

import org.bytedeco.javacpp.opencv_core.CvMat;

import static org.bytedeco.javacpp.opencv_core.*;

/**
 * 保存CvMat到xml文件，从xml文件读取数据，填充CvMat
 * <p>
 * 来源：http://blog.csdn.net/qiao_198911/article/details/37932547 学习javacv之六：保存CvMat到xml文件，从xml文件读取数据，填充CvMat
 *
 * @author chenyujun
 * @date 17-9-18
 */
public class Lesson6_readWhiteCVMat {
    public static void main(String[] args) {
        //创建5X5的矩阵,元素类型是32位浮点型
        CvMat writeCvMat = cvCreateMat(5, 5, CV_32FC1);
        //设置第四行，第四列元素为4.0
        cvmSet(writeCvMat, 4, 4, 4.0);
        //设置第三行，第三列元素为3.0
        cvSetReal2D(writeCvMat, 3, 3, 3.0);
        //将矩阵保存到xml文件中
        cvSave("src/main/CvMat.xml", writeCvMat);
        //从XML文件中，读取数据，并填充到一个矩阵中
        CvMat readCvMat = new CvMat(cvLoad("src/main/CvMat.xml"));
        System.out.println(readCvMat.toString());
    }
}
