import org.antlr.v4.runtime.ParserRuleContext;
import response.EmptyResponse;
import response.MyLangException;
import response.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

public interface ContextHandler<T extends ParserRuleContext>{

    Response handler(T ctx) throws MyLangException;

    default Response defaultHandler(T ctx)  throws MyLangException{
        return defaultHandler(ctx, 0);
    }

    //TODO объснить почему static
    default Response defaultHandler(T ctx, int child) throws MyLangException {
        String className = ctx.getChild(child).getClass().getSimpleName();
        try {
            Class<?> clazz = Class.forName(className);

            Method method = clazz.getDeclaredMethod("handler", ctx.getChild(child).getClass());
            return (Response) method.invoke(clazz.newInstance(), ctx.getChild(child));

        } catch (ClassNotFoundException e) {
            System.err.println("ERROR block statement unknown");
            System.exit(1);
        } catch (NoSuchMethodException e) {
            //так как класс реализует интерфейс то ошибка не возможна
            //TODO specify errors
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //TODO specify errors
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            //e.printStackTrace();
            throw new MyLangException(e.getTargetException().getMessage());
        } catch (InstantiationException e) {
            //TODO specify errors
            e.printStackTrace();
        } catch (ClassCastException e) {
            //TODO specify errors
            e.printStackTrace();
        }

        return new EmptyResponse();
    }
}