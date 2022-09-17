package tech.saturns.mcon;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import io.github.rybot666.refutils.RUClass;
import io.github.rybot666.refutils.RUInstance;
import io.github.rybot666.refutils.RefUtilsException;
import tech.saturns.mcon.commands.Command;
import tech.saturns.mcon.commands.CommandHandler;

public class ClientHandler {


    public static void sendCommand(String command, ObjectOutputStream server) throws Exception{

        if(command.equals("auth")){
            RUInstance session = ModMain.instance.getSession();
            String username = (String) session.invoke("method_1676");
            server.writeObject(username);
            return;
        }

        String[] args = command.split(" ");
        String cmd = args[0];

        if(CommandHandler.find(cmd) != null){
            Command c = CommandHandler.find(cmd);
            String output = c.call(Arrays.copyOfRange(args, 1, args.length));
            server.writeObject(output);
        }else{
            server.writeObject("Command Not found \"" + command + "\"");
        }

        
    }
}
