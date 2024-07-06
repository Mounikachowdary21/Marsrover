// Direction handling
abstract class Direction {
    public abstract Direction left();
    public abstract Direction right();
    public abstract int[] move(int x, int y);
}

class North extends Direction {
    @Override
    public Direction left() {
        return new West();
    }

    @Override
    public Direction right() {
        return new East();
    }

    @Override
    public int[] move(int x, int y) {
        return new int[]{x, y + 1};
    }
}

class South extends Direction {
    @Override
    public Direction left() {
        return new East();
    }

    @Override
    public Direction right() {
        return new West();
    }

    @Override
    public int[] move(int x, int y) {
        return new int[]{x, y - 1};
    }
}

class East extends Direction {
    @Override
    public Direction left() {
        return new North();
    }

    @Override
    public Direction right() {
        return new South();
    }

    @Override
    public int[] move(int x, int y) {
        return new int[]{x + 1, y};
    }
}

class West extends Direction {
    @Override
    public Direction left() {
        return new South();
    }

    @Override
    public Direction right() {
        return new North();
    }

    @Override
    public int[] move(int x, int y) {
        return new int[]{x - 1, y};
    }
}

// Command Pattern
interface Command {
    void execute();
}

class MoveCommand implements Command {
    private Rover rover;

    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.move();
    }
}

class TurnLeftCommand implements Command {
    private Rover rover;

    public TurnLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnLeft();
    }
}

class TurnRightCommand implements Command {
    private Rover rover;

    public TurnRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnRight();
    }
}

// Rover
class Rover {
    private int x;
    private int y;
    private Direction direction;
    private Grid grid;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
    }

    public void move() {
        int[] newPos = direction.move(x, y);
        if (grid.isWithinBounds(newPos[0], newPos[1]) && !grid.hasObstacle(newPos[0], newPos[1])) {
            x = newPos[0];
            y = newPos[1];
        }
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public String status() {
        String dir = "";
        if (direction instanceof North) dir = "North";
        else if (direction instanceof South) dir = "South";
        else if (direction instanceof East) dir = "East";
        else if (direction instanceof West) dir = "West";
        
        return String.format("Rover is at (%d, %d) facing %s. No Obstacles detected.", x, y, dir);
    }
}

// Composite Pattern for Grid
abstract class GridComponent {
    public abstract boolean isWithinBounds(int x, int y);
    public abstract boolean hasObstacle(int x, int y);
}

class Grid extends GridComponent {
    private int width;
    private int height;
    private boolean[][] obstacles;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        obstacles = new boolean[width][height];
    }

    public void addObstacle(int x, int y) {
        if (isWithinBounds(x, y)) {
            obstacles[x][y] = true;
        }
    }

    @Override
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public boolean hasObstacle(int x, int y) {
        return isWithinBounds(x, y) && obstacles[x][y];
    }
}

// Example usage
public class Marsrover{
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        grid.addObstacle(2, 2);
        grid.addObstacle(3, 5);

        Rover rover = new Rover(0, 0, new North(), grid);
        Command[] commands = {
            new MoveCommand(rover), // Move to (0, 1)
            new MoveCommand(rover), // Move to (0, 2)
            new TurnRightCommand(rover), // Facing East
            new MoveCommand(rover), // Move to (1, 2)
            new TurnLeftCommand(rover), // Facing North
            new MoveCommand(rover), // Move to (1, 3)
            new TurnRightCommand(rover) // Facing East
        };

        for (Command command : commands) {
            command.execute();
        }

        System.out.println(rover.status());
    }
}
