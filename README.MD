# Mars Rovers Navigation

## Problem Description

NASA intends to land a squad of robotic rovers on a curiously rectangular plateau on Mars. To achieve complete surveillance of the terrain, the rovers must navigate this plateau so their onboard cameras can capture and send back visuals to Earth.

### Rover's Positioning and Location

A rover's position and location on the plateau is expressed as:

1. `x` coordinate (horizontal axis)
2. `y` coordinate (vertical axis)
3. A letter representing one of the four cardinal compass directions: N, E, S, W.

For instance, `0 0 N` signifies that the rover is positioned at the bottom-left corner of the plateau, facing North.

### Navigation Commands

NASA can control a rover by sending a string of letters:

- `L`: Turn the rover 90 degrees to the left (without moving).
- `R`: Turn the rover 90 degrees to the right (without moving).
- `M`: Move the rover one grid point forward in the direction it is currently facing.

### Input Format

1. The first line of the input indicates the upper-right coordinates of the plateau, while the bottom-left coordinates are always assumed to be `0,0`.
2. Subsequent lines provide details about the rovers:
    - The first line provides the rover's initial position in the format `x y DIRECTION`.
    - The second line provides a sequence of instructions for the rover to explore the plateau.

Note: Each rover completes its instructions sequentially. That is, the second rover only starts moving once the first rover has completed its movements.

### Output Format

The output should display the final position and heading of each rover, one rover per line.

## Example

**Input**
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

**Expected Output**
```
1 3 N
5 1 E
```

## Running the Program

To execute the program, use the command:
```
java -jar rover.jar input.txt
```

Note: Ensure you have Java installed on your machine and that the input file, `input.txt`, is in the appropriate directory.