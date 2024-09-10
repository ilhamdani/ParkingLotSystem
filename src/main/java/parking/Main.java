package parking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main class that handles input commands and interacts with the ParkingLot class.
 */
public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] commands = line.split(" ");
                String command = commands[0].toUpperCase();

                switch (command) {
                    case "CREATE":
                        if (commands.length == 2) {
                            int size = Integer.parseInt(commands[1]);
                            parkingLot.initialize(size);
                            System.out.println("Created parking lot with " + size + " slots");
                        } else {
                            System.out.println("Invalid command: " + line);
                        }
                        break;

                    case "PARK":
                        if (commands.length == 2) {
                            parkingLot.parkCar(commands[1]);
                        } else {
                            System.out.println("Invalid command: " + line);
                        }
                        break;

                    case "LEAVE":
                        if (commands.length == 3) {
                            try {
                                String carNumber = commands[1];
                                int hours = Integer.parseInt(commands[2]);
                                parkingLot.leaveCar(carNumber, hours);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format for hours: " + commands[2]);
                            }
                        } else {
                            System.out.println("Invalid command: " + line);
                        }
                        break;

                    case "STATUS":
                        parkingLot.printStatus();
                        break;

                    default:
                        System.out.println("Invalid command: " + command);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
