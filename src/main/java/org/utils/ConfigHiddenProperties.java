package org.utils;

import org.aeonbits.owner.Config;

@Config.Sources("file:./src/main/resources/hiddenConfig.properties")
public interface ConfigHiddenProperties extends Config {
    String BO_LOGIN();
    String BO_PASSWORD();


}
