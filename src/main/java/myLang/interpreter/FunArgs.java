package myLang.interpreter;

/**
 * Класс обертка, хранит в себе информация об аргументаъ метода.
 */
public class FunArgs{
    private final String type, name;

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

}
