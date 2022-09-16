package tech.saturns.mcon.commands;

import me.x150.ReffyClassView;

public class GetNbtCmd extends Command{

    public GetNbtCmd() {
        super("getNbt");
    }
    
    @Override
    public String call(String args[]){
        ReffyClassView mainhandstack = instance.getPlayer().getMainHandStack();
        ReffyClassView nbt = ReffyClassView.from(mainhandstack.getMethod("method_7969").invoke().get());
        if(nbt == null) return "NBT: null";
        String stringnbt = (String) nbt.getMethod("method_10558").invoke().get();
        return "NBT: " + stringnbt;
    }

}
