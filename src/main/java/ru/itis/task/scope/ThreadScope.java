package ru.itis.task.scope;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.itis.task.ServletInitializer;
import ru.itis.task.TaskApplication;

import javax.servlet.ServletContext;
import java.applet.AppletContext;
import java.util.HashMap;
import java.util.Map;

@Component
@RequestScope
public class ThreadScope implements Scope {

    @Autowired
    BeanFactory beanFactory;

    private final ThreadLocal<Map<String, Object>> threadScope =
            new NamedThreadLocal<Map<String, Object>>(ThreadScope.class.getName()) {
                @Override
                protected Map<String, Object> initialValue() {
                    return new HashMap<String, Object>();
                }
            };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> scope = this.threadScope.get();
        Object object = scope.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            scope.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> scope = this.threadScope.get();
        return scope.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return Thread.currentThread().getName();
    }

    public void clear() {
        Map<String, Object> scope = this.threadScope.get();
        scope.clear();
    }
}