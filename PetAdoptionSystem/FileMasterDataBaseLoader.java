import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.zip.DataFormatException;

public class FileMasterDataBaseLoader implements MasterDataBaseLoader{
    private final static String DELIM = "_";


    @Override
    public MasterDataBase loadMasterDataBase(String filename) throws IOException, DataFormatException, FileNotFoundException {
        MasterDataBase masterDataBase = new MasterDataBase();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();

        while (line != null) {
            Master master = readMaster(line);

            masterDataBase.addMaster(master);

            line = reader.readLine();
        }

        reader.close();

        return masterDataBase;
    }

    private Master readMaster(String line) throws DataFormatException{
        StringTokenizer tokenizer = new StringTokenizer(line, DELIM);

        if (tokenizer.countTokens() != 3){
            throw new DataFormatException(line);
        } else {
            try {
                return new Master(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
            } catch (NumberFormatException nfe){
                throw new DataFormatException(line);
            }
        }
    }
}
