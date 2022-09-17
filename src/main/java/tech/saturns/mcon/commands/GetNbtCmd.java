package tech.saturns.mcon.commands;


import io.github.rybot666.refutils.RUClass;

public class GetNbtCmd extends Command{

    public GetNbtCmd() {
        super("getNbt");
    }
    
    @Override
    public String call(String args[]){
        return "real";
        //RUClass mainhandstack = instance.getPlayer().getMainHandStack();
        //RUClass nbt = RUClass.of(mainhandstack.getMethod("method_7969").invoke().get());
        //if(nbt == null) return "NBT: null";
        //String stringnbt = (String) nbt.getMethod("method_10558").invoke().get();
        //return "NBT: " + stringnbt;
    }

}
