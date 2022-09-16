package tech.saturns.mcon.commands;

public class HelpCmd extends Command{

    public HelpCmd() {
        super("help");
    }
    
    @Override
    public String call(String args[]){
        return "help me please";
    }

}
