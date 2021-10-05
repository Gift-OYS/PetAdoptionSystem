import java.util.Iterator;

public class HTMLDataBaseFormatter implements AdoptionDataBaseFormater{
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static HTMLDataBaseFormatter singletonInstance = null;

    public static HTMLDataBaseFormatter getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HTMLDataBaseFormatter();
        }

        return singletonInstance;
    }

    private HTMLDataBaseFormatter() {
    }

    @Override
    public String formatAdoptionDataBase(MasterDataBase masterDataBase) {
        String out = "<html>"
                + NEW_LINE
                + "  <body>"
                + NEW_LINE + "  "
                + "  <center><h2>Adoption Data Base</h2></center>"
                + NEW_LINE;

        if (masterDataBase.getNumberOfMasters() > 0){
            for (Master master : masterDataBase){
                out += "    <hr>"
                        + NEW_LINE
                        + "    <h4>Master: " + master.getName() + "</h4>"
                        + NEW_LINE;

                if (master.getAdoptedPets().getNumberOfPets() > 0){
                    for (Pet pet : master.getAdoptedPets()){
                        out += "      <p>"
                                + NEW_LINE
                                + "        <b>Code:</b> " + pet.getCode() + "<br>"
                                + NEW_LINE
                                + "        <b>Name:</b> " + pet.getName() + "<br>"
                                + NEW_LINE
                                + "        <b>Age:</b> " + pet.getAge() + "<br>"
                                + NEW_LINE
                                + "      </p>"
                                + NEW_LINE;
                    }
                }
            }
        }

        out += "    </body>"
                + NEW_LINE
                + "</html>";

        return out;
    }
}
