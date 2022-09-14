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


        task1Solution(RAW_DATA);
        task1Solution(null);

        System.out.println(task2Solution(new int[]{3, 4, 2, 7}, 10));
        System.out.println(task2Solution(new int[]{3, 4, 5, 1, 2}, 10));
        System.out.println(task2Solution(null, 10));

        System.out.println();

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false
        System.out.println(fuzzySearch(null, "cartwheel")); // false
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
            if (persons != null) {
            List<Person> uniquePersons = Arrays.stream(persons)
                    .filter(p -> Objects.nonNull(p.name))
                    .distinct()
                    .toList();

            Map<String, Integer> frequency = uniquePersons.stream()
                    .collect(Collectors.toMap(
                            e -> e.getName(),
                            e -> 1,
                            Integer::sum));

            frequency.forEach((k, v) -> System.out.println("Key: " + k + "\n" + "Value: " + v));
            System.out.println();
        }else {
                System.out.println("Error: check input data");
                System.out.println();
            }
    }


    /**
     * Task2
     * <p>
     * [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
     */
    public static String task2Solution(int[] sourceArray, int checkedValue) {
        if (sourceArray != null) {
            for (int i = 0; i < sourceArray.length; i++) {
                for (int j = i + 1; j < sourceArray.length; j++) {
                    if (sourceArray[i] + sourceArray[j] == checkedValue) {
                        return String.format("[%d, %d]\n", sourceArray[i], sourceArray[j]);
                    }
                }
            }return "No matches";
        }
        return "Error: check input data";

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
            if (pattern != null && input != null) {
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
            }
        System.out.println("Error: check input data");
            return false;
    }


}
