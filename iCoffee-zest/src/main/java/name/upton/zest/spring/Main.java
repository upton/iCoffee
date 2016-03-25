package name.upton.zest.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ctx-spring.xml");

        Bean b = ctx.getBean(Bean.class);
        Foo f = ctx.getBean(Foo.class);

        System.out.println(b.bb("123"));
        System.out.println(f.foo("xyz"));

        ctx.close();
    }

}
