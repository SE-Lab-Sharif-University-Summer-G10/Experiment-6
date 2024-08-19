package graph.road;

import graph.Graph;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoadContext {
    Integer trainTime;
    RoadState roadState;
    Graph graph;
}
