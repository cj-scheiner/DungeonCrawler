import java.util.*;

public class DungeonCrawler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DungeonMap dungeon = new DungeonMap();

        System.out.print("Enter input file name: ");
        String filename = scanner.nextLine();

        try (Scanner fileScanner = new Scanner(new java.io.File(filename))) {
            int numRooms = Integer.parseInt(fileScanner.nextLine());
            for (int i = 0; i < numRooms; i++) {
                String[] parts = fileScanner.nextLine().split(" ");
                int id = Integer.parseInt(parts[0]);
                Room room = new Room(id);
                dungeon.addRoom(room);
            }
        } catch (Exception e) {
            System.out.println("Failed to read file: " + e.getMessage());
            return;
        }

        try (Scanner fileScanner = new Scanner(new java.io.File(filename))) {
            int numRooms = Integer.parseInt(fileScanner.nextLine());
            for (int i = 0; i < numRooms; i++) {
                String[] parts = fileScanner.nextLine().split(" ");
                int id = Integer.parseInt(parts[0]);
                int neighbors = Integer.parseInt(parts[1]);
                for (int j = 0; j < neighbors; j++) {
                    int neighborId = Integer.parseInt(parts[2 + j * 2]);
                    int danger = Integer.parseInt(parts[3 + j * 2]);
                    dungeon.connectRooms(id, neighborId, danger);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to build graph: " + e.getMessage());
            return;
        }

        List<Integer> keys = new ArrayList<>(dungeon.rooms.keySet());
        if (keys.size() < 2) {
            System.out.println("Not enough rooms to escape.");
            return;
        }

        int startId = keys.getFirst();
        int endId = keys.getLast();

        List<Room> path = dungeon.findSafestPath(startId, endId);
        if (path == null) {
            System.out.println("No safe path to the exit. You're trapped!");
        } else {
            System.out.println("Safest Escape Path:");
            for (int i = 0; i < path.size() - 1; i++) {
                int danger = path.get(i).neighbors.get(path.get(i + 1));
                System.out.printf("Room %d -> Room %d (Danger Level: %d)%n",
                        path.get(i).id, path.get(i + 1).id, danger);
            }
            System.out.println("Total Danger Level: " + dungeon.getTotalDanger(path));
        }
    }
}