package myLang.interpreter;

public class FunArgs{
    final String type, name;

    FunArgs(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "myLang.interpreter.FunArgs{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
