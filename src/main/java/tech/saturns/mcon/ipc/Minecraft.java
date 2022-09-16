package tech.saturns.mcon.ipc;

import java.lang.instrument.Instrumentation;
import me.x150.ReffyClassView;


public class Minecraft {

    ClassFinder cf;

    public Minecraft(Instrumentation inst){
        cf = new ClassFinder(inst.getAllLoadedClasses());
    }

    public Class<?> getClassByName(String className){
        return cf.find(className);
    }


    public Client getClient(){
        ReffyClassView minecraftClientStatic = ReffyClassView.from(cf.find("net.minecraft.class_310"));
        ReffyClassView minecraftClientInstance = ReffyClassView.from(minecraftClientStatic.getMethod("method_1551").invoke().get());
        return new Client(minecraftClientInstance, this);
    }


    public Player getPlayer(){
        ReffyClassView playerInstance = ReffyClassView.from(getClient().getReffy().getField("field_1724").get().get());
        return new Player(playerInstance, this);
    }

    public ReffyClassView getSession(){
        ReffyClassView session = ReffyClassView.from(getClient().getReffy().getField("field_1726").get().get());
        return session;
    }
}
