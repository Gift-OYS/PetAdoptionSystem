public class PlainTextDataBaseFormatter implements AdoptionDataBaseFormater {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static PlainTextDataBaseFormatter singletonInstance = null;

    public static PlainTextDataBaseFormatter getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new PlainTextDataBaseFormatter();
        }

        return singletonInstance;
    }

    private PlainTextDataBaseFormatter() {
    }

    @Override
    public String formatAdoptionDataBase(MasterDataBase masterDataBase) {
        String out = "";

        if (masterDataBase.getNumberOfMasters() > 0){
            for (Master master : masterDataBase){
                out += "=========================="
                        + NEW_LINE
                        + "Master：" + master.getName();
                out += NEW_LINE + NEW_LINE;

                if (master.getAdoptedPets().getNumberOfPets() > 0){
                    for (Pet pet : master.getAdoptedPets()){
                        out += pet.toString()
                                + NEW_LINE;
                    }
                }
            }
        }else {
            System.out.println("The current data base is empty.");
        }

        return out;
    }
}
