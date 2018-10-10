package myLang;

import myLang.response.MyLangException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * Реализует динамическую загрузку класса по имени.
 * Предполагается что все классы храняться в папке {@code PEAT_TO_CLASSES} и её подпапках.
 *
 * @author Ivan Kylchik
 */
class DynamicClassLoader{

    private HashMap<String, String> classesPath;
    private final String WORKING_PACKAGE = "myLang";
    private final String PATH_TO_CLASSES = "./out/production/classes/" + WORKING_PACKAGE;

    DynamicClassLoader() {
        classesPath = new HashMap<>();
    }

    /**
     * Загружает класс по имени.
     *
     * @param name имя класса для загрузки
     * @return загруженный класс
     * @throws ClassNotFoundException если класс не был найден
     * @throws IOException если возникнут ошибки ввода/вывода при работе с файлами
     */
    Class<?> loadClass(String name) throws ClassNotFoundException, IOException, MyLangException{
        File currentDir = new File(PATH_TO_CLASSES);

        if(!classesPath.containsKey(name)) {
            findClass(currentDir, name, WORKING_PACKAGE);
        }
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:///" + PATH_TO_CLASSES)});
        return classLoader.loadClass(classesPath.get(name));
    }

    /**
     * Производит рекурсивный поиск класса по дереву каталогов, начиная с {@code PEAT_TO_CLASSES}.
     * Все найденные классы добавляются в {@code HashMap classesPath} для того чтобы не искать класс повторно.
     *
     * @param dir имя дерриктории для поиска
     * @param name имя класса для поиска
     * @param curPackage текущий пакет для поиска
     * @throws IOException если возникнут ошибки ввода/вывода при работе с файлами
     * @throws MyLangException если класс уже был найден ранее
     */
    private void findClass(File dir, String name, String curPackage) throws IOException, MyLangException {
        File[] files = dir.listFiles();
        if(files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String[] paths = file.getCanonicalPath().split("\\\\");
                    findClass(file, name, curPackage + "." + paths[paths.length - 1]);
                } else {
                    if (file.getName().equals(name + ".class")) {
                        if (classesPath.containsKey(name)) {
                            throw new MyLangException("Класс " + name + " объявлен дважды");
                        }
                        classesPath.put(name, curPackage + "." + name);
                    }
                }
            }
        }
    }
}
