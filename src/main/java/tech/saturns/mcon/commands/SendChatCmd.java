package tech.saturns.mcon.commands;

import io.github.rybot666.refutils.RefUtilsException;
import net.minecraft.client.MinecraftClient;

public class SendChatCmd extends Command{

    public SendChatCmd() {
        super("sendChat");
    }
    
    @Override
    public String call(String args[]) throws RefUtilsException{
        instance.getPlayer().getDynamicPlayer().invoke("method_3142", String.join(" ", args));
        return "sent " + String.join(" ", args);
    }

}
