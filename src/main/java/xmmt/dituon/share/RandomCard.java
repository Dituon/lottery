package xmmt.dituon.share;

import java.awt.image.BufferedImage;
import java.util.*;

public class RandomCard {
    static boolean percentFormatted = false;

    public static BufferedImage createRandom() {
        if (!percentFormatted) { //转为百分数
            float sumRate = 0; //总概率
            for (RarityModel rarity : BaseService.rarityList) {
                sumRate += rarity.getProbability();
            }
            for (RarityModel rarity : BaseService.rarityList) {
                rarity.setProbability(rarity.getProbability() / sumRate);
            }
            percentFormatted = true;
        }

        float randomIndex = (float) Math.random();
        for (RarityModel rarity : BaseService.rarityList) {
            if (rarity.getProbability() >= randomIndex) return rarity.getRandomImage();
            randomIndex -= rarity.getProbability();
        }

        return null;
    }

    public static BufferedImage createRandom(short n) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        for (short i = 0; i < n; i++) {
            images.add(createRandom());
        }
        return ImageSynthesis.synthesisBackgroundImage(images);
    }
}
