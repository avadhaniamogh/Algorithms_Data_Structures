package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildOrder {
	
	public class Project {
		
		ArrayList<Project> children = new ArrayList<>();
		HashMap<String, Project> map = new HashMap<>();
		int dependencies = 0;
		String name;
		
		Project(String name) {
			this.name = name;
		}
		
		String getName() {
			return name;
		}
		
		ArrayList<Project> getChildren() {
			return children;
		}
		
		int getNumberOfDependencies() {
			return dependencies;
		}
		
		public void addNeighbour(Project p) {
			if(!map.containsKey(p.getName())) {
				children.add(p);
				p.incrementDependencies();
				//Not in CTCI
				map.put(p.getName(), p);
			}
		}
		
		public void incrementDependencies() {
			dependencies++;
		}
		public void decrementDependencies() {
			dependencies--;
		}
	}
	
	public class Graph {
		private ArrayList<Project> nodes = new ArrayList<>();
		private HashMap<String, Project> map = new HashMap<>();
		
		public Project getOrCreateNode(String name) {
			if(!map.containsKey(name)) {
				Project project = new Project(name);
				map.put(name, project);
				nodes.add(project);
			}
			return map.get(name);
		}
		
		public void addEdge(String s1, String s2) {
			Project p1 = getOrCreateNode(s1);
			Project p2 = getOrCreateNode(s2);
			p1.addNeighbour(p2);
		}
		
		public ArrayList<Project> getNodes() {
			return nodes;
		}
	}
	
	Project[] findCorrectBuildOrder(String[] projects, String[][] dependencies) {
		Graph graph = buildGraph(projects, dependencies);
		return orderProjects(graph.getNodes());
	}
	
	Graph buildGraph(String[] projects, String[][] dependencies) {
		Graph graph = new Graph();
		for(String project : projects) {
			graph.getOrCreateNode(project);
		}
		
		for(String[] dependency : dependencies) {
			graph.addEdge(dependency[0], dependency[1]);
		}
		
		return graph;
	}
	
	Project[] orderProjects(ArrayList<Project> projects) {
		Project[] order = new Project[projects.size()];
		
		int endOfList = addNonDependent(order, projects, 0);
		
		int toBeProcessed = 0;
		
		while(toBeProcessed < order.length) {
			
			Project current = order[toBeProcessed];
			
			if(current == null) {
				return null;
			}
			
			ArrayList<Project> children = current.getChildren();
			for(Project child : children) {
				child.decrementDependencies();
			}
			
			endOfList = addNonDependent(order, projects, endOfList);
			toBeProcessed++;
		}
		
		return null;
		
	}
	
	int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
		for(Project project : projects) {
			if(project.getNumberOfDependencies() == 0) {
				order[offset] = project;
				offset++;
			}
		}
		
		return offset;
	}

}
