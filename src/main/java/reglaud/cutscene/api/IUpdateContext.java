package reglaud.cutscene.api;

import net.minecraft.client.gui.DrawContext;

public interface IUpdateContext {
    void memoryAdd(String key, Object object);
    Object memoryGet(String key);
    void memoryChange(String key, Object object);
    boolean isAlive();
    float getSmoothLocalTime();
    DrawContext getDrawContext();
}
