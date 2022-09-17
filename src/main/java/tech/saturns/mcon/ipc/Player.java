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

    public RUClass getMainHandStack() throws RefUtilsException{
        //MinecraftClient.getInstance().player.getInventory().getMainHandStack().getNbt().asString();
        RUClass inventory = RUClass.of(player.invoke("method_31548").getClass());
        RUClass mainhandstack = RUClass.of(inventory.invokeStatic("method_7391").getClass());
        return mainhandstack;
    }


    public RUClass getRuClass(){
        return staticplayer;
    }

    public RUInstance getDynamicPlayer(){
        return player;
    }
}
