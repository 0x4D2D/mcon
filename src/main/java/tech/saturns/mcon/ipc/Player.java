package tech.saturns.mcon.ipc;

import me.x150.ReffyClassView;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.ItemStack;

public class Player {

    ReffyClassView player;
    Minecraft instance;

    public Player(ReffyClassView playerclass, Minecraft instance){
        this.player = playerclass;
        this.instance = instance;
    }


    public void sendChatMessage(String message){
        player.getMethod("method_3142", String.class).invoke(message);
    }

    public ReffyClassView getMainHandStack(){
        //MinecraftClient.getInstance().player.getInventory().getMainHandStack().getNbt().asString();
        ReffyClassView inventory = ReffyClassView.from(player.getMethod("method_31548").invoke().get());
        ReffyClassView mainhandstack = ReffyClassView.from(inventory.getMethod("method_7391").invoke().get());
        return mainhandstack;
    }


    public ReffyClassView getReffy(){
        return player;
    }

    public Class<?> getRawClassInstance(){
        return instance.getClient().getReffy().getField("field_1724").get().get().getClass();
    }
}
