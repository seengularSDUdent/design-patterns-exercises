import java.util.*;

public class MazeGame {
	public static void main (String [] argv) { 
		Maze maze = new Maze(3, 2);
        System.out.println(maze + " " + maze.map);
        
        RoomBuilder builder = new RoomBuilder();
        
        builder.makeWalledRoom();
        maze.addRoomToCoordinate(builder.build(), 0, 0);
        builder.reset();
        
        builder.makeOneDoorThreeWallRoom(Direction.SOUTH);
        maze.addRoomToCoordinate(builder.build(), 1, 0);
        builder.reset();
        
        builder.makeTwoDoorTwoWallRoom(Direction.WEST, Direction.SOUTH);
        maze.addRoomToCoordinate(builder.build(), 2, 0);
        builder.reset();
        
        builder.makeDooredRoom();
        maze.addRoomToCoordinate(builder.build(), 0, 1);
        builder.reset();
        
        builder.makeOneWallThreeDoorRoom(Direction.SOUTH);
        maze.addRoomToCoordinate(builder.build(), 1, 1);
        builder.reset();
        
        builder.makeDooredRoom();
        maze.addRoomToCoordinate(builder.build(), 2, 1);
        builder.reset();
        
        for(int j = 0; j < 2; j++){
            for(int i = 0; i < 3; i++){
                for(Direction direction : Direction.values()){
                    System.out.println(maze.getRoomByCoordinate(i, j).getSide(direction));
                }
            }
        }
	}
}

class RoomBuilder{
    
    private Room inProcess;
    
    RoomBuilder(){
        this.reset();
    }
    
    public void reset(){
        this.inProcess = new Room();
    }
    
    public void makeWalledRoom(){
        for (Direction direction : Direction.values()){
            inProcess.setSide(direction, new Wall());
        }
    }
    
    public void makeDooredRoom(){
        for (Direction direction : Direction.values()){
            inProcess.setSide(direction, new DoorWall());
        }
    }
    
    public void makeOneDoorThreeWallRoom(Direction directionDoor){
        for (Direction direction : Direction.values()){
            if(direction != directionDoor){
                inProcess.setSide(direction, new Wall());
            }
            else{
                inProcess.setSide(directionDoor, new DoorWall());
            }
        }
    }
    
    public void makeTwoDoorTwoWallRoom(Direction directionFirstDoor, Direction directionSecondDoor){
        for (Direction direction : Direction.values()){
            if(direction != directionFirstDoor && direction != directionSecondDoor){
                inProcess.setSide(direction, new Wall());
            }
            else if(direction == directionFirstDoor){
                inProcess.setSide(direction, new DoorWall());
            }
            else{
                inProcess.setSide(direction, new DoorWall());
            }
        }
    }
    
    public void makeOneWallThreeDoorRoom(Direction directionWall){
        for (Direction direction : Direction.values()){
            if(direction == directionWall){
                inProcess.setSide(direction, new Wall());
            }
            else{
                inProcess.setSide(direction, new DoorWall());
            }
        }
    }
    
    public Room build(){
        return inProcess;
    }
}

class Maze {
	public Room[][] map;
	
	public Maze(int x, int y){
	    map = new Room[x][y];
	}
	
	public void addRoomToCoordinate (Room room, int x, int y) {
	    map[x][y] = room;
	}

	public Room getRoomByCoordinate(int x, int y){
	    return map[x][y];
	}
}

class Room{
    
    public Map <Direction, Wall> sides = new HashMap <Direction, Wall>();
    
    void setSide(Direction direction, Wall wall){
        sides.put(direction, wall);
    }
    
    Wall getSide(Direction direction){
        return sides.get(direction);
    }
}

class Wall{
    
}

class DoorWall extends Wall {

}

enum Direction { 
	NORTH,
	EAST,
	SOUTH,
	WEST 
}