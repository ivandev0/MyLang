package myLang.interpreter;

public class Variable<T> {
    private final String name;
    private T value;

    public Variable(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void validate(){

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

    @Override
    public String toString() {
        return "myLang.interpreter.Variable{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
