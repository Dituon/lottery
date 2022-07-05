package xmmt.dituon.plugin;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import xmmt.dituon.share.BaseService;
import xmmt.dituon.share.RandomCard;

import java.awt.image.BufferedImage;

public final class Lottery extends JavaPlugin {
    public static final Lottery INSTANCE = new Lottery();

    private Lottery() {
        super(new JvmPluginDescriptionBuilder("xmmt.dituon.lottery", "0.1")
                .name("Lottery")
                .author("Dituon")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, this::onGroupMessageEvent);
        new BaseService();
    }

    public void onGroupMessageEvent(GroupMessageEvent e) { //TODO 指令解析
        if (e.getMessage().contentToString().equals("抽卡")) {
            sendImage(e.getGroup(), RandomCard.createRandom());
            return;
        }

        if (e.getMessage().contentToString().equals("10连")) {
            sendImage(e.getGroup(), RandomCard.createRandom((short) 10));
            return;
        }

        if (e.getMessage().contentToString().equals("20连")) {
            sendImage(e.getGroup(), RandomCard.createRandom((short) 20));
            return;
        }

        if (e.getMessage().contentToString().equals("50连")) {
            sendImage(e.getGroup(), RandomCard.createRandom((short) 20));
            return;
        }

        if (e.getMessage().contentToString().equals("100连")) {
            sendImage(e.getGroup(), RandomCard.createRandom((short) 100));
            return;
        }

        if (e.getMessage().contentToString().equals("200连")) {
            sendImage(e.getGroup(), RandomCard.createRandom((short) 200));
            return;
        }
    }

    private void sendImage(Group group, BufferedImage inputImage) {
        try {
            ExternalResource resource = ExternalResource.create(BaseService.imageToInputStream(inputImage));
            Image image = group.uploadImage(resource);
            resource.close();
            group.sendMessage(image);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}