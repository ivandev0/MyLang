import org.antlr.v4.runtime.ParserRuleContext;
import response.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

public interface ContextHandler<T extends ParserRuleContext>{

    Response handler(T ctx);

    default Response defaultHandler(T ctx){
        return defaultHandler(ctx, 0);
    }

    //TODO объснить почему static
    default Response defaultHandler(T ctx, int child){
        String className = ctx.getChild(child).getClass().getSimpleName();
        try {
            Class<?> clazz = Class.forName(className);

            Method method = clazz.getDeclaredMethod("handler", ctx.getChild(child).getClass());
            return (Response) method.invoke(clazz.newInstance(), ctx.getChild(child));

        } catch (ClassNotFoundException e) {
            System.err.println("ERROR block statement unknown");
            System.exit(1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassCastException e) {
            //так как класс реализует интерфейс то ошибка не возможна
            //illegal access недостаточно прав
            //TODO specify errors
            e.printStackTrace();
        } catch (NoSuchElementException e){
            //TODO specify element
            System.err.println("ERROR no such element");
            System.exit(1);
            e.printStackTrace();
        }

        //TODO without return
        return null;
    }
}