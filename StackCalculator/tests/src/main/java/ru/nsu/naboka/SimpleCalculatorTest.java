package ru.nsu.naboka;


import java.io.*;
import java.util.List;

public class SimpleCalculatorTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("=== НАЧАЛО ТЕСТИРОВАНИЯ СТЕКОВОГО КАЛЬКУЛЯТОРА ===\n");

        // Тесты команд
        testPushCommand();
        testAddCommand();
        testSubCommand();
        testMulCommand();
        testDivCommand();
        testSqrtCommand();
        testPopCommand();
        testPrintCommand();
        testDefineCommand();
        testPushWithVariable();
        testComplexExpression();

        // Тесты файлового ввода
        testFileInput();
        testFileInputWithVariables();
        testFileWithComments();

        // Тесты ошибок
        testDivisionByZero();
        testNotEnoughArguments();
        testUndefinedVariable();

        System.out.println("\n=== РЕЗУЛЬТАТЫ ТЕСТИРОВАНИЯ ===");
        System.out.println("Пройдено тестов: " + testsPassed);
        System.out.println("Провалено тестов: " + testsFailed);
        System.out.println("Всего тестов: " + (testsPassed + testsFailed));

        if (testsFailed == 0) {
            System.out.println("\n ВСЕ ТЕСТЫ УСПЕШНО ВЫПОЛНЕНЫ!");
        } else {
            System.out.println("\n ЕСТЬ ПРОВАЛЕННЫЕ ТЕСТЫ!");
        }
    }

    private static void printTestResult(String testName, boolean passed) {
        if (passed) {
            System.out.println("✅ " + testName + " - ПРОЙДЕН");
            testsPassed++;
        } else {
            System.out.println("❌ " + testName + " - ПРОВАЛЕН");
            testsFailed++;
        }
    }

    // Тест 1: Команда PUSH
    private static void testPushCommand() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            push.execute(context, List.of("PUSH", "42.5"));
            boolean passed = context.getStack().size() == 1 && context.getStack().peek() == 42.5;
            printTestResult("Тест 1: PUSH числа", passed);
        } catch (Exception e) {
            printTestResult("Тест 1: PUSH числа", false);
        }
    }

    // Тест 2: Команда ADD
    private static void testAddCommand() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            AddCommand add = new AddCommand();
            push.execute(context, List.of("PUSH", "10"));
            push.execute(context, List.of("PUSH", "20"));
            add.execute(context, List.of("ADD"));
            boolean passed = context.getStack().size() == 1 && context.getStack().peek() == 30.0;
            printTestResult("Тест 2: ADD сложение", passed);
        } catch (Exception e) {
            printTestResult("Тест 2: ADD сложение", false);
        }
    }

    // Тест 3: Команда SUB
    private static void testSubCommand() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            SubCommand sub = new SubCommand();
            push.execute(context, List.of("PUSH", "30"));
            push.execute(context, List.of("PUSH", "10"));
            sub.execute(context, List.of("SUB"));
            boolean passed = context.getStack().peek() == 20.0;
            printTestResult("Тест 3: SUB вычитание", passed);
        } catch (Exception e) {
            printTestResult("Тест 3: SUB вычитание", false);
        }
    }

    // Тест 4: Команда MUL
    private static void testMulCommand() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            MulCommand mul = new MulCommand();
            push.execute(context, List.of("PUSH", "5"));
            push.execute(context, List.of("PUSH", "6"));
            mul.execute(context, List.of("MUL"));
            boolean passed = context.getStack().peek() == 30.0;
            printTestResult("Тест 4: MUL умножение", passed);
        } catch (Exception e) {
            printTestResult("Тест 4: MUL умножение", false);
        }
    }

    // Тест 5: Команда DIV
    private static void testDivCommand() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            DivCommand div = new DivCommand();
            push.execute(context, List.of("PUSH", "20"));
            push.execute(context, List.of("PUSH", "4"));
            div.execute(context, List.of("DIV"));
            boolean passed = context.getStack().peek() == 5.0;
            printTestResult("Тест 5: DIV деление", passed);
        } catch (Exception e) {
            printTestResult("Тест 5: DIV деление", false);
        }
    }

    // Тест 6: Команда SQRT
    private static void testSqrtCommand() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            SqrtCommand sqrt = new SqrtCommand();
            push.execute(context, List.of("PUSH", "16"));
            sqrt.execute(context, List.of("SQRT"));
            boolean passed = context.getStack().peek() == 4.0;
            printTestResult("Тест 6: SQRT корень", passed);
        } catch (Exception e) {
            printTestResult("Тест 6: SQRT корень", false);
        }
    }

    // Тест 7: Команда POP
    private static void testPopCommand() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            PopCommand pop = new PopCommand();
            push.execute(context, List.of("PUSH", "100"));
            push.execute(context, List.of("PUSH", "200"));
            pop.execute(context, List.of("POP"));
            boolean passed = context.getStack().size() == 1 && context.getStack().peek() == 100.0;
            printTestResult("Тест 7: POP удаление", passed);
        } catch (Exception e) {
            printTestResult("Тест 7: POP удаление", false);
        }
    }

    // Тест 8: Команда PRINT
    private static void testPrintCommand() {
        try {
            // Сохраняем оригинальный вывод
            PrintStream originalOut = System.out;

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            Context context = new Context();
            PushCommand push = new PushCommand();
            PrintCommand print = new PrintCommand();

            push.execute(context, List.of("PUSH", "123.45"));
            print.execute(context, List.of("PRINT"));

            boolean passed = outContent.toString().trim().equals("123.45");
            printTestResult("Тест 8: PRINT вывод", passed);

            // Восстанавливаем вывод
            System.setOut(originalOut);
        } catch (Exception e) {
            System.setOut(System.out);
            printTestResult("Тест 8: PRINT вывод", false);
            e.printStackTrace();
        }
    }

    // Тест 9: Команда DEFINE
    private static void testDefineCommand() {
        try {
            Context context = new Context();
            DefineCommand define = new DefineCommand();
            PushCommand push = new PushCommand();

            define.execute(context, List.of("DEFINE", "x", "15"));
            push.execute(context, List.of("PUSH", "x"));

            boolean passed = context.getMap().containsKey("x") &&
                    Math.abs(context.getMap().get("x") - 15.0) < 0.0001 &&
                    Math.abs(context.getStack().peek() - 15.0) < 0.0001;
            printTestResult("Тест 9: DEFINE переменная", passed);
        } catch (Exception e) {
            printTestResult("Тест 9: DEFINE переменная", false);
            e.printStackTrace();
        }
    }

    // Тест 10: PUSH с переменной
    private static void testPushWithVariable() {
        try {
            Context context = new Context();
            DefineCommand define = new DefineCommand();
            PushCommand push = new PushCommand();

            define.execute(context, List.of("DEFINE", "pi", "3.14"));
            push.execute(context, List.of("PUSH", "pi"));

            boolean passed = Math.abs(context.getStack().peek() - 3.14) < 0.0001;
            printTestResult("Тест 10: PUSH с переменной", passed);
        } catch (Exception e) {
            printTestResult("Тест 10: PUSH с переменной", false);
            e.printStackTrace();
        }
    }

    // Тест 11: Сложное выражение (2+3)*(4-1)
    private static void testComplexExpression() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            AddCommand add = new AddCommand();
            SubCommand sub = new SubCommand();
            MulCommand mul = new MulCommand();

            push.execute(context, List.of("PUSH", "2"));
            push.execute(context, List.of("PUSH", "3"));
            add.execute(context, List.of("ADD"));

            push.execute(context, List.of("PUSH", "4"));
            push.execute(context, List.of("PUSH", "1"));
            sub.execute(context, List.of("SUB"));

            mul.execute(context, List.of("MUL"));

            boolean passed = Math.abs(context.getStack().peek() - 15.0) < 0.0001;
            printTestResult("Тест 11: Сложное выражение", passed);
        } catch (Exception e) {
            printTestResult("Тест 11: Сложное выражение", false);
            e.printStackTrace();
        }
    }

    // Тест 12: Файловый ввод
    private static void testFileInput() {
        try {
            File tempFile = File.createTempFile("test_calc", ".txt");
            tempFile.deleteOnExit();

            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("PUSH 10\n");
                writer.write("PUSH 20\n");
                writer.write("ADD\n");
                writer.write("PRINT\n");
            }

            PrintStream originalOut = System.out;
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String[] args = {tempFile.getAbsolutePath()};
            Calculator calculator = new Calculator();
            Reader reader = new Reader(args);
            calculator.execute(reader);

            boolean passed = outContent.toString().contains("30.0");
            printTestResult("Тест 12: Файловый ввод", passed);

            System.setOut(originalOut);
        } catch (Exception e) {
            System.setOut(System.out);
            printTestResult("Тест 12: Файловый ввод", false);
            e.printStackTrace();
        }
    }

    // Тест 13: Файловый ввод с переменными
    private static void testFileInputWithVariables() {
        try {
            File tempFile = File.createTempFile("test_calc_var", ".txt");
            tempFile.deleteOnExit();

            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("DEFINE a 10\n");
                writer.write("DEFINE b 5\n");
                writer.write("PUSH a\n");
                writer.write("PUSH b\n");
                writer.write("ADD\n");
                writer.write("PRINT\n");
            }

            PrintStream originalOut = System.out;
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String[] args = {tempFile.getAbsolutePath()};
            Calculator calculator = new Calculator();
            Reader reader = new Reader(args);
            calculator.execute(reader);

            boolean passed = outContent.toString().contains("15.0");
            printTestResult("Тест 13: Файл с переменными", passed);

            System.setOut(originalOut);
        } catch (Exception e) {
            System.setOut(System.out);
            printTestResult("Тест 13: Файл с переменными", false);
            e.printStackTrace();
        }
    }

    // Тест 14: Файл с комментариями
    private static void testFileWithComments() {
        try {
            File tempFile = File.createTempFile("test_calc_comments", ".txt");
            tempFile.deleteOnExit();

            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("# Это комментарий\n");
                writer.write("PUSH 100\n");
                writer.write("PUSH 25\n");
                writer.write("# Еще комментарий\n");
                writer.write("DIV\n");
                writer.write("PRINT\n");
            }

            PrintStream originalOut = System.out;
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String[] args = {tempFile.getAbsolutePath()};
            Calculator calculator = new Calculator();
            Reader reader = new Reader(args);
            calculator.execute(reader);

            boolean passed = outContent.toString().contains("4.0");
            printTestResult("Тест 14: Файл с комментариями", passed);

            System.setOut(originalOut);
        } catch (Exception e) {
            System.setOut(System.out);
            printTestResult("Тест 14: Файл с комментариями", false);
            e.printStackTrace();
        }
    }

    // Тест 15: Деление на ноль
    private static void testDivisionByZero() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            DivCommand div = new DivCommand();

            push.execute(context, List.of("PUSH", "10"));
            push.execute(context, List.of("PUSH", "0"));

            boolean exceptionThrown = false;
            try {
                div.execute(context, List.of("DIV"));
            } catch (Exception e) {
                exceptionThrown = true;
            }

            printTestResult("Тест 15: Деление на ноль (ошибка)", exceptionThrown);
        } catch (Exception e) {
            printTestResult("Тест 15: Деление на ноль (ошибка)", false);
        }
    }

    // Тест 16: Недостаточно аргументов
    private static void testNotEnoughArguments() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();
            AddCommand add = new AddCommand();

            push.execute(context, List.of("PUSH", "5"));

            boolean exceptionThrown = false;
            try {
                add.execute(context, List.of("ADD"));
            } catch (Exception e) {
                exceptionThrown = true;
            }

            printTestResult("Тест 16: Недостаточно аргументов", exceptionThrown);
        } catch (Exception e) {
            printTestResult("Тест 16: Недостаточно аргументов", false);
        }
    }

    // Тест 17: Неопределенная переменная
    private static void testUndefinedVariable() {
        try {
            Context context = new Context();
            PushCommand push = new PushCommand();

            boolean exceptionThrown = false;
            try {
                push.execute(context, List.of("PUSH", "undefined_var"));
            } catch (Exception e) {
                exceptionThrown = true;
            }

            printTestResult("Тест 17: Неопределенная переменная", exceptionThrown);
        } catch (Exception e) {
            printTestResult("Тест 17: Неопределенная переменная", false);
        }
    }
}