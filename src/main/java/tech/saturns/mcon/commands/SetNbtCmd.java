package tech.saturns.mcon.commands;


import io.github.rybot666.refutils.ClassObject;
import io.github.rybot666.refutils.RUClass;
import io.github.rybot666.refutils.RUInstance;
import io.github.rybot666.refutils.RefUtilsException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;

public class SetNbtCmd extends Command{

    public SetNbtCmd() {
        super("setNbt");
    }
    
    @Override
    public String call(String args[]) throws RefUtilsException{
        RUClass livingEntity = RUClass.of(instance.getPlayer().getRuClass().getClazz().getSuperclass().getSuperclass().getSuperclass()); //get player as LivingEntity.class
        RUInstance playerAsLivingEntity = livingEntity.instanceFrom(instance.getPlayer().getPlayerCreationInstance()); //instance it


        Object mainHandStack = playerAsLivingEntity.invoke("method_6047"); //.getMainhandStack() method
        RUClass itemStack = RUClass.of(mainHandStack.getClass()); //get ItemStack
        RUInstance mainHandItemStack = itemStack.instanceFrom(mainHandStack); //instance it into item stack
        

        RUClass stringNbtReader = RUClass.of(instance.getClassByName("net.minecraft.class_2522"));
        Object compoundTag = stringNbtReader.invokeStatic("method_10718", String.join(" ", args));

        mainHandItemStack.invokeSpecific("method_7980", new ClassObject(instance.getClassByName("net.minecraft.class_2487"), compoundTag)); //.setNbt() method

        return "Updated MainHand NBT!";
    }

}
