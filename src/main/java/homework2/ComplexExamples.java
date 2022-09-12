package homework2;

import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
            //new Person(9, null),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();


        task1Solution(null);

        int[] ints = {3, 4, 2, 7};
        System.out.println(task2Solution(ints, 10));
        //System.out.println(task2Solution(null, 10));

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false
        //System.out.println(fuzzySearch(null, "cartwheel")); // false
    }


    /**
     * Task1
     * Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени
     * <p>
     * Что должно получиться
     * Key: Amelia
     * Value:4
     * Key: Emily
     * Value:1
     * Key: Harry
     * Value:3
     * Key: Jack
     * Value:1
     */
    public static void task1Solution(Person[] persons) {

        try {
            if (persons == null) {
                throw new NullPointerException();
            }

            List<Person> uniquePersons = Arrays.stream(persons)
                    //check for null and ignore
                    .filter(p -> Objects.nonNull(p.name))
                    .distinct()
                    .toList();

//        uniquePersons.forEach(person -> System.out.println(person.getName() + " " + person.getId()));

            Map<String, Integer> frequency = uniquePersons.stream()
                    .collect(Collectors.toMap(
                            e -> e.getName(),
                            e -> 1,
                            Integer::sum));

            frequency.forEach((k, v) -> System.out.println("Key: " + k + "\n" + "Value: " + v));

            System.out.println();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("task1Solution NullPointerException: wrong input data ");

        }
    }


    /**
     * Task2
     * <p>
     * [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
     */
    public static String task2Solution(int[] sourceArray, int checkedValue) {
        try {
            if (sourceArray == null) {
                throw new NullPointerException();
            } else {
                for (int i = 0; i < sourceArray.length; i++) {
                    for (int j = i + 1; j < sourceArray.length; j++) {
                        if (sourceArray[i] + sourceArray[j] == checkedValue) {
                            //System.out.println("[" + sourceArray[i] + ", " + sourceArray[j] + "]");
                            return String.format("[%d, %d]\n", sourceArray[i], sourceArray[j]);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("task2Solution NullPointerException: wrong input data ");
        } finally {
            System.out.println("");
        }
        return "No matches";
    }

    /**
     * Task3
     * Реализовать функцию нечеткого поиска
     * <p>
     * fuzzySearch("car", "ca6$$#_rtwheel"); // true
     * fuzzySearch("cwhl", "cartwheel"); // true
     * fuzzySearch("cwhee", "cartwheel"); // true
     * fuzzySearch("cartwheel", "cartwheel"); // true
     * fuzzySearch("cwheeel", "cartwheel"); // false
     * fuzzySearch("lw", "cartwheel"); // false
     */
    public static boolean fuzzySearch(String pattern, String input) {
        try {
            if (pattern == null || input == null) {
                throw new NullPointerException();
            }
            char ch;
            int indexFound;
            StringBuilder sb = new StringBuilder(input);
            for (int i = 0; i < pattern.length(); i++) {
                ch = pattern.charAt(i);
                indexFound = sb.toString().indexOf(ch);
                if (indexFound >= 0) {
                    sb = new StringBuilder(sb.substring(indexFound + 1));
                } else return false;
            }
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("fuzzySearch NullPointerException: wrong input data ");
            return false;
        }
    }


}
