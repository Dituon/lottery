package xmmt.dituon.plugin;

import xmmt.dituon.share.BaseService;
import xmmt.dituon.share.RandomCard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Test {
    public static void main(String[] args) {
//        BufferedImage bufferedImage = RandomCard.createRandom("C:\\Users\\#root.dituon\\Desktop\\data");
        new BaseService();
        BufferedImage bufferedImage = RandomCard.createRandom((short) 10);

        System.out.println("抽中了 " + BaseService.fiveNum + " 张五星");
        System.out.println("抽中了 " + BaseService.fourNum + " 张四星");
        System.out.println("抽中了 " + BaseService.threeNum + " 张三星");


        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 500);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return;
    }
}
