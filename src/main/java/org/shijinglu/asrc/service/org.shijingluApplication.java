package org.shijinglu.asrc.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class org.shijingluApplication extends Application<org.shijingluConfiguration> {

    public static void main(final String[] args) throws Exception {
        new org.shijingluApplication().run(args);
    }

    @Override
    public String getName() {
        return "org.shijinglu";
    }

    @Override
    public void initialize(final Bootstrap<org.shijingluConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final org.shijingluConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
