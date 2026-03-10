package org.utils;

import org.aeonbits.owner.Config;

@Config.Sources("file:./src/main/resources/config.properties")
public interface ConfigProperties extends Config {

    String base_url();

    String backoffice_url();

    String bo_api_url();

    long TIME_FOR_IMPLICIT_WAIT();

    long TIME_FOR_EXPLICIT_WAIT_LOW();

    long TIME_FOR_EXPLICIT_WAIT_HIGH();
}
