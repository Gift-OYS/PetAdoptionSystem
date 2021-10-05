public class Master {
    private String id;
    private String name;
    private String password;
    private AdoptedPets adoptedPets;

    public Master(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.adoptedPets = new AdoptedPets();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public AdoptedPets getAdoptedPets(){
        return this.adoptedPets;
    }


    public String toString(){
        return getId() + "_" + getName() + "_" + getPassword();
    }
}
