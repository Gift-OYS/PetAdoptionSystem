import java.util.ArrayList;
import java.util.Iterator;

public class PetCatalog implements Iterable<Pet>{
    private ArrayList<Pet> items = new ArrayList<Pet>();

    public PetCatalog() {

    }

    public void addItem(Pet pet){
        this.items.add(pet);
    }

    public void removeItem(Pet pet){
        items.remove(pet);
    }

    @Override
    public Iterator<Pet> iterator() {
        return this.items.iterator();
    }

    public int getNumberOfPets(){
        return this.items.size();
    }
}
