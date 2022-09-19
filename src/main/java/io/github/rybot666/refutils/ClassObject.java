package io.github.rybot666.refutils;

public class ClassObject {
    Class<?> clazz;
    Object obj;


    public ClassObject(Class<?> claz, Object ob){
        this.clazz = claz;
        this.obj = ob;
    }


    public Class getClazz(){
        return clazz;
    }

    public Object getObject(){
        return obj;
    }
}
