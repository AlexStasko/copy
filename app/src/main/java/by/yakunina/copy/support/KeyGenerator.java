package by.yakunina.copy.support;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;

import java.util.UUID;

public class KeyGenerator {
    private final static NoArgGenerator generator = Generators.timeBasedGenerator();

    public static UUID getUUID() {
        return generator.generate();
    }
}
