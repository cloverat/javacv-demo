//import org.bytedeco.javacpp.opencv_core;
//import org.bytedeco.javacv.OpenCVFrameGrabber;
//
//import java.awt.*;
//
//import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
//import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_DO_CANNY_PRUNING;
//import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_DO_ROUGH_SEARCH;
//
///**
// * @author chenyujun
// * @date 17-9-18
// */
//public class Face {
//    public boolean recognizeFromCam() throws Exception //从摄像头中识别人脸
//    {
//        OpenCVFrameGrabber grabber = null;
//        FileDialog fd;
//        opencv_core.IplImage pFrame = null;
//        int keypress = 0;
//        int count = 0;
//        if (LoginShell.video_flag)//如果被选，则用视频文件
//        {
//            fd = new FileDialog(LoginShell.sShell, SWT.OPEN);
//            fd.setFilterExtensions(new String[]{"*.*"});
//            fd.setFilterNames(new String[]{".avi", ".wmv", ".mp4"});
//            String filename = fd.open();
//            grabber = OpenCVFrameGrabber.createDefault(filename);
//        } else
//            grabber = OpenCVFrameGrabber.createDefault(0);
//        grabber.start();
//        pFrame = grabber.grab();
//        while (pFrame != null) {
//            //if(count==6){
//            detectAndCropAndPre(pFrame, cascade, CV_HAAR_DO_CANNY_PRUNING | CV_HAAR_DO_ROUGH_SEARCH);
//            //  count=0;
//            //}
//            cvShowImage("Press 'Esc' to Stop!", pFrame);
//            pFrame = grabber.grab();
//            keypress = cvWaitKey(24);
//            System.out.println(g_confidence);
//            grabber.getTimeout();
//            if (keypress == 27) {
//                grabber.release();
//                cvDestroyWindow("Press 'Esc' to Stop!");
//                break;
//            }
//            //count++;
//        }
//        cvReleaseImage(pFrame);
//        //cvDestroyWindow("BP_FaceRecognizer_FaceLogin");
//        cvClearMemStorage(storage);
//        // grabber.stop();
//        grabber.release();
//        return false;
//    }
////TODO 2
//
//    public void recongizeFormImage(String filePath, String fileNames[]) {//从图片中识别人脸
//        IplImage signleImage = null;
//        //System.out.println(filePath);
//        //signleImage=cvLoadImage(filePath);
//        //if(!signleImage.isNull());
//        detectAndCropFromImg(cascade, CV_HAAR_DO_CANNY_PRUNING | CV_HAAR_DO_ROUGH_SEARCH, fileNames, filePath);
//        //cvNamedWindow("Press 'Esc' to exit");
//
//
//    }
//
//    //TODO 3
//    private void detectForRegister(IplImage src, CvHaarClassifierCascade cascade, int flag, String name) {
////从摄像头获取五张人脸注册
//        IplImage greyImg = null;
//        IplImage faceImg = null;
//        IplImage sizedImg = null;
//        IplImage equalizedImg = null;
//        // int countForResgister=0;//for pause to rejust facial expression
//
//
//        CvRect r;
//        CvFont font = new CvFont(CV_FONT_HERSHEY_COMPLEX_SMALL, 1, 1);
//        cvInitFont(font, CV_FONT_HERSHEY_COMPLEX_SMALL, 1.0, 0.8, 1, 1, CV_AA);
//        greyImg = cvCreateImage(cvGetSize(src), IPL_DEPTH_8U, 1);
//        greyImg = convertImageToGreyscale(src);
//        //  CvMemStorage storage = CvMemStorage.create();
//
//        CvSeq sign = cvHaarDetectObjects(
//                greyImg,
//                cascade,
//                storage,
//                1.1,
//                3,
//                flag);
//        cvClearMemStorage(storage);
//        if (sign.total() == 1)//只会有一个脸部
//        {
//            r = new CvRect(cvGetSeqElem(sign, 0));
//            faceImg = cropImage(greyImg, r);
//            sizedImg = resizeImage(faceImg);
//            equalizedImg = cvCreateImage(cvGetSize(sizedImg), 8, 1);
//            cvEqualizeHist(sizedImg, equalizedImg);
//            cvRectangle(
//                    src,
//                    cvPoint(r.x(), r.y()),
//                    cvPoint(r.width() + r.x(), r.height() + r.y()),
//                    CvScalar.WHITE,
//                    1,
//                    CV_AA,
//                    0);
//            cvPutText(src, "Need 5 photos", cvPoint(r.x() + 30, r.y() - 50), font, CvScalar.GREEN);
//            cvPutText(src, "This is No." + String.valueOf(countSavedFace) + " photos. ", cvPoint(r.x() + 30, r.y() -
//                    30), font, CvScalar.GREEN);
//            if (countForResgister == 10) {
//                cvSaveImage("img\\" + name + countSavedFace + ".jpg", equalizedImg);
//                countSavedFace++;
//                countForResgister = 0;
//                cvWaitKey(800);
//            }
//            countForResgister++;
//            cvReleaseImage(greyImg);
//            cvReleaseImage(faceImg);
//            cvReleaseImage(sizedImg);
//            cvReleaseImage(equalizedImg);
//        }
//
//        //TODO 4
//        private void detectAndCropAndPre (IplImage src, CvHaarClassifierCascade cascade,int flag)//从一帧图像中获取脸部并识别
//        {
//            int nearest = 0;
//            IplImage greyImg = null;
//            IplImage faceImg = null;
//            IplImage sizedImg = null;
//            IplImage equalizedImg = null;
//            boolean faceIsTrue = false;
//            CvRect r;
//            CvFont font = new CvFont(CV_FONT_HERSHEY_COMPLEX_SMALL, 1, 1);
//            cvInitFont(font, CV_FONT_HERSHEY_COMPLEX_SMALL, 1.0, 0.8, 1, 1, CV_AA);
//            greyImg = cvCreateImage(cvGetSize(src), IPL_DEPTH_8U, 1);
//            greyImg = convertImageToGreyscale(src);
//            //CvMemStorage storage = CvMemStorage.create();
//
//            CvSeq sign = cvHaarDetectObjects(
//                    greyImg,
//                    cascade,
//                    storage,
//                    1.5,
//                    3,
//                    flag);
//            //cvClearMemStorage(storage);
//            if (sign.total() > 0) {
//
//                for (int i = 0; i < sign.total(); i++) {
//                    r = new CvRect(cvGetSeqElem(sign, i));
//                    faceImg = cropImage(greyImg, r);
//                    sizedImg = resizeImage(faceImg);
//                    if (i == 0)
//                        equalizedImg = cvCreateImage(cvGetSize(sizedImg), 8, 1);
//                    cvEqualizeHist(sizedImg, equalizedImg);
//                    cvRectangle(
//                            src,
//                            cvPoint(r.x(), r.y()),
//                            cvPoint(r.width() + r.x(), r.height() + r.y()),
//                            CvScalar.WHITE,
//                            1,
//                            CV_AA,
//                            0);
//
//                    eigenDecomImg(equalizedImg);
//                    if (g_confidence * 100 > 50) {
//                        cvPutText(src, textName, cvPoint(r.x() - 10, r.y() + r.height() + 20), font, CvScalar.WHITE);
//                        cvPutText(src, " conf=" + Integer.valueOf((int) (g_confidence * 100)) + "%", cvPoint(r.x() -
//                                10, r.y() + r.height() + 40), font, CvScalar.GREEN);
//                        textName = "unknow";
//                    } else {
//                        cvPutText(src, "unknow", cvPoint(r.x() - 10, r.y() + r.height() + 20), font, CvScalar.WHITE);
//                        cvPutText(src, " conf=" + Integer.valueOf((int) (g_confidence * 100)) + "%", cvPoint(r.x() -
//                                10, r.y() + r.height() + 40), font, CvScalar.GREEN);
//                    }
//                }
//                cvReleaseImage(greyImg);
//                cvReleaseImage(faceImg);
//                cvReleaseImage(sizedImg);
//                cvReleaseImage(equalizedImg);
//            }
//
//
//        }
//
//        private void detectAndCropFromImg (CvHaarClassifierCascade cascade,int flag, String fileNames[],String filePath)
//        {
//            CvRect r;
//            CvFont font = new CvFont(CV_FONT_HERSHEY_COMPLEX_SMALL, 1, 1);
//            cvInitFont(font, CV_FONT_HERSHEY_COMPLEX_SMALL, 1.0, 0.8, 1, 1, CV_AA);
//            int fileNum = 0;
//            fileNum = fileNames.length;
//            IplImage src[] = new IplImage[fileNum];
//            IplImage tempImg[] = new IplImage[fileNum];
//            IplImage greyImg[] = new IplImage[fileNum];
//            IplImage faceImg[] = new IplImage[fileNum];
//            IplImage sizedImg[] = new IplImage[fileNum];
//            IplImage equalizedImg[] = new IplImage[fileNum];
//
//            for (int i = 0; i < fileNum; i++) {
//
//                System.out.println(filePath + fileNames[i]);
//
//                src[i] = cvLoadImage(filePath + fileNames[i]);
//                if (src[i].width() > 3200 || src[i].height() > 2400) {
//                    tempImg[i] = cvCreateImage(new CvSize(src[i].width() / 4, src[i].height() / 4), IPL_DEPTH_8U, 1);
//                    tempImg[i] = resizeImage(src[i], src[i].width() / 4, src[i].height() / 4);
//
//                } else if (src[i].width() > 1600 || src[i].height() > 1200) {
//                    tempImg[i] = cvCreateImage(new CvSize(src[i].width() / 2, src[i].height() / 2), IPL_DEPTH_8U, 1);
//                    tempImg[i] = resizeImage(src[i], src[i].width() / 2, src[i].height() / 2);
//
//                } else {
//                    tempImg[i] = cvCreateImage(new CvSize(src[i].width(), src[i].height()), IPL_DEPTH_8U, 1);
//                    tempImg[i] = cvCloneImage(src[i]);
//                }
//                greyImg[i] = cvCreateImage(cvGetSize(tempImg[i]), IPL_DEPTH_8U, 1);
//                greyImg[i] = convertImageToGreyscale(tempImg[i]);
//
//
//                //CvMemStorage storage = CvMemStorage.create();
//
//                CvSeq sign = cvHaarDetectObjects(
//                        greyImg[i],
//                        cascade,
//                        storage,
//                        1.2,
//                        3,
//                        flag);
//                if (sign.total() > 0) {
//
//                    for (int j = 0; j < sign.total(); j++) {
//                        r = new CvRect(cvGetSeqElem(sign, j));
//
//                        if (j == 0) {
//                            faceImg[i] = cvCreateImage(new CvSize(r.width(), r.height()), IPL_DEPTH_8U, 1);
//                            sizedImg[i] = cvCreateImage(new CvSize(newWidth, newHeight), IPL_DEPTH_8U, 1);
//                            equalizedImg[i] = cvCreateImage(cvGetSize(sizedImg[i]), IPL_DEPTH_8U, 1);
//
//                        }
//                        faceImg[i] = cropImage(greyImg[i], r);
//                        sizedImg[i] = resizeImage(faceImg[i]);
//
//                        cvEqualizeHist(sizedImg[i], equalizedImg[i]);
//                        cvRectangle(
//                                tempImg[i],
//                                cvPoint(r.x(), r.y()),
//                                cvPoint(r.width() + r.x(), r.height() + r.y()),
//                                CvScalar.WHITE,
//                                1,
//                                CV_AA,
//                                0);
//
//                        eigenDecomImg(equalizedImg[i]);
//                        if (g_confidence * 100 > 50) {
//                            cvPutText(tempImg[i], textName, cvPoint(r.x() - 10, r.y() + r.height() + 20), font,
//                                    CvScalar.WHITE);
//                            cvPutText(tempImg[i], " conf=" + Integer.valueOf((int) (g_confidence * 100)) + "%",
//                                    cvPoint(r.x() - 10, r.y() + r.height() + 40), font, CvScalar.GREEN);
//                            textName = "unknow";
//                        } else {
//                            cvPutText(tempImg[i], "Unknow", cvPoint(r.x() - 10, r.y() + r.height() + 20), font,
//                                    CvScalar.WHITE);
//                            cvPutText(tempImg[i], " conf=" + Integer.valueOf((int) (g_confidence * 100)) + "%",
//                                    cvPoint(r.x() - 10, r.y() + r.height() + 40), font, CvScalar.GREEN);
//                        }
//                    }
//
//                    cvReleaseImage(faceImg[i]);
//                    cvReleaseImage(sizedImg[i]);
//                    cvReleaseImage(equalizedImg[i]);
//                } else
//                    cvPutText(tempImg[i], "can't find any face!", cvPoint(src[i].width() / 2, src[i].height() / 2),
//                            font, CvScalar.GREEN);
//
//                cvReleaseImage(greyImg[i]);
//                cvReleaseImage(src[i]);
//                cvShowImage("Press 'Esc' to exit  " + filePath + fileNames[i], tempImg[i]);
//                cvReleaseImage(tempImg[i]);
//            }
//            cvWaitKey(0);
//            cvClearMemStorage(storage);
//            cvDestroyAllWindows();
//
//        }
//
//        //TODO 5
//        private void eigenDecomImg (IplImage src){//人脸对比的核心方法
//            //CvMat trainPersonNumMat=null;
//            float confidence = 0.0f;
//            int nearest = 0;
//            int iNearest = 0;
//
//
//            LOGGER.info("=====================================Waiting For the camera .....");
//            //
//            if (trainPersonNumMat == null) {
//                LOGGER.info("ERROR in recognizeFromCam(): Couldn't load the training data!\n");
//                System.exit(1);
//            }
//            float[] projectedTestFace = new float[nEigens];
//            cvEigenDecomposite(
//                    src,
//                    nEigens,
//                    eigenVectArr,
//                    0,
//                    null,
//                    pAvgTrainImg,
//                    projectedTestFace);
//
//            final FloatPointer pConfidence = new FloatPointer(confidence);
//            iNearest = findNearestNeighbor(projectedTestFace, new FloatPointer(pConfidence));
//            confidence = pConfidence.get();
//            nearest = trainPersonNumMat.data_i().get(iNearest);
//            personName = "";
//            textName = personNames.get(nearest - 1);
//            personName = personNames.get(nearest - 1);
//            g_confidence = confidence;
//
//        }
//
//        //TODO 6
//        private void doPCA () {//人脸对比的核心方法
//
//            int i;
//            CvTermCriteria calcLimit;
//            CvSize faceImgSize = new CvSize();
//
//            // set the number of eigenvalues to use
//            nEigens = nTrainFaces - 1;
//
//            LOGGER.info("allocating images for principal component analysis, using " + nEigens + (nEigens == 1 ? " " +
//                    "eigenvalue" : " eigenvalues"));
//
//            // allocate the eigenvector images
//            faceImgSize.width(trainingFaceImgArr[0].width());
//            faceImgSize.height(trainingFaceImgArr[0].height());
//            eigenVectArr = new IplImage[nEigens];
//            for (i = 0; i < nEigens; i++) {
//                eigenVectArr[i] = cvCreateImage(
//                        faceImgSize, // size
//                        IPL_DEPTH_32F, // depth
//                        1); // channels
//            }
//
//            // allocate the eigenvalue array
//            eigenValMat = cvCreateMat(
//                    1, // rows
//                    nEigens, // cols
//                    CV_32FC1); // type, 32-bit float, 1 channel
//
//            // allocate the averaged image
//            pAvgTrainImg = cvCreateImage(
//                    faceImgSize, // size
//                    IPL_DEPTH_32F, // depth
//                    1); // channels
//
//            // set the PCA termination criterion
//            calcLimit = cvTermCriteria(
//                    CV_TERMCRIT_ITER, // type
//                    nEigens, // max_iter
//                    1); // epsilon
//
//            LOGGER.info("computing average image, eigenvalues and eigenvectors");
//            // compute average image, eigenvalues, and eigenvectors
//            cvCalcEigenObjects(
//                    nTrainFaces, // nObjects
//                    trainingFaceImgArr, // input
//                    eigenVectArr, // output
//                    CV_EIGOBJ_NO_CALLBACK, // ioFlags
//                    0, // ioBufSize
//                    null, // userData
//                    calcLimit,
//                    pAvgTrainImg, // avg
//                    eigenValMat.data_fl()); // eigVals
//
//            LOGGER.info("normalizing the eigenvectors");
//            cvNormalize(
//                    eigenValMat, // src (CvArr)
//                    eigenValMat, // dst (CvArr)
//                    1, // a
//                    0, // b
//                    CV_L1, // norm_type
//                    null); // mask
//        }
//
//
//    }
