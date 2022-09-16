package tech.saturns.mcon;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.net.InetAddress;
import java.net.Socket;

import tech.saturns.mcon.ipc.Minecraft;

public class ModMain {

    public static Minecraft instance;

    public static void agentmain(String aargs, Instrumentation inst) {

        instance = new Minecraft(inst);

        new Thread(() -> {
            try{
                InetAddress host = InetAddress.getLocalHost();
                System.out.println("Trying to connect to socket server...");
                Socket socket = new Socket(host.getHostName(), 7331);
                ObjectOutputStream sout = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Success!");
                ObjectInputStream sin = new ObjectInputStream(socket.getInputStream());
                System.out.println("Starting connection server...");
                while(true){
                    if(socket.getInputStream().available() > 0){
                        String message = (String) sin.readObject();
                        ClientHandler.sendCommand(message, sout);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                System.exit(0);
            }
        }).start();
    }
}
