package xmmt.dituon.share;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

//TODO GameModel? PoolModel?
public class RarityModel {
    private float probability;
    private final ArrayList<ElementModel> elementList = new ArrayList<>();

    public RarityModel(File rarityDir) {
        try {
            //读取概率
            probability = RarityConfig.getRarityConfig(BaseService.readFileString(
                    rarityDir.getAbsolutePath() + "/rarity.json")).getProbability();

            for (File elementsDir : rarityDir.listFiles()) { //遍历卡片图片
                if (!elementsDir.isDirectory()) continue;
                elementList.add(new ElementModel(elementsDir));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float percent) {
        this.probability = percent;
    }

    public ElementModel getRandomElement() {
        return elementList.get(new Random().nextInt(elementList.size()));
    }

    public BufferedImage getRandomImage() {
        if (probability == 0.05F) {
            BaseService.fiveNum++;
        }
        if (probability == 0.25F) {
            BaseService.fourNum++;
        }
        if (probability == 0.70F) {
            BaseService.threeNum++;
        }

        return getRandomElement().getRandomImage();
    }
}
