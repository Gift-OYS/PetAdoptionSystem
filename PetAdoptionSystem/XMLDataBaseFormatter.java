import java.util.Iterator;

public class XMLDataBaseFormatter implements AdoptionDataBaseFormater{
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static XMLDataBaseFormatter singletonInstance = null;

    public static XMLDataBaseFormatter getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new XMLDataBaseFormatter();
        }

        return singletonInstance;
    }

    private XMLDataBaseFormatter() {
    }

    @Override
    public String formatAdoptionDataBase(MasterDataBase masterDataBase) {
        String out = "<AdoptionDataBase>"
                + NEW_LINE;

        if (masterDataBase.getNumberOfMasters() > 0){
            for (Master master : masterDataBase){
                out += "  <Master MasterName=\"" + master.getName() + "\">"
                        + NEW_LINE;

                if (master.getAdoptedPets().getNumberOfPets() > 0){
                    for (Pet pet : master.getAdoptedPets()){
                        out += "    <Pet PetName=\"" + pet.getName() + "\" PetAge=\"" + pet.getAge()+ "\">" + pet.getCode() + "</Pet>"
                                + NEW_LINE;
                    }
                }

                out += "  </Master>"
                        + NEW_LINE;
            }
        }

        out += "</AdoptionDataBase>";

        return out;
    }
}
