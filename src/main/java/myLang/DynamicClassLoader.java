package myLang;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * This class provides dynamic loading in the sense that the given class and
 * other changed classes are loaded from disk, not from cache.
 * Ensures most recent version is used for those classes.
 */
public class DynamicClassLoader extends ClassLoader{

    private HashMap<String, String> classesPath;

    public DynamicClassLoader() {
        classesPath = new HashMap<>();
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        File currentDir = new File(".\\out\\production\\classes\\myLang");
        try {
            if(!classesPath.containsKey(name)) {
                displayDirectoryContents(currentDir, name, "myLang");
            }

            URLClassLoader classLoader = new URLClassLoader(new URL[]{
                    new URL("file:///./out/production/classes/myLang/")});
            return classLoader.loadClass(classesPath.get(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (RuntimeException e){
            e.printStackTrace();
            System.exit(-1);
        }

        throw new ClassNotFoundException();
    }

    private void displayDirectoryContents(File dir, String name, String curPackage) throws IOException, RuntimeException{
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //System.out.println("directory:" + file.getCanonicalPath());
                String[] paths = file.getCanonicalPath().split("\\\\");
                displayDirectoryContents(file, name, curPackage + "." + paths[paths.length - 1]);
            } else {
                if (file.getName().equals(name + ".class")) {
                    if(classesPath.containsKey(name)){
                        throw new RuntimeException("Класс " + name + " объявлен дважды");
                    }
                    classesPath.put(name, curPackage + "." + name);
                }
                //System.out.println("     file:" + file.getCanonicalPath());
            }
        }
    }
}
