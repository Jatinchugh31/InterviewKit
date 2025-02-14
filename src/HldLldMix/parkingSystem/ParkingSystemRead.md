1. Requirements

   Functional Requirements
   Parking Lot Management:

Ability to park and un-park vehicles.
Support for multiple parking floors/levels.
Different parking spots for different vehicle types (e.g., car, bike, truck).
Ticketing System:

Generate a ticket when a vehicle parks.
Calculate parking fees when a vehicle leaves.
Support for payment processing.
Entry/Exit Management:

Check availability of parking spots.
Monitor occupancy on each floor.
Vehicle Types and Spot Types:

Various vehicle sizes (motorbike, car, truck) may require different spot sizes.
Reserved spots, handicapped spots, etc.
Non-Functional Requirements
Scalability: The system should handle many vehicles and large parking lots.
Maintainability: Easy to add or modify parking spot types, pricing rules, etc.
Extensibility: Support additional features like reservation systems, real-time availability tracking, etc.

2. High-Level Design
   Key Entities / Classes
   ParkingLot:
   Represents the entire parking facility.

ParkingFloor:
Represents a single floor in the parking lot; holds multiple parking spots.

ParkingSpot:
Represents an individual parking spot. It can be further subclassed based on spot type (e.g., CompactSpot, LargeSpot,
HandicappedSpot).

Vehicle:
Represents a vehicle. It can be subclassed for different vehicle types (e.g., Car, Motorcycle, Truck).

Ticket:
Represents a parking ticket issued when a vehicle parks. Contains details like entry time, assigned spot, etc.

Payment:
(Optional) Represents the payment information and transaction logic.

DisplayBoard / Sensor System:
(Optional) To show available spots and monitor occupancy.

3. Object-Oriented Design
   Below is an outline of the key classes with their responsibilities and possible methods:

A. Vehicle Hierarchy


public abstract class Vehicle {
private String licensePlate;
private VehicleSize size;
// Other common attributes

    public abstract boolean canFitInSpot(ParkingSpot spot);
}

public enum VehicleSize {
MOTORCYCLE, COMPACT, LARGE
}

public class Car extends Vehicle {
public Car(String licensePlate) {
// initialize licensePlate and size
}

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        // A car might fit in a compact spot or a large spot.
        return spot.getSize() == VehicleSize.COMPACT || spot.getSize() == VehicleSize.LARGE;
    }
}

public class Motorcycle extends Vehicle {
public Motorcycle(String licensePlate) { }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        // A motorcycle can fit in any spot.
        return true;
    }
}

public class Truck extends Vehicle {
public Truck(String licensePlate) { }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        // Truck may require a large spot.
        return spot.getSize() == VehicleSize.LARGE;
    }
}




B. ParkingSpot and Its Subclasses
public abstract class ParkingSpot {
private int spotNumber;
private VehicleSize size;
private boolean occupied;

    public ParkingSpot(int spotNumber, VehicleSize size) {
        this.spotNumber = spotNumber;
        this.size = size;
        this.occupied = false;
    }
    
    public boolean isAvailable() {
        return !occupied;
    }
    
    public void parkVehicle(Vehicle vehicle) {
        if (vehicle.canFitInSpot(this)) {
            occupied = true;
            // Associate vehicle with spot
        }
    }
    
    public void removeVehicle() {
        occupied = false;
        // Remove association
    }
    
    public VehicleSize getSize() {
        return size;
    }
    
    // Additional getters/setters
}

public class CompactSpot extends ParkingSpot {
public CompactSpot(int spotNumber) {
super(spotNumber, VehicleSize.COMPACT);
}
}

public class LargeSpot extends ParkingSpot {
public LargeSpot(int spotNumber) {
super(spotNumber, VehicleSize.LARGE);
}
}

// Similarly, you can have HandicappedSpot, ElectricSpot, etc.
C. ParkingFloor

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
private int floorNumber;
private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }
    
    // Find a free spot that can fit the given vehicle.
    public ParkingSpot getAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && vehicle.canFitInSpot(spot)) {
                return spot;
            }
        }
        return null;  // No available spot on this floor.
    }
    
    // Other helper methods (e.g., update availability, display board integration)
}



import java.util.List;

public class ParkingLot {
private List<ParkingFloor> floors;
// A mapping of ticket IDs to parked vehicles/spots can be maintained.

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }
    
    // Park a vehicle and return a ticket.
    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.getAvailableSpot(vehicle);
            if (spot != null) {
                spot.parkVehicle(vehicle);
                Ticket ticket = new Ticket(vehicle, spot, System.currentTimeMillis());
                // Save ticket to a data store or mapping for future reference.
                return ticket;
            }
        }
        return null;  // Parking lot is full.
    }
    
    // Remove vehicle from spot using ticket details and calculate fee.
    public boolean unparkVehicle(Ticket ticket) {
        ParkingSpot spot = ticket.getParkingSpot();
        spot.removeVehicle();
        // Calculate payment based on time parked, if required.
        return true;
    }
    
    // Methods for retrieving parking lot status, available spots, etc.
}


public class Ticket {
private String ticketId;  // Could be generated using a UUID.
private Vehicle vehicle;
private ParkingSpot parkingSpot;
private long entryTime;
// exitTime and fee can be added for complete functionality.

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot, long entryTime) {
        this.ticketId = java.util.UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }
    
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
    
    // Other getters and setters.
}


