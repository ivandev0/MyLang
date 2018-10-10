package myLang.interpreter;

import myLang.response.MyLangException;

/**
 * Класс реализует простую абстракию для реализации переменных.
 * Содержит имя {@code name} и тип {@code T}.
 *
 * @param <T> тип переменной
 */
public class Variable<T> {
    private final int MAX_SYMBOL_COUNT = 40;
    private final String name;
    private T value;

    public Variable(String name, T value) throws MyLangException {
        if(name.length() > MAX_SYMBOL_COUNT){
            throw new MyLangException("Имя переменной " + name + " содержит больше " + MAX_SYMBOL_COUNT + " символов");
        }
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void updateValue(T newValue){
        value = newValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Variable){
            Variable variable = (Variable) obj;
            return name.equals(variable.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
