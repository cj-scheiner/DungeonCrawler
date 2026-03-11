# DungeonCrawler

A Java program that finds the safest escape path through a dungeon represented as a weighted graph. Given a map of rooms and the danger levels of paths between them, the program uses **Dijkstra's algorithm** to compute the lowest-risk route from the entrance to the exit.

---

## How It Works

The dungeon is modeled as an undirected weighted graph:
- Each **node** represents a room
- Each **edge** represents a path between two rooms
- Each edge has a **danger level** (weight) — lower is safer

The program reads a dungeon map from a `.txt` file, builds the graph, and runs Dijkstra's algorithm to find the path with the minimum total danger level.

---

## Project Structure

```
DungeonCrawler/
├── DungeonCrawler.java   # Main entry point — handles input and output
├── DungeonMap.java       # Graph structure and Dijkstra's algorithm
├── Room.java             # Node class representing a single room
├── dungeonmap1.txt       # Sample map — 10 rooms
├── dungeonmap2.txt       # Sample map — 15 rooms
├── dungeonmap3.txt       # Sample map — 5 rooms (some disconnected)
├── dungeonmap4.txt       # Sample map — 7 rooms
└── dungeonmap5.txt       # Sample map — 5 rooms (cycle test)
```

---

## Getting Started

### Requirements
- Java 8 or higher

### Compile
```bash
javac Room.java DungeonMap.java DungeonCrawler.java
```

### Run
```bash
java DungeonCrawler
```

You will be prompted to enter a map file name:
```
Enter input file name: dungeonmap1.txt
```

---

## Input File Format

```
<number of rooms>
<room_id> <num_neighbors> <neighbor_id> <danger> <neighbor_id> <danger> ...
```

### Example (`dungeonmap4.txt`)
```
7
1 2 2 3 3 1
2 3 1 3 3 1 4 2
3 2 1 1 5 2
4 3 2 2 5 1 6 3
5 3 3 2 4 1 6 2
6 3 4 3 5 2 7 1
7 1 6 1
```

This defines 7 rooms. Room `1` has 2 neighbors: Room `2` (danger 3) and Room `3` (danger 1).

---

## Sample Output

```
Safest Escape Path:
Room 1 -> Room 3 (Danger Level: 1)
Room 3 -> Room 5 (Danger Level: 2)
Room 5 -> Room 4 (Danger Level: 1)
Room 4 -> Room 6 (Danger Level: 3)
Room 6 -> Room 7 (Danger Level: 1)
Total Danger Level: 8
```

If no path exists between the start and exit:
```
No safe path to the exit. You're trapped!
```

---

## Algorithm

Dijkstra's shortest path algorithm is implemented in `DungeonMap.java`. It uses a `PriorityQueue` ordered by cumulative danger level and a `prev` map to reconstruct the path once the exit room is reached.

- **Time complexity:** O((V + E) log V)
- **Space complexity:** O(V)

---

## Included Test Maps

| File | Rooms | Notes |
|------|-------|-------|
| `dungeonmap1.txt` | 10 | Standard connected map |
| `dungeonmap2.txt` | 15 | Larger map with more branching paths |
| `dungeonmap3.txt` | 5 | Includes a disconnected room (Room 5) |
| `dungeonmap4.txt` | 7 | Moderate complexity |
| `dungeonmap5.txt` | 5 | Contains cycles |
