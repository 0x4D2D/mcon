package tech.saturns.mcon.ipc;

import java.lang.instrument.Instrumentation;

import io.github.rybot666.refutils.RUClass;
import io.github.rybot666.refutils.RUInstance;
import io.github.rybot666.refutils.RefUtilsException;
import net.minecraft.client.MinecraftClient;


public class Minecraft {

    ClassFinder cf;

    public Minecraft(Instrumentation inst){
        cf = new ClassFinder(inst.getAllLoadedClasses());
    }

    public Class<?> getClassByName(String className){
        return cf.find(className);
    }


    public Client getClient() throws RefUtilsException{
        RUClass minecraftClientStatic = RUClass.of(cf.find("net.minecraft.class_310"));
        RUClass minecraftClientInstance = RUClass.of(minecraftClientStatic.invokeStatic("method_1551").getClass());
        return new Client(minecraftClientInstance, this, minecraftClientStatic.invokeStatic("method_1551"));
    }


    public Player getPlayer() throws RefUtilsException{
        RUInstance minecraftclient = getClient().getRuClass().instanceFrom(getClient().getInstance());
        RUClass playerInstance = RUClass.of(minecraftclient.getField("field_1724").getClass());
        return new Player(playerInstance, this, minecraftclient.getField("field_1724"));
    }

    public RUInstance getSession() throws RefUtilsException{
        RUInstance minecraftclient = getClient().getRuClass().instanceFrom(getClient().getInstance());
        RUClass session = RUClass.of(minecraftclient.invoke("method_1548").getClass());
        RUInstance sessioninst = session.instanceFrom(minecraftclient.invoke("method_1548"));
        return sessioninst;
    }
}
