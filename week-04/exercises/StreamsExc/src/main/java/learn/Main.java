package learn;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        StudentDataStore ds = new StudentDataStore();
        List<Student> students = ds.all();

        // 0. Print all students
        // iteration solution
        for (Student student : students) {
            System.out.println(student);
        }

        // stream solution
        students.stream().forEach(System.out::println);

        // 1. Print students from Argentina

        students.stream().filter(student -> student.getCountry().equalsIgnoreCase("Argentina"))
                .forEach(System.out::println);

        // 2. Print students whose last names starts with 'T'.

        students.stream().filter(student -> student.getLastName().startsWith("T"))
                .forEach(System.out::println);

        // 3. Print students from Argentina, ordered by GPA

        students.stream()
                .sorted((a,b) -> a.getGpa().intValue() - b.getGpa().intValue())
                .forEach(System.out::println);

        // 4. Print the bottom 10% (100 students) ranked by GPA.
        students.stream()
                .sorted((a,b) -> a.getGpa().intValue() - b.getGpa().intValue())
                .skip(90)
                .forEach(System.out::println);

        // 5. Print the 4th - 6th ranked students by GPA from Argentina
        List<Student> studentfromArgentina = students.stream()
                .filter(student -> student.getCountry().equalsIgnoreCase("Argentina"))
                .sorted((a,b) -> a.getGpa().intValue() - b.getGpa().intValue())
                .collect(Collectors.toList());
        System.out.println(studentfromArgentina.get(3));
        System.out.println(studentfromArgentina.get(4));
        System.out.println(studentfromArgentina.get(5));

        /*students.stream()
                .filter(student -> student.getCountry().equalsIgnoreCase("Argentina"))
                .sorted(Comparator)*/


        // 6. Is anyone from Maldives?
        boolean yesorNo = students.stream()
                .anyMatch(student -> student.getCountry().equalsIgnoreCase("Maldives"));
        System.out.println(yesorNo);
        // 7. Does everyone have a non-null, non-empty email address?
        boolean notEmpty = students.stream()
                .allMatch(student -> student.getEmailAddress() != null);
        System.out.println(notEmpty);

        // 8. Print students who are currently registered for 5 courses.

        students.stream()
                .filter(student -> student.getRegistrations().stream().count() == 5)
                .forEach(System.out::println);

        // 9. Print students2 who are registered for the course "Literary Genres".

        students.stream()
                .filter(student -> student.getRegistrations()
                        .stream().anyMatch(registration -> registration.getCourse()
                                .equalsIgnoreCase("literary genres")))
                .forEach(System.out::println);

        // 10. Who has the latest birthday? Who is the youngest?
        students.stream()
                .max(Comparator.comparing(Student::getBirthDate))
                .ifPresent(System.out::println);

        // 11. Who has the highest GPA? There may be a tie.

        BigDecimal maxGpa = students.stream()
                .map(i -> i.getGpa())
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(BigDecimal.ZERO);

        students.stream()
                .max(Comparator.comparing(Student::getGpa))
                .ifPresent(System.out::println);


        // 12. Print every course students are registered for, including repeats.
        students.stream()
                .flatMap(student -> student.getRegistrations().stream())
                .map(Registration::getCourse)
                .forEach(System.out::println);

        // 13. Print a distinct list of courses students are registered for.

        // 14. Print a distinct list of courses students are registered for, ordered by name.

        // 15. Count students per country.

        // 16. Count students per country. Order by most to fewest students.

        students.stream()
                .collect(Collectors.groupingBy(Student::getCountry))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getKey()))
                .forEach(System.out::println);

        //Look up entryset
        //Map
        //flatMap

        // 17. Count registrations per course.

        // 18. How many registrations are not graded (GradeType == AUDIT)?

        // 19. Create a new type, StudentSummary with fields for Country, Major, and IQ.
        //     Map Students to StudentSummary, then sort and limit by IQ (your choice of low or high).



        // 20. What is the average GPA per country (remember, it's random fictional data).

        students.stream()
                .collect(Collectors.groupingBy(Student::getCountry, Collectors.
                                averagingDouble((student -> student.getGpa().doubleValue()))))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing((Map.Entry<String,Double> entry) -> entry.getValue()).reversed())
                .forEach(System.out::println);

        // 21. What is the maximum GPA per country?

        // 22. Print average IQ per Major ordered by IQ ascending.

        // 23. STRETCH GOAL!
        // Who has the highest pointPercent in "Sacred Writing"?
    }
}
