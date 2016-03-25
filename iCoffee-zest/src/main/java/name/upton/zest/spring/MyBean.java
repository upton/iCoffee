package name.upton.zest.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean implements Bean, RuntimeContext {
    private String env;

    public void setEnv(String env) {
        this.env = env;
    }

    @Override
    @PostConstruct
    public void doInit() {
        System.out.println("MyBean init env:" + env);
    }

    @Override
    @PreDestroy
    public void doDestory() {
        System.out.println("MyBean destory");
    }

    @Override
    public String bb(String name) {
        return "MyBean.bb -> " + env + ":" + name;
    }

}
