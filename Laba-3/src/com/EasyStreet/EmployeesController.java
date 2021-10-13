package com.EasyStreet;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeesController {

    private Map<LocalDate, List<String>> employeesSurnames;
    private Map<LocalDate, List<Employee>> employees;

    public LinkedList<Employee> readFromFile(File file){
        try {
            LinkedList<Employee> items = new LinkedList<Employee>();
            Scanner fread = new Scanner(file);

            while (fread.hasNext()) {
                items.add(Employee.readFromFile(fread));
            }
            fread.close();

            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
            return null;
        }
    }

    public Map<LocalDate, List<String>> buildSurnamesListFromEmployeesList(){
        for(Map.Entry<LocalDate, List<Employee>> entry : employees.entrySet()) {
            LocalDate date = entry.getKey();
            employeesSurnames.put(date, entry.getValue().stream().map(e-> e.getSurname()).collect(Collectors.toList()));
        }

        return this.employeesSurnames;
    }

    public Map<LocalDate, List<String>> initMap(){
        LinkedList<Employee> employeesList = this.readFromFile(new File("employees_1.txt"));
        employeesList.addAll(this.readFromFile(new File("employees_2.txt")));
        this.employeesSurnames = new HashMap<>();
        employees =  employeesList.stream().collect(Collectors.groupingBy(e -> e.getEnrollmentDate()));

        return this.buildSurnamesListFromEmployeesList();
    }

    public Map<LocalDate, List<Employee>> getEmployeesByDate(String date) {
        LocalDate ldate = LocalDate.parse(date);
        Map<LocalDate, List<Employee>> res = new HashMap<>();

        res = employees.entrySet().stream()
                .filter(a->a.getValue()
                        .stream()
                        .anyMatch(l->l.getEnrollmentDate().equals(ldate)))
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));

        return res;
    }

    public void sortEmployeesInEachRow(){
        for (Map.Entry<LocalDate, List<Employee>> entry : employees.entrySet()) {
            entry.getValue().sort((Employee e1, Employee e2)->e1.getPosition().name().compareTo(e2.getPosition().name()));
        }
    }

    public Map<LocalDate, List<Employee>> deleteEmployeesWithExperience(int yearsOfExperience){
        Map<LocalDate, List<Employee>> res = new HashMap<>();
        res = employees.entrySet().stream()
                .filter(a->a.getValue()
                        .stream()
                        .anyMatch(l->l.getEnrollmentDate().getYear() + yearsOfExperience > 2021))
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));

        return res;
    }

    public int countEmployees(Position position){
        int cnt = 0;

        for (Map.Entry<LocalDate, List<Employee>> entry : employees.entrySet()) {
            for (Employee employee : entry.getValue()){
                if(employee.getPosition().equals(position)){
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public void printEmployeesMap(){
        for (Map.Entry<LocalDate, List<Employee>> entry : employees.entrySet()) {
            System.out.println();
            System.out.println("======================================================================");
            System.out.println("\t"+entry.getKey());
            System.out.println("----------------------------------------------------------------------");
            for (Employee employee : entry.getValue()){
                System.out.println(employee.getSurname()+"\t"+String.valueOf(employee.getPosition()) + "\t" + employee.getEnrollmentDate().toString());
            }
            System.out.println("======================================================================");
        }
    }

    public void printEmployeesSurnamesMap(){
        for (Map.Entry<LocalDate, List<String>> entry : employeesSurnames.entrySet()) {
            System.out.println();
            System.out.println("==========================================");
            System.out.println(entry.getKey());
            System.out.println("------------------------------------------");
            for (String employeeSurname : entry.getValue()){
                System.out.println(employeeSurname);
            }
            System.out.println("==========================================");
        }
    }

    public static void printEmployeesMap(Map<LocalDate, List<Employee>> map){
        for (Map.Entry<LocalDate, List<Employee>> entry : map.entrySet()) {
            System.out.println();
            System.out.println("==========================================");
            System.out.println(entry.getKey());
            System.out.println("------------------------------------------");
            for (Employee employee : entry.getValue()){
                System.out.println(employee.getSurname()+"\t"+String.valueOf(employee.getPosition()) + "\t" + employee.getEnrollmentDate().toString());
            }
            System.out.println("==========================================");
        }
    }
}
