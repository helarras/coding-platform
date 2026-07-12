package com.helarras.codingplatform;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTests {

    @Test
    void verifyModularStructure() {
        ApplicationModules.of(CodingPlatformApplication.class).verify();
    }
}
