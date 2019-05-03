package org.shijinglu.asrc.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import org.shijinglu.asrc.core.EventSender;
import org.shijinglu.asrc.core.IFormulaProvider;
import org.shijinglu.asrc.core.Service;
import org.shijinglu.asrc.core.UdfMap;
import org.shijinglu.asrc.gateway.RedisStore;
import org.shijinglu.asrc.gateway.SingleRedisProvider;
import org.shijinglu.asrc.gateway.YamlFormulaProvider;
import org.shijinglu.asrc.service.health.NaiveHealthCheck;
import org.shijinglu.asrc.service.resources.RemoteConfig;
import org.shijinglu.asrc.service.resources.SayHello;
import org.shijinglu.lure.extensions.IFunction;

public class MainApp extends Application<MainConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MainApp().run(args);
    }

    @Override
    public String getName() {
        return "org.shijinglu";
    }

    @Override
    public void initialize(final Bootstrap<MainConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MainConfiguration configuration, final Environment environment) {

        environment.healthChecks().register(configuration.getTemplate(), new NaiveHealthCheck());
        environment.jersey().register(new SayHello(configuration.getTemplate(), "there"));
        environment
                .jersey()
                .register(new RemoteConfig(createService(configuration.getFormulaDir())));
    }

    private Service createService(String yamlDir) {
        Path yamlDirPath = Paths.get(yamlDir);
        if (!Files.exists(yamlDirPath)) {
            throw new RuntimeException(
                    "Could not load formulas, make sure path: '" + yamlDir + "' exist");
        }
        IFormulaProvider formulaProvider = new YamlFormulaProvider(yamlDirPath);
        RedisStore redisStore = new RedisStore(new SingleRedisProvider());
        IFunction udfMap = new UdfMap(redisStore);
        EventSender sender = new EventSender();
        return new Service(formulaProvider, Collections.singletonList(udfMap), sender);
    }
}
