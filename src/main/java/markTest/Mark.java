package markTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author chenyujun
 * @date 17-9-20
 */
public class Mark {
    public static void main(String[] args) throws IOException {
        Font font = new Font("宋体", Font.PLAIN, 14);
        // 原图位置, 输出图片位置, 水印文字颜色, 水印文字
        new MarkUtil().mark("eguidMarkText2.jpg", "eguidMarkText2.jpg", "水印效果测试", font, Color.ORANGE, 0, 14);
        // 增加图片水印
        new MarkUtil().mark("src/main/a1.jpg", "src/main/b1.jpg", "src/main/e1.jpg", 40, 20, 0, 14);
    }
}
