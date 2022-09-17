package io.github.rybot666.refutils;

/**
 * A wrapper around a {@link java.lang.Class}.
 */
public class RUClass<T> {
    private final Class<T> clazz;

    private RUClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @param clazz The class to wrap.
     * @return A new {@code RUClass} with the given class.
     */
    public static <T> RUClass<T> of(Class<T> clazz) {
        return new RUClass<>(clazz);
    }

    /**
     * Invokes a static method on this class.
     * @param name The method name.
     * @param args The parameters to pass to the method.
     * @return The result of the method.
     * @throws RefUtilsException If the method is missing, we do not have permission to access it, or if it threw an
     * exception.
     */
    public Object invokeStatic(String name, Object... args) throws RefUtilsException {
        return Utils.invokeMethod(this.clazz, null, name, args);
    }

    /**
     * Gets a static field from this class.
     * @param name The method name.
     * @return The field value.
     * @throws RefUtilsException If the field is missing, we do not have permission to access it.
     */
    public Object getStaticField(String name) throws RefUtilsException {
        return Utils.getFieldValue(this.clazz, null, name);
    }


    /**
     * Sets a static field on this class.
     * @param name The field name.
     * @param value The new value.
     * @throws RefUtilsException If the field is missing, we do not have permission to access it.
     */
    public void setStaticField(String name, Object value) throws RefUtilsException {
        Utils.setFieldValue(this.clazz, null, name, value);
    }

    /**
     * @param obj The object to create an instance from.
     * @return The new {@link RUInstance}.
     */
    public RUInstance<T> instanceFrom(T obj) {
        return new RUInstance<>(this, obj);
    }

    public Class<T> getClazz() {
        return this.clazz;
    }

    @Override
    public String toString() {
        return "RUClass{clazz=" + this.clazz + "}";
    }
}

// RUClass<?> clazz = RUClass.of(String.class);
//
// RUInstance instance = clazz.instanceFrom("abc");
// instance.invoke("abc");