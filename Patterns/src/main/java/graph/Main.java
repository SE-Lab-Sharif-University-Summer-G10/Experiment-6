package graph;

import graph.road.RoadContext;

import java.util.ArrayList;
import java.util.Scanner;

import static graph.CommandRunner.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String governorCommands = "governor (one-way | two-way | train [new-time])";
    private static final String citizenCommands = "citizen (bus [src] [dst] | train [src] [dst] | best [src] [dst] | hate [src] [dst] [hated-city])";


    public static void main(String[] args) {
        System.out.println("Input number of cities:");
        ArrayList<Node> cities;
        cities = initializeNodes(Integer.parseInt(scanner.nextLine()));
        System.out.println("input edges in format [src dst weight]. Type exit when finished.");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            addEdge(input, cities);
            input = scanner.nextLine();
        }
        System.out.println(governorCommands);
        System.out.println(citizenCommands);
        RoadContext context = new RoadContext();
        context.setGraph(new Graph(cities));
        input = scanner.nextLine();
        while (!input.equals("exit")) {
            System.out.println(executeCommand(input, context));
            input = scanner.nextLine();
        }
    }
}
