package io.github.rybot666.refutils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Non-object-oriented reflection wrapper methods.
 */
public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Cannot instantiate utility class");
    }

    /**
     * @param clazz The target class.
     * @param name The field name.
     * @return A {@link java.lang.reflect.Field} object for the provided name and args.
     * @throws RefUtilsException If the field doesn't exist.
     */
    public static Field getField(Class<?> clazz, String name) throws RefUtilsException {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new RefUtilsException(String.format("No such field %s", name), e);
        }
    }

    /**
     * Sets the value of a field.
     * @param clazz The target class.
     * @param target The target instance (null for static field).
     * @param name The name of the field.
     * @param value The new value for the field.
     * @throws RefUtilsException If the field is missing, or we can't access it.
     */
    public static void setFieldValue(Class<?> clazz, Object target, String name, Object value) throws RefUtilsException {
        Field field = getField(clazz, name);

        if (target == null && !Modifier.isStatic(field.getModifiers())) {
            throw new RefUtilsException(String.format("Field %s should be static but isn't!", name));
        }

        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            throw new RefUtilsException(String.format("Missing permission to set field %s", name));
        }
    }

    /**
     * Gets the value of a field.
     * @param clazz The target class.
     * @param target The target instance (null for static field).
     * @param name The name of the field.
     * @return The current value of the field.
     * @throws RefUtilsException If the field is missing, or we can't access it.
     */
    public static Object getFieldValue(Class<?> clazz, Object target, String name) throws RefUtilsException {
        Field field = getField(clazz, name);

        if (target == null && !Modifier.isStatic(field.getModifiers())) {
            throw new RefUtilsException(String.format("Field %s should be static but isn't!", name));
        }

        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RefUtilsException(String.format("Missing permission to access field %s", name));
        }
    }

    /**
     * @param clazz The target class.
     * @param name The method name.
     * @param argTypes The types of the arguments to the method.
     * @return A {@link java.lang.reflect.Method} object for the provided name and args.
     * @throws RefUtilsException If the method doesn't exist.
     */
    public static Method getMethod(Class<?> clazz, String name, Class<?>... argTypes) throws RefUtilsException {
        try {
            return clazz.getDeclaredMethod(name, argTypes);
        } catch (NoSuchMethodException e) {
            throw new RefUtilsException(String.format("No such method %s", name), e);
        }
    }

    /**
     * @param clazz The target class.
     * @param target The target instance.
     * @param name The method name.
     * @param args The arguments to pass to the method.
     * @return The value returned by the method.
     * @throws RefUtilsException If the method is missing, we can't access it, or the method threw an exception.
     */
    public static Object invokeMethod(Class<?> clazz, Object target, String name, Object... args) throws RefUtilsException {
        List<Class<?>> argTypes = new ArrayList<>();

        for (Object obj: args) {
            argTypes.add(obj.getClass());
        }

        Method method = Utils.getMethod(clazz, name, argTypes.toArray(new Class<?>[0]));

        if (target == null && !Modifier.isStatic(method.getModifiers())) {
            throw new RefUtilsException(String.format("Method %s is not static!", name));
        }

        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            throw new RefUtilsException(String.format("Missing permission to access method %s", name));
        } catch (InvocationTargetException e) {
            throw new RefUtilsException(String.format("Exception was thrown by %s", name), e.getCause());
        }
    }


        /**
         * idk how to write these so enjoy a smily face :D
     */
    public static Object invokeMethodSpecific(Class<?> clazz, Object target, String name, ClassObject... args) throws RefUtilsException {
        List<Class<?>> argTypes = new ArrayList<>();

        for (ClassObject obj: args) {
            argTypes.add(obj.getClazz());
        }

        Method method = Utils.getMethod(clazz, name, argTypes.toArray(new Class<?>[0]));

        if (target == null && !Modifier.isStatic(method.getModifiers())) {
            throw new RefUtilsException(String.format("Method %s is not static!", name));
        }

        try {
            List<Object> objargs = new ArrayList<>();
            for(ClassObject obj : args){
                objargs.add(obj.getObject());
            }
            return method.invoke(target, objargs.toArray());
        } catch (IllegalAccessException e) {
            throw new RefUtilsException(String.format("Missing permission to access method %s", name));
        } catch (InvocationTargetException e) {
            throw new RefUtilsException(String.format("Exception was thrown by %s", name), e.getCause());
        }
    }
}
