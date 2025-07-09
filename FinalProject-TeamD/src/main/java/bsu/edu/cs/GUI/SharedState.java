package bsu.edu.cs.GUI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.concurrent.atomic.AtomicBoolean;

public class SharedState {
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private final BooleanProperty sharedBooleanProperty = new SimpleBooleanProperty(atomicBoolean.get());

    public BooleanProperty getSharedBooleanProperty() {
        return sharedBooleanProperty;
    }

    public void setAtomicBooleanValue(boolean value) {
        atomicBoolean.set(value);
      sharedBooleanProperty.set(value);
    }

    public boolean getAtomicBooleanValue() {
        return atomicBoolean.get();
    }
}
