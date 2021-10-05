import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;

public interface MasterDataBaseLoader {
    MasterDataBase loadMasterDataBase(String filename) throws IOException, DataFormatException, FileNotFoundException;
}
