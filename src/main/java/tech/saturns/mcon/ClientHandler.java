package tech.saturns.mcon;

import java.io.IOException;
import java.io.ObjectOutputStream;

import me.x150.ReffyClassView;
import me.x150.ReffyMethodView;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ClientHandler {


    public static void sendCommand(String command, ObjectOutputStream server) throws IOException{
        Minecraft mc = ModMain.mc;

        String[] args = command.split(" ");
        String cmd = args[0];

        switch(cmd){
            case "auth" -> {
                ReffyClassView session = mc.getSession();
                String username = (String) session.getField("field_1982").get().get();
                server.writeObject(username);
            }

            case "help" -> {
                server.writeObject("sendMessage, sendChatMessage, playerSetAbilities, updatePosition, addVelocity");
            }

            case "sendMessage" -> {
                String chatmessage = readArgsFrom(args, 1);
                chatmessage = chatmessage.replace("&", "\u00a7");
                Class<?> clientPlayerEntity = mc.client.getField("field_1724").get().get().getClass();
                Class<?> abstractClientPlayerEntity = clientPlayerEntity.getSuperclass();
                Class<?> playerEntity = abstractClientPlayerEntity.getSuperclass();
                System.out.println(abstractClientPlayerEntity.getName());
                System.out.println(clientPlayerEntity.getName());
                System.out.println(playerEntity.getName());
                ReffyClassView playerinstance = ReffyClassView.from(playerEntity);
                Class<?> textClass = mc.getClassByName("net.minecraft.class_2561");
                ReffyMethodView sendMessage = playerinstance.getMethod("method_7353", textClass, Boolean.class);
                sendMessage.invoke(Text.of(chatmessage), false);
                server.writeObject("Sent " + chatmessage + " to the hud");
            }


            case "sendChatMessage" -> {
                String chatmessage = readArgsFrom(args, 1);
                ReffyClassView player = mc.getPlayer();
                player.getMethod("method_3142", String.class).invoke(chatmessage);
                server.writeObject("Sent " + chatmessage + " to chat");
            }


            case "updatePosition" -> {
                Class<?> player = mc.client.getField("field_1724").get().get().getClass();
                Class<?> entity = player.getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                ReffyClassView.from(entity).getMethod("method_30634", Double.class, Double.class, Double.class).invoke(Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3]));
            }

            case "addVelocity" -> {
                Class<?> player = mc.client.getField("field_1724").get().get().getClass();
                Class<?> entity = player.getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                System.out.println(entity.getName());
                ReffyClassView.from(entity).getMethod("method_5762", Double.class, Double.class, Double.class).invoke(Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3]));
            }


            case "playerSetAbilities" -> {
                Class <?> clientPlayer = mc.client.getField("field_1724").get().get().getClass();
                ReffyClassView playerAbilities = ReffyClassView.from(ReffyClassView.from(clientPlayer.getSuperclass().getSuperclass()).getMethod("method_31549").invoke().get());
                playerAbilities.addInstance(ReffyClassView.from(clientPlayer.getSuperclass().getSuperclass()).getMethod("method_31549").invoke().get()); //god help me
                switch(args[1]){
                    case "allowFlying" -> {
                        if(args[2] == "True"){
                            playerAbilities.getField("field_7478").set(true);
                            server.writeObject(args[1] + " is now set to " + args[2]);
                        }else{
                            playerAbilities.getField("field_7478").set(false);
                            server.writeObject(args[1] + " is now set to " + args[2]);
                        }
                    }

                    case "allowModifyWorld" -> {
                        if(args[2] == "True"){
                            playerAbilities.getField("field_7476").set(true);
                            server.writeObject(args[1] + " is now set to " + args[2]);
                        }else{
                            playerAbilities.getField("field_7476").set(false);
                            server.writeObject(args[1] + " is now set to " + args[2]);
                        }
                    }

                    case "creativeMode" -> {
                        if(args[2] == "True"){
                            playerAbilities.getField("field_7477").set(true);
                            server.writeObject(args[1] + " is now set to " + args[2]);
                        }else{
                            playerAbilities.getField("field_7477").set(false);
                            server.writeObject(args[1] + " is now set to " + args[2]);
                        }
                    }

                    default -> {
                        server.writeObject("Unknown property of player, use allowFlying, creativeMode, allowModifyWorld");
                    }
                }
            }



            default -> {
                server.writeObject("Unknown command " + command);
            }
        }
    }


    private static String readArgsFrom(String[] args, int index){
        String out = "";
        for(int i = index; i < args.length; i++){
            out += args[i] + " ";
        }
        return out;
    }
}
