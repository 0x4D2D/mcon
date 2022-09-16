package tech.saturns.mcon;

import java.io.IOException;
import java.io.ObjectOutputStream;

import me.x150.ReffyClassView;
import tech.saturns.mcon.commands.Command;
import tech.saturns.mcon.commands.CommandHandler;

public class ClientHandler {


    public static void sendCommand(String command, ObjectOutputStream server) throws IOException{

        if(command.equals("auth")){
            ReffyClassView session = ModMain.instance.getSession();
            String username = (String) session.getField("field_1982").get().get();
            server.writeObject(username);
            return;
        }

        String[] args = command.split(" ");
        String cmd = args[0];

        if(CommandHandler.find(cmd) != null){
            Command c = CommandHandler.find(cmd);
            String output = c.call(args);
            server.writeObject(output);
        }else{
            server.writeObject("Command Not found \"" + command + "\"");
        }

        
    }
}
