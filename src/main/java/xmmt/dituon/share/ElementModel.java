package xmmt.dituon.share;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ElementModel {
    private final ArrayList<BufferedImage> elementImageList = new ArrayList<>();

    public ElementModel(File eleDir) {
//        System.out.println(eleDir);
        try {
            ArrayList<BufferedImage> iconImageList = new ArrayList<>();
            ArrayList<File> iconFileList = new ArrayList<>();

            ElementConfig config = ElementConfig.getElementConfig(
                    BaseService.readFileString(eleDir.getAbsolutePath() + "/element.json"));

            for (IconImage icon : config.getIcon()) { //遍历icon
                File iconFile = new File(eleDir.getAbsolutePath() + '/' + icon.getImage());
                iconFileList.add(iconFile);
                iconImageList.add(ImageIO.read(iconFile));
            }

            for (File imageFile : eleDir.listFiles()) { //遍历卡片图片
                String fileName = imageFile.getName();
                if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") &&
                        !fileName.endsWith(".jpeg") && !iconFileList.contains(imageFile)) {
                    continue;
                }
                BufferedImage backgroundImage = ImageIO.read(imageFile);
                int[] pos = new int[]{64, 0, 18, 18}; //TODO 读配置文件

                iconImageList.forEach(icon -> {
                    ImageSynthesis.synthesisImage(backgroundImage, icon, pos); //合成图片
                });

                elementImageList.add(backgroundImage);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public BufferedImage getRandomImage() { //随机元素图片
        return elementImageList.get(new Random().nextInt(elementImageList.size()));
    }

    public ArrayList<BufferedImage> getElementImageList() {
        return elementImageList;
    }
}
