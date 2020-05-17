package com.family.fmlbase.base;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EnvironmentDefine {
    private static String DEV = "dev";

    private static String TEST = "test";

    private static String PROD = "prod";

    private static String PRE = "pre";

    @Resource
    private Environment environment;

    public boolean isDev() {
        String[] activeProfiles = this.environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            if (DEV.equals(activeProfile))
                return true;
        }
        return false;
    }

    public boolean isTest() {
        String[] activeProfiles = this.environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            if (TEST.equals(activeProfile))
                return true;
        }
        return false;
    }

    public boolean isProd() {
        String[] activeProfiles = this.environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            if (PROD.equals(activeProfile))
                return true;
        }
        return false;
    }

    public boolean isPre() {
        String[] activeProfiles = this.environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            if (PRE.equals(activeProfile))
                return true;
        }
        return false;
    }
}
