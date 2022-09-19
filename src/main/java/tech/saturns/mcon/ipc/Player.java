package tech.saturns.mcon.ipc;

import io.github.rybot666.refutils.RUClass;
import io.github.rybot666.refutils.RUInstance;
import io.github.rybot666.refutils.RefUtilsException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.ItemStack;

public class Player {

    RUClass staticplayer;
    Object instplayer;
    RUInstance player;
    Minecraft instance;

    public Player(RUClass playerclass, Minecraft instance, Object baseclass){
        this.staticplayer = playerclass;
        this.instance = instance;
        this.player = staticplayer.instanceFrom(baseclass);
        this.instplayer = baseclass;
    }


    public void sendChatMessage(String message) throws RefUtilsException{
        player.invoke("method_3142", message);
    }

    public RUClass getRuClass(){
        return staticplayer;
    }

    public Object getPlayerCreationInstance(){
        return instplayer;
    }

    public RUInstance getDynamicPlayer(){
        return player;
    }
}
