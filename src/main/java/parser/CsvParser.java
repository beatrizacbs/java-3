package parser;

import bean.Jogador;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvParser {

    private static final String PATH = "C:\\Users\\Marnie\\codenation\\java-3\\src\\main\\resources\\data.csv";

    public static List<Jogador> getAllJogadores(){
        List<Jogador> retorno =  new ArrayList<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(PATH))){
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Jogador.class);
            String[] memberFieldsToBindTo = {"ID", "eur_release_clause", "birth_date", "full_name", "nationality", "club"};
            strategy.setColumnMapping(memberFieldsToBindTo);

            CsvToBean<Jogador> jogadores = new CsvToBeanBuilder(reader).withMappingStrategy(strategy)
                    .withSkipLines(1).withIgnoreLeadingWhiteSpace(true).build();

            Iterator<Jogador> iterator = jogadores.iterator();

            while (iterator.hasNext()){
                Jogador jogador = iterator.next();
                retorno.add(jogador);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return retorno;
    }

}
