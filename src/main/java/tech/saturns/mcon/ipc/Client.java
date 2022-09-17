package tech.saturns.mcon.ipc;

import io.github.rybot666.refutils.RUClass;

public class Client {

    RUClass client;
    Object mc;
    Minecraft instance;

    public Client(RUClass clientclass, Minecraft instance, Object baseclass){
        this.client = clientclass;
        this.instance = instance;
        this.mc = baseclass;
    }


    public Object getInstance(){
        return mc;
    }


    public RUClass getRuClass(){
        return client;
    }
}
