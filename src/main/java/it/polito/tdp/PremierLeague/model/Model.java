package it.polito.tdp.PremierLeague.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao;
	List<Match> matchesFiltered;
	List<EdgeModel> archi;
	SimpleWeightedGraph<Match, DefaultWeightedEdge> graph;
	
	public Model() {
		dao = new PremierLeagueDAO();
		graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		archi = new ArrayList<>();
	}
	
	public void getMatchByMonth(Month month){
		
		matchesFiltered = new ArrayList<>();
		List<Match> allMatches = dao.listAllMatches();
		
		for(Match m: allMatches) {
			Month mese = m.getDate().getMonth();
			
			if(mese.equals(month)) {
				matchesFiltered.add(m);
			}
		}
	}
	
	public void buildGraph(Month month, int min) {
		
		getMatchByMonth(month);
		
		//aggiungo i vertici filtrati per mese
		Graphs.addAllVertices(graph, matchesFiltered);
		
		System.out.println(this.graph.vertexSet().size());
		
		archi = dao.listAllEdges(min);
		
		for(EdgeModel e: archi) {
			if(matchesFiltered.contains(e.getM1()) && matchesFiltered.contains(e.getM2())) {
				
				Graphs.addEdge(graph, e.getM1(), e.getM2(), e.getPeso());
			}
		}
		System.out.println(this.graph.edgeSet().size());
	}

	public int getSizeVertex() {
		// TODO Auto-generated method stub
		return this.graph.vertexSet().size();
	}
	public int getSizeEdges() {
		// TODO Auto-generated method stub
		return this.graph.edgeSet().size();
	}
	
	public List<EdgeModel> getCollMax(){
		
		int pesoMax = 0;
		List<EdgeModel> result = new ArrayList<>();
		
		for(DefaultWeightedEdge e: this.graph.edgeSet()) {
			if(graph.getEdgeWeight(e)>pesoMax) {
				pesoMax = (int) graph.getEdgeWeight(e);
			}
		}
		for(DefaultWeightedEdge e: this.graph.edgeSet()) {
			if((int) graph.getEdgeWeight(e)==pesoMax) {
				Match m1 = graph.getEdgeSource(e);
				Match m2 = graph.getEdgeTarget(e);
				result.add( new EdgeModel(m1,m2,pesoMax));
			}
		}
		return result;
	}
}
