package challenge;

import javafx.util.Pair;
import parser.CsvParser;
import sun.reflect.generics.tree.Tree;

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
	final int nationalityIndex = 14;
	final int clubIndex = 3;

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		CsvParser.getAllJogadores();
		return lerLista(new ArrayList<String>(), nationalityIndex).size() - 1;
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		return lerLista(new ArrayList<>(), clubIndex).size() - 2;
	}

	// Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		return lerLista(new ArrayList<>(), fullNameIndex).subList(1,21);
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		return pegarJogadoresQ4().subList(0, 10);
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		//TODO: verificar se a data já existe, se já existir, comparar com o campo de desempate. Usar TreeMap<>
		return pegarJogadoresQ5();
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

	private LocalDate transformaParaLocalDate(String s) {
		String[] dateSeparated = s.split("-");
		return LocalDate.of(Integer.parseInt(dateSeparated[0]), Integer.parseInt(dateSeparated[1]), Integer.parseInt(dateSeparated[2]));
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		return separarPorIdade();
	}


	private List<String> pegarJogadoresQ4(){
		String linha;
		boolean isFirstLine = true;
		TreeMap<BigDecimal, List<String>> jogadores = new TreeMap<>();
		BigDecimal salarioAtual;
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))){
			while((linha = bufferedReader.readLine()) != null){
				if(!isFirstLine){
					String[] linhaSplit = linha.split(comma);
					if(!linhaSplit[eurReleaseClauseIndex].equals("")){
						salarioAtual = new BigDecimal(linhaSplit[eurReleaseClauseIndex]);
						if(!jogadores.containsKey(salarioAtual)){
							List<String> lista = new ArrayList<>();
							lista.add(linhaSplit[fullNameIndex]);
							jogadores.put(salarioAtual, lista);
						}else{
							List<String> listaAux = new ArrayList<>(jogadores.get(salarioAtual));
							listaAux.add(linhaSplit[fullNameIndex]);
							jogadores.put(salarioAtual, listaAux);

						}
					}

				}else{
					isFirstLine = false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}


		return retirarNomesMaiores(jogadores);
	}

	private List<String> retirarNomesMaiores(TreeMap<BigDecimal, List<String>> jogadores) {

		List<String> retorno = new ArrayList<>();
		for (List<String> lista: jogadores.descendingMap().values()) {
			retorno.addAll(lista);
		}
		return retorno;
	}

	private List<String> lerLista(List<String> lista, int index){
		String linha;
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))){
			while((linha = bufferedReader.readLine()) != null){
				String[] informacao = linha.split(comma);
				if(lista.isEmpty() || !lista.contains(informacao[index])){
					lista.add(informacao[index]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return lista;
	}

	private Map<Integer, Integer> separarPorIdade(){
		Map<Integer, Integer> retorno = new HashMap<>();
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


