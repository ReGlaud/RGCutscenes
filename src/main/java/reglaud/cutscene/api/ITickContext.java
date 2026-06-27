package reglaud.cutscene.api;

public interface ITickContext {
    void deleteScene();
    void memoryAdd(String key, Object object);
    Object memoryGet(String key);
    void memoryChange(String key, Object object);
    boolean isAlive();
}
