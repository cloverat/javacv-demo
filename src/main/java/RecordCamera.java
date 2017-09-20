import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.*;

import javax.swing.*;

/**
 * @author chenyujun
 * @date 17-9-17
 */
public class RecordCamera {

    /**
     * /opt/jvm/jdk1.8.0_131/bin/java -javaagent:/home/cloverat/soft/idea-IU/lib/idea_rt
     * .jar=40163:/home/cloverat/soft/idea-IU/bin -Dfile.encoding=UTF-8 -classpath
     * /opt/jvm/jdk1.8.0_131/jre/lib/charsets.jar:/opt/jvm/jdk1.8.0_131/jre/lib/deploy
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/cldrdata.jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/dnsns
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/jaccess.jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/jfxrt
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/localedata.jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/nashorn
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/sunec.jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/sunjce_provider
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/sunpkcs11.jar:/opt/jvm/jdk1.8.0_131/jre/lib/ext/zipfs
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/javaws.jar:/opt/jvm/jdk1.8.0_131/jre/lib/jce
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/jfr.jar:/opt/jvm/jdk1.8.0_131/jre/lib/jfxswt
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/jsse.jar:/opt/jvm/jdk1.8.0_131/jre/lib/management-agent
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/plugin.jar:/opt/jvm/jdk1.8.0_131/jre/lib/resources
     * .jar:/opt/jvm/jdk1.8.0_131/jre/lib/rt.jar:/home/cloverat/workspace/testjavacv/target/classes:/home/cloverat/
     * .m2/repository/org/bytedeco/javacv/1.3.3/javacv-1.3.3.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacv-platform/1.3.3/javacv-platform-1.3.3.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/opencv-platform/3.2.0-1.3/opencv-platform-3.2.0-1.3.jar:/home
     * /cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0-1.3-android-arm
     * .jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0-1.3-android
     * -x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0-1.3-linux
     * -x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0-1.3-linux
     * -x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0-1.3-linux
     * -armhf.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0-1.3-linux
     * -ppc64le.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0-1.3
     * -macosx-x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0
     * -1.3-windows-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3/opencv-3.2.0
     * -1.3-windows-x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/ffmpeg-platform/3.2.1-1.3
     * /ffmpeg-platform-3.2.1-1.3.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3-android-arm.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3-android-x86.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3-linux-x86.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3-linux-x86_64.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3-linux-armhf.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3-linux-ppc64le
     * .jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3-macosx
     * -x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3
     * -windows-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.2.1-1.3/ffmpeg-3.2.1-1.3
     * -windows-x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flycapture-platform/2.9.3.43
     * -1.3/flycapture-platform-2.9.3.43-1.3.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/flycapture/2.9.3.43-1.3/flycapture-2.9.3.43-1.3-linux-x86.jar
     * :/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flycapture/2.9.3.43-1.3/flycapture-2.9.3.43-1.3
     * -linux-x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flycapture/2.9.3.43-1.3
     * /flycapture-2.9.3.43-1.3-linux-armhf.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/flycapture/2.9.3.43-1.3/flycapture-2.9.3.43-1.3-windows-x86.jar
     * :/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flycapture/2.9.3.43-1.3/flycapture-2.9.3.43-1.3
     * -windows-x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libdc1394-platform/2.2.4-1.3
     * /libdc1394-platform-2.2.4-1.3.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.4-1.3/libdc1394-2.2.4-1.3-linux-x86.jar:/home
     * /cloverat/.m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.4-1.3/libdc1394-2.2.4-1.3-linux-x86_64.jar
     * :/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.4-1.3/libdc1394-2.2.4-1.3-linux
     * -armhf.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.4-1.3/libdc1394-2.2.4-1.3
     * -linux-ppc64le.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.4-1.3/libdc1394
     * -2.2.4-1.3-macosx-x86_64.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.4-1.3/libdc1394-2.2.4-1.3-windows-x86.jar:/home
     * /cloverat/.m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.4-1.3/libdc1394-2.2.4-1.3-windows
     * -x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect-platform/0.5.3-1.3
     * /libfreenect-platform-0.5.3-1.3.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3/libfreenect-0.5.3-1.3-linux-x86.jar:/home
     * /cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3/libfreenect-0.5.3-1.3-linux
     * -x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3/libfreenect-0.5.3
     * -1.3-linux-armhf.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3
     * /libfreenect-0.5.3-1.3-linux-ppc64le.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3/libfreenect-0.5.3-1.3-macosx-x86_64.jar
     * :/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3/libfreenect-0.5.3-1.3
     * -windows-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3/libfreenect
     * -0.5.3-1.3-windows-x86_64.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libfreenect2-platform/0.2.0-1.3/libfreenect2-platform-0.2.0
     * -1.3.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect2/0.2.0-1.3/libfreenect2-0.2.0
     * -1.3-linux-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/libfreenect2/0.2.0-1.3
     * /libfreenect2-0.2.0-1.3-linux-x86_64.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/librealsense-platform/1.9.6-1.3/librealsense-platform-1.9.6
     * -1.3.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/librealsense/1.9.6-1.3/librealsense-1.9.6
     * -1.3-linux-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/librealsense/1.9.6-1.3
     * /librealsense-1.9.6-1.3-linux-x86_64.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/videoinput-platform/0.200-1.3/videoinput-platform-0.200-1.3.jar
     * :/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/videoinput/0.200-1.3/videoinput-0.200-1.3-windows
     * -x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/videoinput/0.200-1.3/videoinput-0.200-1.3
     * -windows-x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/artoolkitplus-platform/2.3.1
     * -1.3/artoolkitplus-platform-2.3.1-1.3.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3/artoolkitplus-2.3.1-1.3-android-arm
     * .jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3/artoolkitplus-2.3.1
     * -1.3-android-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3
     * /artoolkitplus-2.3.1-1.3-linux-x86.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3/artoolkitplus-2.3.1-1.3-linux-x86_64.jar
     * :/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3/artoolkitplus-2.3.1-1.3
     * -linux-armhf.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3
     * /artoolkitplus-2.3.1-1.3-linux-ppc64le.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3/artoolkitplus-2.3.1-1.3-macosx-x86_64.jar
     * :/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3/artoolkitplus-2.3.1-1.3
     * -windows-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3
     * /artoolkitplus-2.3.1-1.3-windows-x86_64.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/flandmark-platform/1.07-1.3/flandmark-platform-1.07-1.3.jar:/home
     * /cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07-1.3-android-arm
     * .jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07-1.3-android
     * -x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07-1.3
     * -linux-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07
     * -1.3-linux-x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark
     * -1.07-1.3-linux-armhf.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07-1.3-linux-ppc64le
     * .jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07-1.3-macosx
     * -x86_64.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07-1.3
     * -windows-x86.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3/flandmark-1.07
     * -1.3-windows-x86_64.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp/1.3.4-SNAPSHOT/javacpp-1.3.4-20170916.080924-4.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/opencv/3.2.0-1.3.4-SNAPSHOT/opencv-3.2.0-1.3.4-20170917.124634
     * -151.jar:/home/cloverat/.m2/repository/org/bytedeco/javacpp-presets/ffmpeg/3.3.2-1.3.4-SNAPSHOT/ffmpeg-3.3.2
     * -1.3.4-20170917.141659-211.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/flycapture/2.11.3.121-1.3.4-SNAPSHOT/flycapture-2.11.3.121-1.3.4
     * -20170917.140823-199.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libdc1394/2.2.5-1.3.4-SNAPSHOT/libdc1394-2.2.5-1.3.4
     * -20170917.141545-209.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libfreenect/0.5.3-1.3.4-SNAPSHOT/libfreenect-0.5.3-1.3.4
     * -20170917.142241-207.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/libfreenect2/0.2.0-1.3.4-SNAPSHOT/libfreenect2-0.2.0-1.3.4
     * -20170917.142438-204.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/librealsense/1.9.6-1.3.4-SNAPSHOT/librealsense-1.9.6-1.3.4
     * -20170917.143019-203.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/videoinput/0.200-1.3.4-SNAPSHOT/videoinput-0.200-1.3.4
     * -20170917.143117-205.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/artoolkitplus/2.3.1-1.3.4-SNAPSHOT/artoolkitplus-2.3.1-1.3.4
     * -20170917.143201-206.jar:/home/cloverat/
     * .m2/repository/org/bytedeco/javacpp-presets/flandmark/1.07-1.3.4-SNAPSHOT/flandmark-1.07-1.3.4-20170917.124646
     * -152.jar:/home/cloverat/.m2/repository/com/google/android/android/4.1.1.4/android-4.1.1.4.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt-main/2.3.1/gluegen-rt-main-2.3.1.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-android-aarch64.jar:/home/cloverat
     * /.m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-android-armv6.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-linux-amd64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-linux-armv6.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-linux-armv6hf.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-linux-i586.jar:/home/cloverat/
     * .m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-macosx-universal
     * .jar:/home/cloverat/.m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-solaris
     * -amd64.jar:/home/cloverat/.m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-solaris
     * -i586.jar:/home/cloverat/.m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-windows
     * -amd64.jar:/home/cloverat/.m2/repository/org/jogamp/gluegen/gluegen-rt/2.3.1/gluegen-rt-2.3.1-natives-windows
     * -i586.jar:/home/cloverat/.m2/repository/org/jogamp/jogl/jogl-all-main/2.3.1/jogl-all-main-2.3.1.jar:/home
     * /cloverat/.m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-android-aarch64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-android-armv6.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-linux-amd64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-linux-armv6.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-linux-armv6hf.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-linux-i586.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-macosx-universal.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-solaris-amd64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-solaris-i586.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-windows-amd64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jogl/jogl-all/2.3.1/jogl-all-2.3.1-natives-windows-i586.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl-main/2.3.1/jocl-main-2.3.1.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-android-aarch64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-android-armv6.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-linux-amd64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-linux-armv6.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-linux-armv6hf.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-linux-i586.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-macosx-universal.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-solaris-amd64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-solaris-i586.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-windows-amd64.jar:/home/cloverat/
     * .m2/repository/org/jogamp/jocl/jocl/2.3.1/jocl-2.3.1-natives-windows-i586.jar RecordCamera
     RealSense devices found: 0
     (1) Could not find the CLEyeMulticam.dll
     (2) Could not find the CLEyeMulticam.dll

     Process finished with exit code 0
     */

    /**
     * 按帧录制本机摄像头视频（边预览边录制，停止预览即停止录制）
     *
     * @param outputFile -录制的文件路径，也可以是rtsp或者rtmp等流媒体服务器发布地址
     * @param frameRate  - 视频帧率
     * @throws Exception
     * @throws InterruptedException
     * @throws FrameRecorder.Exception
     */
    public static void recordCamera(String outputFile, double frameRate)
            throws Exception, InterruptedException, FrameRecorder.Exception {
        Loader.load(opencv_objdetect.class);
        FrameGrabber grabber = FrameGrabber.createDefault(0);//本机摄像头默认0，这里使用javacv的抓取器，至于使用的是ffmpeg还是opencv，请自行查看源码
        grabber.start();//开启抓取器

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();//转换器
        opencv_core.IplImage grabbedImage = converter.convert(grabber.grab());
        //抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
        int width = grabbedImage.width();
        int height = grabbedImage.height();

        FrameRecorder recorder = FrameRecorder.createDefault(outputFile, width, height);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264，编码
        recorder.setFormat("flv");//封装格式，如果是推送到rtmp就必须是flv封装格式
        recorder.setFrameRate(frameRate);

        recorder.start();//开启录制器
        long startTime = 0;
        long videoTS = 0;
        CanvasFrame frame = new CanvasFrame("camera", CanvasFrame.getDefaultGamma() / grabber.getGamma());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        Frame rotatedFrame = converter.convert(grabbedImage);//不知道为什么这里不做转换就不能推到rtmp
        while (frame.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null) {
            rotatedFrame = converter.convert(grabbedImage);
            frame.showImage(rotatedFrame);
            if (startTime == 0) {
                startTime = System.currentTimeMillis();
            }
            videoTS = 1000 * (System.currentTimeMillis() - startTime);
            recorder.setTimestamp(videoTS);
            recorder.record(rotatedFrame);
            Thread.sleep(40);
        }
        frame.dispose();
        recorder.stop();
        recorder.release();
        grabber.stop();
    }

    public static void main(String[] args) throws Exception, InterruptedException, FrameRecorder
            .Exception {
        recordCamera("output.mp4", 25);
    }

    //public static void main(String[] args) throws Exception, InterruptedException, org.bytedeco.javacv
    // .FrameRecorder.Exception {
    //    recordCamera("rtmp://localhost/live/record1",25);
    //}

}
