package com.EasyStreet;

/*
Задано список, що містить дані про працівників підприємства (прізвище, посада, дата зарахування).
Створити карту з ключем дата зарахування та значенням –
списку всіх прізвищ осіб, що мають таку дату зарахування. Кожен список посортувати за посадами.
У попередній таблиці вилучити всіх осіб, для яких на біжучу дату зарахування відбулось більше 10 років назад.
З 2 різних файлів зчитати 2 вихідні набори інформації про працівників. Створити спільну колекцію, що не містить повторів прізвищ.
Порахувати частотну таблицю людей з заданою посадою.
*/

import javafx.geometry.Pos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        EmployeesController controller = new EmployeesController();
        controller.initMap();

        int option = 0;

        while(true) {
            System.out.println("\n1 - Sort employees\n" +
                    "2 - Count employees at position\n" +
                    "3 - Delete employees with experience\n" +
                    "4 - Get employees with date\n" +
                    "5 - Print employees\n" +
                    "6 - Print employees surnames\n");

            option = sc.nextInt();

            if(option == 1){
                controller.sortEmployeesInEachRow();
                System.out.println("Sorted employees list:");
                controller.printEmployeesSurnamesMap();
            }
            else if(option == 2){

                int cnt=1;
                for (Position p: Position.values()) {
                    System.out.println(String.valueOf(cnt) + " - "+p.name());
                    cnt++;
                }

                System.out.println("Position: ");
                int position;
                position = sc.nextInt();

                int count = controller.countEmployees(Position.values()[position-1]);
                System.out.println("Employees: "+String.valueOf(count));
            }
            else if(option == 3){
                System.out.println("Years: ");
                int years;
                years = sc.nextInt();
                Map<LocalDate, List<Employee>> employees = controller.deleteEmployeesWithExperience(years);
                System.out.println("Employees:");
                EmployeesController.printEmployeesMap(employees);
            }
            else if(option == 4){
                System.out.println("Date: ");
                String date;
                date = sc.next();

                Map<LocalDate, List<Employee>> employees = controller.getEmployeesByDate(date);
                System.out.println("Employees:");
                EmployeesController.printEmployeesMap(employees);
            }
            else if(option == 5){
                System.out.println("All employees");
                controller.printEmployeesMap();
            }
            else if(option == 6){
                System.out.println("Employees surnames");
                controller.printEmployeesSurnamesMap();
            }
            else{
                break;
            }
        }

    }
}
