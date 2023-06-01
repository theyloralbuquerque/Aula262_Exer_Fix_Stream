package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o caminho do arquivo: ");
		String caminho = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))){
			
			List<Employee> list = new ArrayList<>(); // Criação da Lista list do tipo Employee.
			
			String linha = br.readLine(); // Leitura da primeira linha do arquivo. 
			while (linha != null) {
				String[] campos = linha.split(",");
				
				list.add(new Employee(campos[0],campos[1], Double.parseDouble(campos[2]))); // Acrescenta direto na list um objeto Employees com os valores dos campos
				linha = br.readLine(); // Leitura da linha seguinte do arquivo.
			}
			
			System.out.print("Digite o valor do salário: R$ ");
			double value_salary = sc.nextDouble();
			
			System.out.println("E-mail das pessoas que tem o salário maior que R$ " + value_salary + ": ");
			
			List<String> emails = list.stream() // Criação de uma Lista emails do tipo String a partir do retorno de list convertida em stream e convertida em Lista novamente, 
					.filter(e -> e.getSalary() > value_salary)
					.map(e -> e.getEmail())
					.sorted() // Ordena os elementos da Lista em ordem crescente.
					.collect(Collectors.toList());
					
			emails.forEach(System.out::println);
			
			Double soma = list.stream() // Criação variável soma do tipo Double que recebe o valor de uma lista convertida em stream.
					.filter(e -> e.getName().charAt(0) == 'M') // Filtragem de cada objeto da Employee da list pelo caractere 0 igual a 'M'.
					.map(e -> e.getSalary()) // Para cada Employee e da minha list eu vou querer utilizar apenas o getSalary().
					.reduce(0.0, (x, y) -> x + y); // Redução dos valores de salary, que serão somados e serão um valor somente. 
				
					
			System.out.println("A Soma dos salários das pessoas que começam com a letra 'M' é: R$ " + String.format("%.2f", soma) );
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());;
		}
		sc.close();
	}
}