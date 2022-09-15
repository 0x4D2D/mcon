package tech.saturns.mcon;

import java.lang.instrument.Instrumentation;
import me.x150.ReffyClassView;
import me.x150.ReffyMethodView;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;


public class Minecraft {

    ClassFinder cf;
    ReffyClassView client;
    ReffyClassView player;

    public Minecraft(Instrumentation inst){
        cf = new ClassFinder(inst.getAllLoadedClasses());
        client = getClientInstance();
        player = getPlayer();
    }

    public Class<?> getClassByName(String className){
        return cf.find(className);
    }


    private ReffyClassView getClientInstance(){
        ReffyClassView minecraftClientStatic = ReffyClassView.from(cf.find("net.minecraft.class_310")); // MinecraftClient
        ReffyClassView minecraftClientInstance = ReffyClassView.from(minecraftClientStatic.getMethod("method_1551").invoke().get()); // MinecraftClient instance
        return minecraftClientInstance;
    }


    public ReffyClassView getPlayer(){
        ReffyClassView playerInstance = ReffyClassView.from(client.getField("field_1724").get().get());
        return playerInstance;
    }

    public ReffyClassView getSession(){
        ReffyClassView session = ReffyClassView.from(client.getField("field_1726").get().get());
        return session;
    }

    
    public void sendMessage(String message, Class<?> playerEntity){
        ReffyClassView playerinstance = ReffyClassView.from(playerEntity);
        Class<?> textClass = cf.find("net.minecraft.class_2561");
        ReffyMethodView sendMessage = playerinstance.getMethod("method_7353", textClass, Boolean.class);
        for(Class<?> cl : sendMessage.getParameters()){
            System.out.println(cl.getName());
        }
        sendMessage.invoke(Text.of(message), false);
    }


    //ReffyClassView minecraftClientStatic = ReffyClassView.from(cf.find("net.minecraft.class_310")); // MinecraftClient
    //ReffyClassView minecraftClientInstance = ReffyClassView.from(minecraftClientStatic.getMethod("method_1551").invoke().get()); // MinecraftClient instance
    //ReffyClassView playerInstance = ReffyClassView.from(minecraftClientInstance.getField("field_1724").get().get()); // player
    //playerInstance.getMethod("method_3142", String.class).invoke("Hello chat"); // sendMessage

}
