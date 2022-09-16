package tech.saturns.mcon.commands;

import tech.saturns.mcon.ModMain;
import tech.saturns.mcon.ipc.Minecraft;

public class Command {

    String name;

    Minecraft instance = ModMain.instance;

    public Command(String name){
        this.name = name;
    }


    public String call(String[] args){
        return "Method call in command not overridden";
    }

    public String getName(){
        return name;
    }

    private static String readArgsFrom(String[] args, int index){
        String out = "";
        for(int i = index; i < args.length; i++){
            out += args[i] + " ";
        }
        return out;
    }
}
