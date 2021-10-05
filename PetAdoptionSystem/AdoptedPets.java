import java.util.ArrayList;
import java.util.Iterator;

public class AdoptedPets implements Iterable<Pet>{
    private ArrayList<Pet> pets = new ArrayList<Pet>();

    public AdoptedPets() {
    }

    public void addPet(Pet pet){
        this.pets.add(pet);
    }

    public void removePet(Pet pet){
        this.pets.remove(pet);
    }

    @Override
    public Iterator<Pet> iterator() {
        return this.pets.iterator();
    }

    public int getNumberOfPets(){
        return this.pets.size();
    }
}
