package name.upton.zest.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyFoo implements Foo, RuntimeContext {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @PostConstruct
    public void doInit() {
        System.out.println("MyFoo init name:" + name);
    }

    @Override
    @PreDestroy
    public void doDestory() {
        System.out.println("MyFoo destory");
    }

    @Override
    public String foo(String bar) {
        return "MyFoo.foo -> " + name + ":" + bar;
    }

}
