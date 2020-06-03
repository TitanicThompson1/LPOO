package com.g77.data.objects;

import com.g77.data.stats.Position;

import java.util.Objects;

public class Door extends Object implements SteppedOn {

    private int roomId1, roomId2;
    private boolean locked;
    private Position nextRoomPosition;

    public Door(Position position, Position nextRoomPosition, int currentRoom, int nextRoom, boolean locked) {
        super(position);
        this.roomId1 = currentRoom;
        this.roomId2 = nextRoom;
        this.locked = locked;
        this.nextRoomPosition = nextRoomPosition;
    }

    public Door(Position position){
        super(position);
    }

    public int openDoor()
    {
        return roomId2;
    }

    public int getRoomId1() {
        return roomId1;
    }

    public int getRoomId2() {
        return roomId2;
    }

    public Position getNextRoomPosition() {
        return nextRoomPosition;
    }


    @Override
    public void wasSteppedOn(MainCharacter mainCharacter)
    {
        mainCharacter.moveTo(this.nextRoomPosition);
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Door door = (Door) o;
        return roomId1 == door.roomId1 && roomId2 == door.roomId2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId1, roomId2);
    }
}
