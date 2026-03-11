import java.util.*;

public class DungeonMap {
    Map<Integer, Room> rooms;

    DungeonMap() {
        rooms = new HashMap<>();
    }

    void addRoom(Room room) {
        rooms.put(room.id, room);
    }

    void connectRooms(int room1Id, int room2Id, int dangerLevel) {
        Room room1 = rooms.get(room1Id);
        Room room2 = rooms.get(room2Id);
        if (room1 != null && room2 != null) {
            room1.addNeighbor(room2, dangerLevel);
            room2.addNeighbor(room1, dangerLevel); // Assuming undirected graph
        }
    }

    List<Room> findSafestPath(int startId, int endId) {
        Map<Room, Integer> dangerLevels = new HashMap<>();
        Map<Room, Room> prev = new HashMap<>();
        PriorityQueue<Room> pq = new PriorityQueue<>(Comparator.comparingInt(dangerLevels::get));

        Room start = rooms.get(startId);
        Room end = rooms.get(endId);

        if (start == null || end == null) return null;

        for (Room room : rooms.values()) {
            dangerLevels.put(room, Integer.MAX_VALUE);
        }
        dangerLevels.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Room current = pq.poll();
            for (Map.Entry<Room, Integer> entry : current.neighbors.entrySet()) {
                Room neighbor = entry.getKey();
                int danger = entry.getValue();
                int newDanger = dangerLevels.get(current) + danger;
                if (newDanger < dangerLevels.get(neighbor)) {
                    dangerLevels.put(neighbor, newDanger);
                    prev.put(neighbor, current);
                    pq.remove(neighbor); // Reinsert to update priority
                    pq.add(neighbor);
                }
            }
        }

        List<Room> path = new LinkedList<>();
        if (dangerLevels.get(end) == Integer.MAX_VALUE) return null; // No path
        for (Room at = end; at != null; at = prev.get(at)) {
            path.addFirst(at);
        }
        return path;
    }

    int getTotalDanger(List<Room> path) {
        if (path == null || path.size() < 2) return 0;
        int total = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            total += path.get(i).neighbors.get(path.get(i + 1));
        }
        return total;
    }
}