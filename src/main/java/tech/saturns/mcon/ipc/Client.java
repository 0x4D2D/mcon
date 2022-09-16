package tech.saturns.mcon.ipc;

import me.x150.ReffyClassView;

public class Client {

    ReffyClassView client;
    Minecraft instance;

    public Client(ReffyClassView clientclass, Minecraft instance){
        this.client = clientclass;
        this.instance = instance;
    }


    public ReffyClassView getReffy(){
        return client;
    }
}
