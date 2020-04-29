package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		List<Employee> employeesList = new ArrayList<>();
		
		//C:\tmp\ws-eclipse\JavaCourse51_Stream\in.csv
		
		System.out.print("Enter full file path: ");
		String path = input.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while(line != null) {
				
				String[] lineParts = line.split(",");
				String name = lineParts[0];
				String email = lineParts[1];
				double salary = Double.parseDouble(lineParts[2]);
				employeesList.add(new Employee(name, email, salary));
				
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double limitSalary = input.nextDouble();
			
			List<String> emails = employeesList.stream()
					.filter(x -> x.getSalary() >= limitSalary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			for (String employeesEmails : emails) {
				System.out.println(employeesEmails);
			}
			
			List<Double> salaries = employeesList.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.collect(Collectors.toList());
			
			double sum = 0.0;
			for (Double employeesSalaries : salaries) {
				sum += employeesSalaries;
			}
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} 
		catch (IOException e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
		
		input.close();
		
	}

}
