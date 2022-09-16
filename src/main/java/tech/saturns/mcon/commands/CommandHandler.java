package tech.saturns.mcon.commands;

import java.util.ArrayList;
import java.util.List;


public class CommandHandler {
    private static final List<Command> registry = new ArrayList<>();

    static {
        registry.add(new HelpCmd());
        registry.add(new GetNbtCmd());
    }

    public static List<Command> getCommands(){
        return registry;
    }

    public static Command find(String name){
        for(Command c : registry){
            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
}
