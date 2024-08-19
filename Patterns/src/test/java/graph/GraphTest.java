package graph;

import graph.road.RoadContext;
import graph.road.TwoWayRoadState;
import org.junit.Test;

import java.util.ArrayList;

public class GraphTest {

    @Test
    public void travelTest() {
        RoadContext context = new RoadContext();
        ArrayList<Node> cities = CommandRunner.initializeNodes(5);

        CommandRunner.addEdge("0 1 5", cities);
        CommandRunner.addEdge("1 2 4", cities);
        CommandRunner.addEdge("0 2 10", cities);
        CommandRunner.addEdge("0 3 10", cities);
        CommandRunner.addEdge("3 4 22", cities);
        context.setGraph(new Graph(cities));

        String result;

        result = CommandRunner.executeCommand("governor two-way", context);
        assert result.equals("SUCCESS");
        assert context.getRoadState().getClass() == TwoWayRoadState.class;

        result = CommandRunner.executeCommand("governor train 3", context);
        assert result.equals("SUCCESS");
        assert context.getTrainTime() == 3;

        result = CommandRunner.executeCommand("citizen bus 0 2", context);
        assert result.equals("Bus time is: 9");

        result = CommandRunner.executeCommand("citizen train 0 2", context);
        assert result.equals("Train time is: 3");

        result = CommandRunner.executeCommand("citizen best 0 2", context);
        assert result.equals("Train is the best way to get there.");

        result = CommandRunner.executeCommand("citizen hate 0 2 1", context);
        assert result.equals("You can do it.");

        result = CommandRunner.executeCommand("citizen hate 0 4 3", context);
        assert result.equals("You can not do it.");

    }
}
