package upton.spring.order;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

public class MyPreInit implements BeanFactoryPostProcessor, PriorityOrdered {

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("pre init");

        ClassPool pool = ClassPool.getDefault();

        CtClass businessObjectCc;
        try {
            businessObjectCc = pool.get("upton.spring.order.A");

            CtConstructor[] cons = businessObjectCc.getConstructors();
            for (CtConstructor ctConstructor : cons) {
                ctConstructor.insertAfter("System.out.println(123);");
            }

            businessObjectCc.toClass();
        } catch (NotFoundException | CannotCompileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
