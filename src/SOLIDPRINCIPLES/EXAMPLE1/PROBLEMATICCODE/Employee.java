package SOLIDPRINCIPLES.EXAMPLE1.PROBLEMATICCODE;

public class Employee {
    private String name;
    private int id;
    private String address;

    public Employee(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void printPerformanceReport(){
        // code to calculate report
        System.out.println("Performance report of employee: "+name);
    }

    public double computeSalary(){
        // some algo to compute salary
        return 1000.0;
    }

    public void updateEmployeeData(){
        // code to update data;
        System.out.println("Employee data is updated successfully");
    }

    public void fetchEmloyeeData(){
        // code to fetch employee data
        System.out.println("Employee data fetch successfully");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
