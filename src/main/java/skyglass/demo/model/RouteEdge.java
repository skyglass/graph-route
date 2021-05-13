package skyglass.demo.model;

import java.util.Objects;

public class RouteEdge {

	private String startNode;

	private String endNode;

	public RouteEdge(String startNode, String endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}

	public String getStartNode() {
		return startNode;
	}

	public String getEndNode() {
		return endNode;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof RouteEdge)) {
			return false;
		}
		final RouteEdge other = (RouteEdge) obj;
		return Objects.equals(startNode, other.startNode)
				&& Objects.equals(endNode, other.endNode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(startNode, endNode);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(startNode);
		sb.append(" -> ");
		sb.append(endNode);
		sb.append(")");
		return sb.toString();
	}

}
