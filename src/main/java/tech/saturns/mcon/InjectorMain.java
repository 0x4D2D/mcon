package tech.saturns.mcon;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class InjectorMain {
    public static void main(String[] args) throws Throwable{
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        VirtualMachineDescriptor toAttach = null;
        System.out.println("Starting MCON...");
        System.out.println("Finding processes...");
        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            if(virtualMachineDescriptor.displayName().contains("net.fabricmc")){
                toAttach = virtualMachineDescriptor;
                System.out.println("Minecraft Process auto-detected");
            }
        }
        if(toAttach == null){
            System.out.println("Minecraft could not be found, please use the manual attach menu");
            for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
                System.out.println("Process:" + virtualMachineDescriptor.displayName().split(" ")[0] + " ID:" + virtualMachineDescriptor.id());
            }
            while (true) {
                String inp = System.console().readLine();
                int a;
                try {
                    a = Integer.parseInt(inp);
                } catch (Exception e) {
                    System.out.println("PID is not a valid integer!");
                    continue;
                }
                Optional<VirtualMachineDescriptor> first = list.stream()
                    .filter(virtualMachineDescriptor -> virtualMachineDescriptor.id().equals(String.valueOf(a)))
                    .findFirst();
                if (first.isEmpty()) {
                    System.out.println("PID does not exist!");
                    continue;
                }
                toAttach = first.get();
                break;
            }
        }
        System.out.println("Process selected, injecting into PID:" + toAttach.id());

        new Thread(() -> {
            try{
                System.out.println("Lauching Virtual Server...");
                ServerSocket server = new ServerSocket(7331);
                System.out.println("Waiting for minecraft to connect to us...");
                Socket client = server.accept();
                System.out.println("Minecraft connected to Virtual Server!");
                ObjectInputStream clin = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream clout = new ObjectOutputStream(client.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                clout.writeObject("auth");
                String username = (String) clin.readObject();
                while(true){
                    System.out.print(username + "@minecraft:~$");
                    String command = reader.readLine();
                    clout.writeObject(command);
                    while(client.getInputStream().available() == 0) Thread.sleep(10);
                    String in = (String) clin.readObject();
                    System.out.println(in);
                }
            }catch(Exception e){
                e.printStackTrace();
                System.exit(0);
            }
        }).start();


        Thread.sleep(1000);
        System.out.println("Attaching to virtual machine...");
        VirtualMachine attach = VirtualMachine.attach(toAttach);
        System.out.println("Finding Classes...");
        File file = new File(InjectorMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        System.out.println("Loading agents into JVM");
        attach.loadAgent(file.getAbsolutePath());
        System.out.println("Detaching from JVM");
        attach.detach();
        System.out.println("Successfully injected the client!");
    }
}
