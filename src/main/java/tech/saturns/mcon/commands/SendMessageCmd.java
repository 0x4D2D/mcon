package tech.saturns.mcon.commands;

import io.github.rybot666.refutils.RUClass;
import io.github.rybot666.refutils.RefUtilsException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SendMessageCmd extends Command{

    public SendMessageCmd() {
        super("sendMessage");
    }
    
    @Override
    public String call(String args[]) throws RefUtilsException{
        RUClass textV = RUClass.of(instance.getClassByName("net.minecraft.class_2561"));
        Text textobject = (Text) textV.invokeStatic("method_30163", String.join(" ", args));
        instance.getPlayer().getDynamicPlayer().invoke("method_7353", textobject, false);
        return "sent " + String.join(" ", args);
    }

}
