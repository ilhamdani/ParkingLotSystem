package parking;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Class to represent the ParkingLot.
 * This class manages car parking, car leaving, and parking status.
 */
public class ParkingLot {
    private int capacity;
    private PriorityQueue<Integer> availableSlots;
    private Map<String, Integer> parkedCars;

    public ParkingLot() {
        // Empty constructor
    }

    /**
     * Initialize the parking lot with a given capacity.
     * @param capacity Maximum number of parking slots.
     */
    public void initialize(int capacity) {
        this.capacity = capacity;
        this.availableSlots = new PriorityQueue<>();
        this.parkedCars = new HashMap<>();

        // Initialize available slots from 1 to the capacity
        for (int i = 1; i <= capacity; i++) {
            availableSlots.add(i);
        }
    }

    /**
     * Parks a car in the nearest available slot.
     * @param carNumber Car registration number.
     */
    public void parkCar(String carNumber) {
        if (availableSlots.isEmpty()) {
            System.out.println("Sorry, parking lot is full");
            return;
        }

        int slot = availableSlots.poll();
        parkedCars.put(carNumber, slot);
        System.out.println("Allocated slot number: " + slot);
    }

    /**
     * Removes a car from the parking lot and calculates the charge based on hours parked.
     * @param carNumber Car registration number.
     * @param hours Number of hours the car was parked.
     */
    public void leaveCar(String carNumber, int hours) {
        if (!parkedCars.containsKey(carNumber)) {
            System.out.println("Registration Number " + carNumber + " not found");
            return;
        }

        int slot = parkedCars.remove(carNumber);
        availableSlots.add(slot);
        int charge = calculateCharge(hours);
        System.out.println("Registration Number " + carNumber + " from Slot " + slot + " has left with Charge " + charge);
    }

    /**
     * Prints the current status of the parking lot.
     */
    public void printStatus() {
        if (parkedCars.isEmpty()) {
            System.out.println("Parking lot is empty.");
            return;
        }

        System.out.println("Slot No.    Registration No.");
        parkedCars.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getValue() + "           " + entry.getKey()));
    }

    /**
     * Private helper method to calculate the parking charge.
     * @param hours Number of hours parked.
     * @return Parking charge.
     */
    private int calculateCharge(int hours) {
        if (hours <= 2) {
            return 10; // Charge is $10 for up to 2 hours
        }
        return 10 + (hours - 2) * 10; // $10 for each additional hour
    }
}
