package xmmt.dituon.share;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class BaseService {
    final static String path = "C:\\Users\\#root.dituon\\Desktop\\data";
    final static ArrayList<RarityModel> rarityList = new ArrayList<>();
    public static short fiveNum = 0;
    public static short fourNum = 0;
    public static short threeNum = 0;

    public BaseService() {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            return;
        }

        try {
            for (File rarityDir : dir.listFiles()) { //稀有度目录
                if (!rarityDir.isDirectory()) continue;
                rarityList.add(new RarityModel(rarityDir));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String readFileString(String path) throws IOException {
        BufferedReader configBr = new BufferedReader(
                new FileReader(path));
        StringBuilder configSb = new StringBuilder();
        String str;
        while ((str = configBr.readLine()) != null) {
            configSb.append(str);
        }
        configBr.close();
        return configSb.toString();
    }

    public static InputStream imageToInputStream(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
        } catch (IOException ignore) {
        }
        return new ByteArrayInputStream(os.toByteArray());
    }
}
