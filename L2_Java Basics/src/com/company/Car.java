package com.company;

public class Car extends Vehicle{

    private String engine;

//    public Car(){
//        super(1, "Honda City", "Honda", 30);
//    }

    public Car(int id, String name, String company, int speed){
        super(id, name, company, speed);
//        super();
//        super(id, name, company, speed + 2);
        System.out.println("In Car class constructor");
    }

//    public Car(int id, String name, String company, int speed, String engine){
//        super(id, name, company, speed);
//        this.engine = engine;
////        super();
////        super(id, name, company, speed + 2);
//        System.out.println("In Car class constructor");
//    }

    // Overriding means defining same function in parent and child class
    // Same function means function having same name and same no and type of args
    // Overriden functions cannot have different return types
    public int calculateSpeed(){
        System.out.println("In Car class: calculateSpeed function");
        int speedVehicle = super.calculateSpeed();
        int speedCar = this.speed;
        return super.calculateSpeed();
    }

    // Final functions cannot be overridden
//    public int calculateDistance(){
//        return 10;
//    }

    public static void main(String[] args) {
        Car car = new Car(2, "Swift", "Maruti Suzuki", 20);
        System.out.println(car.calculateSpeed());

//        Vehicle vehicle = new Vehicle(1, "Honda City", "Honda", 30);
//        System.out.println(vehicle.calculateSpeed());

        Vehicle v = new Car(2, "Swift", "Maruti Suzuki", 20);
//        v.calculateSpeed(); // This will give error in case when calculateSpeed function is removed from Vehicle class
        v.calculateSpeed();

        // Invoke the parent class' function and then execute child class function

//        Car c = new Vehicle(2, "Swift", "Maruti Suzuki", 20); // This os not possible since we can't define a parent with a reference of child
    }
}
