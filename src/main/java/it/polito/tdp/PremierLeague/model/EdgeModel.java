package it.polito.tdp.PremierLeague.model;

public class EdgeModel {
	
	Match m1;
	Match m2;
	int peso;
	public EdgeModel(Match m1, Match m2, int peso) {
		super();
		this.m1 = m1;
		this.m2 = m2;
		this.peso = peso;
	}
	/**
	 * @return the m1
	 */
	public Match getM1() {
		return m1;
	}
	/**
	 * @return the m2
	 */
	public Match getM2() {
		return m2;
	}
	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}
	@Override
	public String toString() {
		return "Edge: m1=" + m1.getMatchID() + ", m2=" + m2.getMatchID() + ", peso=" + peso + "]"+"\n";
	}
	
	
}
