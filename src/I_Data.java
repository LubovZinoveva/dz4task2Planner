public interface I_Data<T, V> {
    public void write(T data);
    public V read(String path);
}
