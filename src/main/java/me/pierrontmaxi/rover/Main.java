package me.pierrontmaxi.rover;

import me.pierrontmaxi.rover.usecase.MarsNasaMission;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage java -jar rover.jar <filePath>");
            return;
        }
        String fileName = args[0];
        MarsNasaMission marsNasaMission = new MarsNasaMission(fileName);
        System.out.println(marsNasaMission.getPlateau().getRoversFinalCoordinates());
    }
}
