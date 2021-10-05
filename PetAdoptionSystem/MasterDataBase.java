import java.util.ArrayList;
import java.util.Iterator;

public class MasterDataBase implements Iterable<Master>{
    private ArrayList<Master> masters = new ArrayList<Master>();

    public MasterDataBase() {

    }

    @Override
    public Iterator<Master> iterator() {
        return this.masters.iterator();
    }

    public void addMaster(Master master){
        this.masters.add(master);
    }

    public int getNumberOfMasters(){
        return this.masters.size();
    }
}
