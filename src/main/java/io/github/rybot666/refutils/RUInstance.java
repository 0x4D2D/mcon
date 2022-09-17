package io.github.rybot666.refutils;

/**
 * A wrapper around an {@link java.lang.Object} with a specific class.
 * @param <T> The type of the instance.
 */
public class RUInstance<T> {
    private final RUClass<T> owner;
    private final T obj;

    RUInstance(RUClass<T> owner, T obj) {
        this.owner = owner;
        this.obj = obj;
    }

    /**
     * Invokes an instance method on this instance.
     * @param name The method name.
     * @param args The parameters to pass to the method.
     * @return The result of the method.
     * @throws RefUtilsException If the method is missing, we do not have permission to access it, or if it threw an
     * exception.
     */
    public Object invoke(String name, Object... args) throws RefUtilsException {
        return Utils.invokeMethod(this.owner.getClazz(), this.obj, name, args);
    }

    /**
     * Gets a field from this class.
     * @param name The field name.
     * @return The field value.
     * @throws RefUtilsException If the field is missing, we do not have permission to access it.
     */
    public Object getField(String name) throws RefUtilsException {
        return Utils.getFieldValue(this.owner.getClazz(), this.obj, name);
    }

    /**
     * Sets a field on this class.
     * @param name The field name.
     * @param value The field value.
     * @throws RefUtilsException If the field is missing, we do not have permission to access it.
     */
    public void setField(String name, Object value) throws RefUtilsException {
        Utils.setFieldValue(this.owner.getClazz(), this.obj, name, value);
    }
}
