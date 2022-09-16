package tech.saturns.mcon.ipc;

public class ClassFinder {

    Class<?>[] classes;

    public ClassFinder(Class<?>[] allLoadedClasses) {
        this.classes = allLoadedClasses;
    }


    public Class<?> find(String classname){
        for(Class<?> classview : this.classes){
            if(classview.getName().equalsIgnoreCase(classname)){
                return classview;
            }
        }
        System.out.println("class not found " + classname);
        System.exit(0);
        return null;
    }
    
}