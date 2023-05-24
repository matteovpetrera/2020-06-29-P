package it.polito.tdp.PremierLeague.db;

import java.util.List;

import it.polito.tdp.PremierLeague.model.EdgeModel;

public class TestDao {

	public static void main(String[] args) {
		 PremierLeagueDAO dao = new PremierLeagueDAO();
		
		 List<EdgeModel> res = dao.listAllEdges(45);
		for(EdgeModel e: res) {
			System.out.println(e.toString());
		}
	}
	
	

}
