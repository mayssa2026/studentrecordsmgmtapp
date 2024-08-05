package edu.miu.cs.cs425.studentrecordsmgmtapp;

import edu.miu.cs.cs425.studentrecordsmgmtapp.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class StudentrecordsmgmtappApplication {

	public static void main(String[] args) {
		Student[] students = {
				new Student("111", "David", LocalDate.of(1990, 11, 18)),
				new Student("222", "Mike", LocalDate.of(1991, 12, 7)),
				new Student("333", "Erique", LocalDate.of(1992, 1, 31)),
				new Student("444", "Carla", LocalDate.of(1993, 8, 22)),
		};

		printListOfStudents(students);
		
		List<Student> platinumAlumni = getListOfPlatinumAlumniStudents(students);
		System.out.println("\nPlatinum Alumni Students:");
		platinumAlumni.forEach(System.out::println);
	}

	public static void printListOfStudents(Student[] students) {
		Arrays.stream(students)
				.sorted(Comparator.comparing(Student::getName))
				.forEach(System.out::println);
	}

	public static List<Student> getListOfPlatinumAlumniStudents(Student[] students) {
		LocalDate thirtyYearsAgo = LocalDate.now().minusYears(30);
		return Arrays.stream(students)
				.filter(student -> student.getDateOfAdmission().isBefore(thirtyYearsAgo))
				.sorted(Comparator.comparing(Student::getDateOfAdmission).reversed())
				.collect(Collectors.toList());
	}

	public static void printHelloWorld(int[] numbers) {
		for (int number : numbers) {
			if (number % 5 == 0 && number % 7 == 0) {
				System.out.println("HelloWorld");
			} else if (number % 5 == 0) {
				System.out.println("Hello");
			} else if (number % 7 == 0) {
				System.out.println("World");
			}
		}
	}

	public static int findSecondBiggest(int[] numbers) {
		if (numbers == null || numbers.length < 2) {
			throw new IllegalArgumentException("the array must contain at least two elements.");
		}

		int firstBiggest = Integer.MIN_VALUE;
		int secondBiggest = Integer.MIN_VALUE;

		for (int number : numbers) {
			if (number > firstBiggest) {
				secondBiggest = firstBiggest;
				firstBiggest = number;
			} else if (number > secondBiggest && number != firstBiggest) {
				secondBiggest = number;
			}
		}

		if (secondBiggest == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("the array does not have a second biggest element.");
		}

		return secondBiggest;
	}

}
