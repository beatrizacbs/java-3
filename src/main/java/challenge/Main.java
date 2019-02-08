package challenge;

import bean.Jogador;
import javafx.util.Pair;
import parser.CsvParser;
import sun.reflect.generics.tree.Tree;
import sun.rmi.server.LoaderHandler;

import java.awt.geom.PathIterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {

	final String csvFile = "C:\\Users\\Marnie\\codenation\\java-3\\src\\main\\resources\\data.csv";
	final String comma = ",";
	final int fullNameIndex = 2;
	final int birthDateIndex = 8;
	final int eurWageIndex = 17;
	final int eurReleaseClauseIndex = 18;

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
		return separarPorIdade();
	}

	private BigDecimal tranformarParaBigDecimal(String eur_release_clause) {
		if(eur_release_clause != null){
			return new BigDecimal(eur_release_clause);
		}else {
			return new BigDecimal("0");
		}
	}

	private LocalDate transformaParaLocalDate(String s) {
		String[] dateSeparated = s.split("-");
		return LocalDate.of(Integer.parseInt(dateSeparated[0]), Integer.parseInt(dateSeparated[1]), Integer.parseInt(dateSeparated[2]));
	}

	private List<String> getNomesJogadores(List<Pair<String, BigDecimal>> pairs) {

		List<String> retorno = new ArrayList<>();
		Iterator iterator = pairs.iterator();
		Pair<String, BigDecimal> pair = pairs.get(0);
		while (retorno.size() != 10){
			retorno.add(pair.getKey());
			pair = (Pair<String, BigDecimal>) iterator.next();
		}
		return retorno;

	}

	private List<String> pegarJogadoresQ5() {
		String linha;
		boolean isFirstLine = true;
		TreeMap<LocalDate, Pair<String, BigDecimal>> jogadores = new TreeMap<>();
		LocalDate dataAtual;
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))){
			while((linha = bufferedReader.readLine()) != null){
				if(!isFirstLine){
					String[] linhaSplit = linha.split(comma);
					dataAtual = transformaParaLocalDate(linhaSplit[birthDateIndex]);
					Pair<String, BigDecimal> jogadorAtual = jogadores.get(transformaParaLocalDate(linhaSplit[birthDateIndex]));
					if(!jogadores.containsKey(dataAtual) ||
							(jogadorAtual.getValue().compareTo(new BigDecimal(linhaSplit[eurWageIndex])) == -1)){
						jogadores.put(dataAtual, new Pair<>(linhaSplit[fullNameIndex], new BigDecimal(linhaSplit[eurWageIndex])));
					}

				}else{
					isFirstLine = false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return getNomesJogadores(new ArrayList<>(jogadores.values()));

	}

	private Map<Integer, Integer> separarPorIdade(){
		Map<Integer, Integer> retorno = new TreeMap<>();
		String linha = "";
		boolean isFirstLine = true;
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))){
			while((linha = bufferedReader.readLine()) != null){
				if(!isFirstLine){
					String[] informacao = linha.split(comma);
					Integer valor = Integer.parseInt(informacao[6]);
					if(retorno.isEmpty() || !retorno.containsKey(valor)){
						retorno.put(valor, 1);
					}else if(retorno.containsKey(valor)){
						Integer quantidade = retorno.get(valor);
						retorno.put(valor, quantidade + 1);
					}
				}else{
					isFirstLine = false;
				}


			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return retorno;
	}

}


