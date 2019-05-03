# A Dropwizzard Service that Demonstrates How

How to start the application
---
1. Run `mkdir -p /tmp/formulas && git clone git@github.com:shijinglu/asrc-yaml-examples.git /tmp/formulas/asrc-yaml-examples`
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/asrc-example-1.0-SNAPSHOT.jar server config.yml`
1. Get remote configuration with:

```sh
    curl -X POST --header "Content-Type:application/json" 0.0.0.0:8080/user/getConfig \
    --data '{"namespace":"overrides","context":{"PI":3.14,"NATURAL_CONSTANT_E":2.718,"first_name":"Alice","last_name":"Liddell","toggle_flag_on":true,"toggle_flag_off":false}}'
```


Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
