package xmmt.dituon.share;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageSynthesis {
    public static BufferedImage synthesisBackgroundImage(ArrayList<BufferedImage> cardImageList) {

        short rowLength = 5; //列数

        int cardWidth = cardImageList.get(0).getWidth();
        int cardHeight = cardImageList.get(0).getHeight();

        int bgWidth = cardWidth * rowLength;
        int bgHeight = (cardImageList.size() + rowLength - 1) / rowLength * cardHeight;

        BufferedImage canvas = new BufferedImage(bgWidth, bgHeight, cardImageList.get(0).getType());
        Graphics2D canvasG2d = canvas.createGraphics();

        //透明背景
        canvas = canvasG2d.getDeviceConfiguration().createCompatibleImage(
                bgWidth, bgHeight, Transparency.TRANSLUCENT);
        canvasG2d.dispose();
        canvasG2d = canvas.createGraphics();

        int x = 0;
        int y = 0;
        short i = 0;
        for (BufferedImage cardImage : cardImageList) {
            canvasG2d.drawImage(cardImage, x, y, cardWidth, cardHeight, null);

            x = ++i % rowLength != 0 ? x + cardWidth : 0;
            y += i % rowLength == 0 ? cardHeight : 0;
        }
        canvasG2d.dispose();
        return canvas;
    }

    public static void synthesisImage(BufferedImage background, BufferedImage icon, int[] pos) {
        Graphics2D g2d = background.createGraphics();
        if (pos.length == 2) { //x,y
            pos = new int[]{pos[0], pos[1], icon.getWidth(), icon.getHeight()};
        }
        g2d.drawImage(icon, pos[0], pos[1], pos[2], pos[3], null); //x,y,w,h
    }
}