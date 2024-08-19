package graph;

import graph.road.OneWayRoadState;
import graph.road.RoadContext;
import graph.road.RoadState;
import graph.road.TwoWayRoadState;
import graph.travel.BusTravelStrategy;
import graph.travel.ITravelStrategy;
import graph.travel.TrainTravelStrategy;

import java.util.ArrayList;

public class CommandRunner {

    public static ArrayList<Node> initializeNodes(int n) {
        ArrayList<Node> cities;
        cities = new ArrayList<>();
        for (int i = 0; i < n; i++) cities.add(new Node());
        return cities;
    }

    public static void addEdge(String input, ArrayList<Node> cities) {
        String[] inputs = input.split(" ");
        Edge.createEdge(
                cities.get(Integer.parseInt(inputs[0])),
                cities.get(Integer.parseInt(inputs[1])),
                false,
                Integer.parseInt(inputs[2])
        );
    }

    public static String executeCommand(String input, RoadContext context) {
        String[] inputs = input.split(" ");
        switch (inputs[0]) {
            case "citizen":
                ITravelStrategy busTravelStrategy = new BusTravelStrategy(context);
                ITravelStrategy trainTravelStrategy = new TrainTravelStrategy(context);
                Integer busTime;
                Integer trainTime;
                Integer src = Integer.parseInt(inputs[2]);
                Integer dst = Integer.parseInt(inputs[3]);
                switch (inputs[1]) {
                    case "bus":
                        busTime = busTravelStrategy.calculate(src, dst, null);
                        return "Bus time is: " + busTime.toString();
                    case "train":
                        trainTime = trainTravelStrategy.calculate(src, dst, null);
                        return "Train time is: " + trainTime.toString();
                    case "best":
                        busTime = busTravelStrategy.calculate(src, dst, null);
                        trainTime = trainTravelStrategy.calculate(src, dst, null);

                        if (busTime > trainTime) {
                            return "Train is the best way to get there.";
                        } else if (busTime < trainTime) {
                            return "Bus is the best way to get there.";
                        } else return "No difference in taking a bus or train.";
                    case "hate":
                        Integer hated = Integer.parseInt(inputs[4]);
                        trainTime = trainTravelStrategy.calculate(src, dst, hated);
                        if (trainTime != -1) return "You can do it.";
                        else return "You can not do it.";
                    default:
                        return "INVALID COMMAND";
                }
            case "governor":
                switch (inputs[1]) {
                    case "one-way":
                        RoadState oneWayRoadState = new OneWayRoadState();
                        oneWayRoadState.updateContext(context);
                        break;
                    case "two-way":
                        RoadState twoWayRoadState = new TwoWayRoadState();
                        twoWayRoadState.updateContext(context);
                        break;
                    case "train":
                        context.setTrainTime(Integer.parseInt(inputs[2]));
                        break;
                }
                return "SUCCESS";
            default:
                return "INVALID COMMAND";
        }
    }
}
