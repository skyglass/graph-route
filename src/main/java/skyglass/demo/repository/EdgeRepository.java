package skyglass.demo.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import skyglass.demo.model.RouteEdge;

public class EdgeRepository {

	private Collection<RouteEdge> edges;

	public EdgeRepository(Collection<RouteEdge> edges) {
		this.edges = edges;
	}

	public List<RouteEdge> find(String startNode) {
		List<RouteEdge> result = new ArrayList<>();
		for (RouteEdge edge : edges) {
			if (Objects.equals(startNode, edge.getStartNode())) {
				result.add(edge);
			}
		}
		return result;
	}

}
