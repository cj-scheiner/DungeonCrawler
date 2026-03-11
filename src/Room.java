import java.util.*;

public class Room {
    int id;
    Map<Room, Integer> neighbors;

    Room(int id) {
        this.id = id;
        this.neighbors = new HashMap<>();
    }

    void addNeighbor(Room neighbor, int dangerLevel) {
        neighbors.put(neighbor, dangerLevel);
    }
}