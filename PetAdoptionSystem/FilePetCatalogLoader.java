import java.io.*;
import java.util.StringTokenizer;
import java.util.zip.DataFormatException;

public class FilePetCatalogLoader implements PetCatalogLoader{
    private final static String DOG_PREFIX = "Dog";
    private final static String CAT_PROFIX = "Cat";
    private final static String BIRD_PROFIX = "Bird";
    private final static String DELIM = "_";

    @Override
    public PetCatalog loadPetCatalog(String filename) throws FileNotFoundException, IOException, DataFormatException {
        PetCatalog petcatalog = new PetCatalog();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();

        while (line != null) {
            Pet pet = null;

            if (line.startsWith(DOG_PREFIX)) {
                pet = readDog(line);
            } else if (line.startsWith(CAT_PROFIX)) {
                pet = readCat(line);
            } else if (line.startsWith(BIRD_PROFIX)){
                pet = readBird(line);
            } else {
                throw new DataFormatException(line);
            }

            petcatalog.addItem(pet);

            line = reader.readLine();
        }

        reader.close();

        return petcatalog;
    }

    private Dog readDog(String line) throws DataFormatException{
        StringTokenizer tokenizer = new StringTokenizer(line, DELIM);

        if (tokenizer.countTokens() != 5){
            throw new DataFormatException(line);
        } else {
            try {
                tokenizer.nextToken();

                return new Dog(tokenizer.nextToken(), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken());
            } catch (NumberFormatException nfe){
                throw new DataFormatException(line);
            }
        }
    }

    private Cat readCat(String line) throws DataFormatException{
        StringTokenizer tokenizer = new StringTokenizer(line, DELIM);

        if (tokenizer.countTokens() != 4){
            throw new DataFormatException(line);
        } else {
            try {
                tokenizer.nextToken();

                return new Cat(tokenizer.nextToken(), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()));
            } catch (NumberFormatException nfe){
                throw new DataFormatException(line);
            }
        }
    }

    private Bird readBird(String line) throws DataFormatException{
        StringTokenizer tokenizer = new StringTokenizer(line, DELIM);

        if (tokenizer.countTokens() != 5){
            throw new DataFormatException(line);
        } else {
            try {
                tokenizer.nextToken();

                return new Bird(tokenizer.nextToken(), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken());
            } catch (NumberFormatException nfe){
                throw new DataFormatException(line);
            }
        }
    }

}
