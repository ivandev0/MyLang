# MyLang
В данном проекте реализован простой интерпретируемый Java-пододный
язык программирования. Для реализации парсера применялся фреймворк 
[ANTLR](http://www.antlr.org/), а интерпретатор был написан на языке
Java. Также имеется версия, написанная на Kotlin.

## Основные возможности
**1.** Точкой входа в программу является функция `main`.

**2.** Явно реализован только целочисленый тип `int`. Неявно существует
 еще тип `boolean` (применятеся в условиях) и тип `string` (применяется 
 в операторе print).
 
**3.** Реализованны 4 арифметические операции: 
   * сложение `+`
   * вычитание `-`
   * умножение `*`
   * деление `/` - возвращает целое число, 
    округленное в меньшую сторону.
    
**4.** Реализованы операторы присваивания `=`
и составного присваивания `+=`, `-=`, `*=`, `/=`.
Данные операторы возвращают новое вычисленное значение 
и могут применяться каскадно с оператором присваивания.

**5.** Логические операторы `&&`, `||`, `>`, `>=`,
`<`, `<=`, `==`, `!=`. Принцип их работы аналогичен логическим 
операторам в Java.

**6.** Вышеописанные операторы могут быть занесены в скобки
 для указания порядка их выполнения.
 
**7.** Поддерживается оператор `if-then` со следующим синтаксисом
```
if (  expression  )  statement
```
где `expression` обязательно логического типа, а `statement` представляет 
собой один из перечисленных здесь операторов, либо набор операторов
 заключенных в фигурные скобки.
 
**8.** Поддерживается оператор `if-then-else` со следующим синтаксисом
 ```
 if (  expression  )  statement  else  statement
 ```
Данный оператор может быть применен для каскадного if.

**9.** Из циклов реализован цикл `for` со следующим синтаксисом
```
for ( init-expression ; cond-expression ; loop-expression )statement
```
где `init-expression` - набор переменны, определяющих инициализацию
цикла; `cond-expression` - условие проверяемое на каждом шаге цикла,
если условие ложно то происходит выход из цикла; `loop-expression` -
набор операторов выполняемых после каждого шага. Все и каждое в отдельности
условия могут быть опущены.

**10.** Реализован оператор `print` для поддержки вывода на консоль.
Основные формы оператора:
   * `print expression` - выводи на экран значение выражение `expression`
   * `print STRING` - выводит на экран строку `STRING`
   * `print STRING : expression` - выводит на экран значение в виде `STRING expression`

После каждого вызова оператора `print` происходит перенос строки.
В конце каждого выражения в теле оперетора можно поставить запятую
и продолжить запись следуюющего тела оператора. В данном случае не будет 
происзводиться перенос строки, все значения будут записаны через пробел.
Пример `print "x=", 1, "y=" : 2 ;` 

**11.** Имеется возможность объявления и вызова функций. Общий вид объявления функции
```
result-type fun-name (argument-list) statement-block
``` 
При объявлении функции, в качестве параметров должны быть указаны через запятую все
принимаемые аргументы с их типами (т.к. реализован только тип `int` то
все агрументы должны быть этого типа). Перед названием функции должен быть 
объявлен возвращаемый тип: либо `int`, либо `void`.

Вызов функции совпадает с вызовов Java методов. 

## Требования к коду
* Функция `main` должна быть объявена первой функцией в файле
* Функция `main` должна возвращать тип `int` и не должна принимать аргументов
* Нельзя вызывать функцию `main` внутри другой функции или внутри самой `main`
* Объявление всех функций должно происходить вне всех остальных функций.
* Если функция объявляет о возвращении типа `int` она должна его явно вернуть.
* Если функция возвращает 'void', то вызывать явно `return` не обязательно,
а также функция не имеет право вернуть тип `int`.
* Метод перегрузки функции не реализован. Т.е. если в программе будет 
2 функции с одинаковым именем но разным количеством параметров, то
интерпретатор будет использовать ту, что объявлена первой.
* Реализовано ограничение на число вложенных функций, равное 100.
* Размер на число символов в переменной и в названии функций - 40.
* Тип `int` имеет размер в 32 бита. 