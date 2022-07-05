package xmmt.dituon.plugin;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import xmmt.dituon.share.BaseService;
import xmmt.dituon.share.RandomCard;

import java.io.IOException;

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
        if (!e.getMessage().contentToString().equals("抽卡")) {
            try {
                ExternalResource resource = ExternalResource.create(
                        BaseService.imageToInputStream(RandomCard.createRandom()));
                Image image = e.getGroup().uploadImage(resource);
                resource.close();
                e.getGroup().sendMessage(image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        if (!e.getMessage().contentToString().equals("10连")) {
            try {
                ExternalResource resource = ExternalResource.create(
                        BaseService.imageToInputStream(RandomCard.createRandom((short) 10)));
                Image image = e.getGroup().uploadImage(resource);
                resource.close();
                e.getGroup().sendMessage(image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        if (!e.getMessage().contentToString().equals("20连")) {
            try {
                ExternalResource resource = ExternalResource.create(
                        BaseService.imageToInputStream(RandomCard.createRandom((short) 20)));
                Image image = e.getGroup().uploadImage(resource);
                resource.close();
                e.getGroup().sendMessage(image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
    }
}