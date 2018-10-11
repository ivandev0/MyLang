package myLangKotlin

import myLangKotlin.response.MyLangException

import java.io.File
import java.io.IOException
import java.net.URL
import java.net.URLClassLoader
import java.util.HashMap

/**
 * Реализует динамическую загрузку класса по имени.
 * Предполагается что все классы храняться в папке `PEAT_TO_CLASSES` и её подпапках.
 *
 * @author Ivan Kylchik
 */
internal class DynamicClassLoader {

    private var classesPath: MutableMap<String, String> = mutableMapOf()
    private val WORKING_PACKAGE = "myLangKotlin"
    private val PATH_TO_CLASSES = "./out/production/classes/$WORKING_PACKAGE"

    /**
     * Загружает класс по имени.
     *
     * @param name имя класса для загрузки
     * @return загруженный класс
     * @throws ClassNotFoundException если класс не был найден
     * @throws IOException если возникнут ошибки ввода/вывода при работе с файлами
     */
    @Throws(ClassNotFoundException::class, IOException::class, MyLangException::class)
    fun loadClass(name: String): Class<*> {
        val currentDir = File(PATH_TO_CLASSES)

        if (!classesPath.containsKey(name)) {
            findClass(currentDir, name, WORKING_PACKAGE)
        }
        val classLoader = URLClassLoader(arrayOf(URL("file:///$PATH_TO_CLASSES")))
        return classLoader.loadClass(classesPath[name])
    }

    /**
     * Производит рекурсивный поиск класса по дереву каталогов, начиная с `PEAT_TO_CLASSES`.
     * Все найденные классы добавляются в `HashMap classesPath` для того чтобы не искать класс повторно.
     *
     * @param dir имя дерриктории для поиска
     * @param name имя класса для поиска
     * @param curPackage текущий пакет для поиска
     * @throws IOException если возникнут ошибки ввода/вывода при работе с файлами
     * @throws MyLangException если класс уже был найден ранее
     */
    @Throws(IOException::class, MyLangException::class)
    private fun findClass(dir: File, name: String, curPackage: String) {
        val files = dir.listFiles()
        if (files != null) {
            for (file in files) {
                if (file.isDirectory) {
                    val paths = file.canonicalPath.replace("\\", "/").split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    findClass(file, name, curPackage + "." + paths[paths.size - 1])
                } else {
                    if (file.name == "$name.class") {
                        if (classesPath.containsKey(name)) {
                            throw MyLangException("Класс $name объявлен дважды")
                        }
                        classesPath[name] = "$curPackage.$name"
                    }
                }
            }
        }
    }
}
