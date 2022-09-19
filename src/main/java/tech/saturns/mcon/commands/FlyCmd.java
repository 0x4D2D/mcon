package tech.saturns.mcon.commands;

import io.github.rybot666.refutils.ClassObject;
import io.github.rybot666.refutils.RUClass;
import io.github.rybot666.refutils.RUInstance;
import io.github.rybot666.refutils.RefUtilsException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;

public class FlyCmd extends Command{

    public FlyCmd() {
        super("fly");
    }
    
    @Override
    public String call(String args[]) throws RefUtilsException{
        RUClass entity = RUClass.of(instance.getPlayer().getRuClass().getClazz().getSuperclass().getSuperclass());
        RUInstance playerAsEntity = entity.instanceFrom(instance.getPlayer().getPlayerCreationInstance());
        RUClass abilities = RUClass.of(playerAsEntity.invoke("method_31549").getClass());
        RUInstance playerAbilities = abilities.instanceFrom(playerAsEntity.invoke("method_31549"));
        if(args[0].equalsIgnoreCase("on")){
            playerAbilities.setField("field_7478", true);
            return "Updated Flying to TRUE";
        }
        if(args[0].equalsIgnoreCase("off")){
            playerAbilities.setField("field_7478", false);
            return "Updated Flying to FALSE";
        }
        return "Use Fly on or Fly off";
    }

}
