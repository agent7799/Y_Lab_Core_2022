package homework;

import java.util.*;
import java.util.function.Function;
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

    }


        /**
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        public static void task1Solution(Person[] people){
            Map<String, Integer> personMap = new HashMap<>();

            //filtering duplicates and sort by name
            List<Person> uniquePersons = Arrays.stream(RAW_DATA)
                    .filter(p -> Objects.nonNull(p.id) && Objects.nonNull(p.name))
                    .distinct()
                    .sorted(Comparator.comparing(Person::getName))
                    .toList();

//            get Map of names and frequency
//            for (Person p : uniquePersons) {
//                personMap.put(p.getName(), (int) uniquePersons
//                        .stream()
//                        .filter(person -> person.getName().equals(p.getName()))
//                        .count());
//            }

            uniquePersons
                    .stream()
                    .forEach(person -> {
                        personMap.put(person.getName(),
                                (int) uniquePersons.stream().filter(p -> p.getName().equals(person.getName())).count());
                            });

            uniquePersons.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));



            // printing Map
            for (Map.Entry<String, Integer> entry : personMap.entrySet()) {
                System.out.println("Key: " + entry.getKey());
                System.out.println("Value: " + entry.getValue());
            };

            System.out.println();



            System.out.println(uniquePersons.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

            uniquePersons.stream().forEach(person -> {
                                System.out.println(person.getName());
                                System.out.println(uniquePersons
                                        .stream()
                                        .filter(p -> p.getName().equals(person.getName()))
                                        .count());
                            });



        }


        /**
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */


        /**
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */


}
