package tech.saturns.mcon.commands;

import io.github.rybot666.refutils.ClassObject;
import io.github.rybot666.refutils.RUClass;
import io.github.rybot666.refutils.RUInstance;
import io.github.rybot666.refutils.RefUtilsException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class VelCmd extends Command{

    public VelCmd() {
        super("vel");
    }
    
    @Override
    public String call(String args[]) throws RefUtilsException{
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);
        RUClass entity = RUClass.of(instance.getPlayer().getRuClass().getClazz().getSuperclass().getSuperclass().getSuperclass().getSuperclass());
        RUInstance playerAsEntity = entity.instanceFrom(instance.getPlayer().getPlayerCreationInstance());
        playerAsEntity.invokeSpecific("method_5762", new ClassObject(double.class, (double)x), new ClassObject(double.class, (double)y), new ClassObject(double.class, (double)z));
        return "Flung Player!";
    }

}
