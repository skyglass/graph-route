package skyglass.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import skyglass.demo.exception.BusinessRuleValidationException;
import skyglass.demo.exception.PathNotFoundException;

public class RoutePath {

	public static String NO_CONNECTION_MESSAGE = "There is no connection between these nodes!";

	private List<RouteEdge> edges = new ArrayList<>();

	private Set<String> edgeIds = new HashSet<>();

	public RoutePath() {

	}

	public Collection<RouteEdge> getEdges() {
		return edges;
	}

	public boolean alreadyBeenThere(RouteEdge edge) {
		//if it's true then it means that we came back to the same place and need to skip this path
		return edgeIds.contains(edge.getEndNode());
	}

	public void addEdge(RouteEdge edge) throws BusinessRuleValidationException {
		if (edges.size() == 0) {
			edgeIds.add(edge.getStartNode());
		} else {
			if (!Objects.equals(edges.get(edges.size() - 1).getEndNode(), edge.getStartNode())) {
				throw new BusinessRuleValidationException("Programming Error: Incorrect Path");
			}
		}
		edgeIds.add(edge.getEndNode());
		edges.add(edge);
	}

	public int getLength() {
		return edges.size();
	}

	public String getPath() throws PathNotFoundException {
		StringBuilder sb = new StringBuilder();
		if (edges.size() == 0) {
			sb.append(getNoConnectionMessage());
			throw new PathNotFoundException(sb.toString());
		}
		String startNode = edges.get(0).getStartNode();
		String endNode = edges.get(edges.size() - 1).getEndNode();
		sb.append(String.format("The path from %s to %s is: [", startNode, endNode));
		int i = 0;
		for (RouteEdge route : edges) {
			sb.append(route.getStartNode());
			sb.append(", ");
			if (i == edges.size() - 1) {
				sb.append(route.getEndNode());
			}
			i++;
		}
		sb.append("]");
		return sb.toString();
	}

	protected String getNoConnectionMessage() {
		return NO_CONNECTION_MESSAGE;
	}

	public RoutePath copy() throws BusinessRuleValidationException {
		RoutePath result = new RoutePath();
		for (RouteEdge route : this.edges) {
			result.addEdge(route);
		}
		return result;
	}

}
