package challenge;

import bean.Jogador;
import parser.CsvParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		List<Jogador> jogadores = CsvParser.getListaJogadores();
		Iterator<Jogador> iterator = jogadores.iterator();
		List<String> nacionalidades = new ArrayList<>();
		Jogador jogadorAtual;
		while (iterator.hasNext()){
			jogadorAtual = iterator.next();
			if(nacionalidades.isEmpty() || !nacionalidades.contains(jogadorAtual.getNationality())){
				nacionalidades.add(jogadorAtual.getNationality());
			}
		}

		return nacionalidades.size();
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		List<Jogador> jogadores = CsvParser.getListaJogadores();
		Iterator<Jogador> iterator = jogadores.iterator();
		List<String> clubes = new ArrayList<>();
		Jogador jogadorAtual;
		while (iterator.hasNext()){
			jogadorAtual = iterator.next();
			if((clubes.isEmpty() || !clubes.contains(jogadorAtual.getClub())) && !jogadorAtual.getClub().equals("")){
				clubes.add(jogadorAtual.getClub());
			}
		}
		return clubes.size();
	}

	// Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		List<Jogador> jogadores = CsvParser.getListaJogadores();
		Iterator<Jogador> iterator = jogadores.iterator();
		int index = 0;
		List<String> nomes = new ArrayList<>();
		Jogador jogadorAtual;
		while (iterator.hasNext() && index < 20){
			jogadorAtual = iterator.next();
			nomes.add(jogadorAtual.getFull_name());
			index++;
		}
		return nomes;
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		List<Jogador> jogadores = CsvParser.getListaJogadores();
		Iterator<Jogador> iterator = jogadores.iterator();
		Jogador jogadorAtual;
		BigDecimal eurClauseAtual;
		TreeMap<BigDecimal, String> mapJogadores = new TreeMap<>();
		while (iterator.hasNext()){
			jogadorAtual = iterator.next();
			eurClauseAtual = tranformarParaBigDecimal(jogadorAtual.getEur_release_clause());
			mapJogadores.put(eurClauseAtual, jogadorAtual.getFull_name());
		}
		return new ArrayList<>(mapJogadores.descendingMap().values()).subList(0, 10);
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		List<Jogador> jogadores = CsvParser.getListaJogadores();
		Iterator<Jogador> iterator = jogadores.iterator();
		Jogador jogadorAtual;
		TreeMap<LocalDate, Jogador> jogadorTreeMap = new TreeMap<>();
		while (iterator.hasNext()){
			jogadorAtual = iterator.next();
			if(jogadorTreeMap.isEmpty() || !jogadorTreeMap.containsKey(transformaParaLocalDate(jogadorAtual.getBirth_date()))){
				jogadorTreeMap.put(transformaParaLocalDate(jogadorAtual.getBirth_date()), jogadorAtual);
			}else{
				if(tranformarParaBigDecimal(jogadorAtual.getEuro_wage())
								.compareTo(tranformarParaBigDecimal(jogadorTreeMap.get(transformaParaLocalDate(jogadorAtual.getBirth_date()))
												.getEuro_wage())) == 1){
					jogadorTreeMap.put(transformaParaLocalDate(jogadorAtual.getBirth_date()), jogadorAtual);
				}
			}
		}

		return fazerParserParaNomes(jogadorTreeMap).subList(0, 10);
	}

	private List<String> fazerParserParaNomes(TreeMap<LocalDate, Jogador> jogadorTreeMap) {
		List<Jogador> jogadores = new ArrayList<>(jogadorTreeMap.values());
		Iterator<Jogador> iterator = jogadores.iterator();
		List<String> nomesJogadores = new ArrayList<>();
		Jogador jogadorAtual;
		while (iterator.hasNext()){
			jogadorAtual = iterator.next();
			nomesJogadores.add(jogadorAtual.getFull_name());
		}
		return  nomesJogadores;
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		List<Jogador> jogadores = CsvParser.getListaJogadores();
		Jogador jogadorAtual;
		Iterator<Jogador> iterator = jogadores.iterator();
		Integer quantidade = 0;
		Map<Integer, Integer> contagem = new TreeMap<>();
		while (iterator.hasNext()){
			jogadorAtual = iterator.next();
			if(contagem.isEmpty() || !contagem.containsKey(transformarParaInteger(jogadorAtual.getAge()))){
				contagem.put(transformarParaInteger(jogadorAtual.getAge()), 1);
			}else{
				quantidade = contagem.get(transformarParaInteger(jogadorAtual.getAge())) + 1;
				contagem.put(transformarParaInteger(jogadorAtual.getAge()), quantidade);
			}
		}
		return contagem;
	}

	private BigDecimal tranformarParaBigDecimal(String eur_release_clause) {
		if(eur_release_clause != null && !eur_release_clause.equals("")){
			return new BigDecimal(eur_release_clause);
		}else {
			return new BigDecimal("0");
		}
	}

	private LocalDate transformaParaLocalDate(String s) {
		String[] dateSeparated = s.split("-");
		return LocalDate.of(Integer.parseInt(dateSeparated[0]), Integer.parseInt(dateSeparated[1]), Integer.parseInt(dateSeparated[2]));
	}

	private Integer transformarParaInteger(String str){
		if(str != null){
			return Integer.parseInt(str);
		}else{
			return 0;
		}
	}

}


