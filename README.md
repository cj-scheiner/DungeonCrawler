# DungeonCrawler

A Java program that finds the safest escape path through a dungeon using Dijkstra's algorithm. Rooms and the danger levels between them are loaded from a text file, and the program outputs the lowest-risk route to the exit.

## How to run

**Compile:**
```bash
javac Room.java DungeonMap.java DungeonCrawler.java
```

**Run:**
```bash
java DungeonCrawler
```

You'll be prompted to enter a map file name, e.g. `dungeonmap1.txt`. Five sample maps are included.

## Input file format

```
<number of rooms>
<room_id> <num_neighbors> <neighbor_id> <danger> <neighbor_id> <danger> ...
```

## Sample output

```
Safest Escape Path:
Room 1 -> Room 3 (Danger Level: 1)
Room 3 -> Room 5 (Danger Level: 2)
...
Total Danger Level: 8
```
