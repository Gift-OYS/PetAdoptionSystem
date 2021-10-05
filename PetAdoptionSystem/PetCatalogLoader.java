import java.io.*;
import java.util.zip.DataFormatException;

public interface PetCatalogLoader  {
    PetCatalog loadPetCatalog(String filename) throws FileNotFoundException, IOException, DataFormatException;
}