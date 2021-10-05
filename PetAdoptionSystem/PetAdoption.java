import java.io.*;
import java.util.StringTokenizer;
import java.util.zip.DataFormatException;

public class PetAdoption {
    private static BufferedReader stdIn = new  BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter stdOut = new  PrintWriter(System.out, true);
    private static PrintWriter  stdErr = new  PrintWriter(System.err, true);
    private static final String NEW_LINE = System.getProperty("line.separator");
    private final static String DELIM = "_";

    private static PetCatalog petCatalog;
    private static MasterDataBase masterDataBase;
    private AdoptionDataBaseFormater adoptionDataBaseFormater;

    private MasterDataBase MASTERDATABASE = new MasterDataBase();

    public static void main(String[] args) throws IOException{

        try {
            petCatalog = (new FilePetCatalogLoader()).loadPetCatalog("petCatalog.dat");
        } catch (FileNotFoundException e) {
            stdErr.println("petCatalog.dat文件不存在！");
            System.exit(1);
        } catch (DataFormatException e) {
            stdErr.println("petCatalog.dat文件包含错误内容: " + e.getMessage());

            System.exit(1);
        }

        try {
            masterDataBase = (new FileMasterDataBaseLoader()).loadMasterDataBase("masterDataBase.dat");
        } catch (FileNotFoundException e) {
            stdErr.println("masterDataBase.dat文件不存在！");

            System.exit(1);
        } catch (DataFormatException e) {
            stdErr.println("masterDataBase.dat文件包含错误内容: " + e.getMessage());

            System.exit(1);
        }

        PetAdoption application = new PetAdoption(petCatalog, masterDataBase);

        application.run();
    }

    public PetAdoption(PetCatalog petCatalog, MasterDataBase masterDataBase) {
        this.petCatalog = petCatalog;
        this.masterDataBase = masterDataBase;
        this.adoptionDataBaseFormater = PlainTextDataBaseFormatter.getSingletonInstance();
        loadAdoptionDataBase();
    }

    public void loadAdoptionDataBase() {

        Master masterOne = new Master("LY001", "LiuYi", "111");
        Pet petOne = new Pet("D006", "DuoDuo", 1);
        Pet petTwo = new Pet("B001", "BaiLing", 4);

        masterOne.getAdoptedPets().addPet(petOne);
        masterOne.getAdoptedPets().addPet(petTwo);
        this.MASTERDATABASE.addMaster(masterOne);

        Master masterTwo = new Master("CE002", "ChenEr", "222");
        Pet petThree = new Pet("C001", "NaiTang", 2);

        masterTwo.getAdoptedPets().addPet(petThree);
        this.MASTERDATABASE.addMaster(masterTwo);

        Master masterThree = new Master("ZS003", "ZhangSan", "333");
        Pet petFour = new Pet("D004", "BuDing", 3);
        Pet petFive = new Pet("D005", "JiaoJiao", 3);
        Pet petSix = new Pet("B002", "KaLa", 4);

        masterThree.getAdoptedPets().addPet(petFour);
        masterThree.getAdoptedPets().addPet(petFive);
        masterThree.getAdoptedPets().addPet(petSix);
        this.MASTERDATABASE.addMaster(masterThree);

        Master masterFour = new Master("LS004", "LiSi", "333");
        Pet petSeven = new Pet("C003", "AHu", 4);

        masterFour.getAdoptedPets().addPet(petSeven);
        this.MASTERDATABASE.addMaster(masterFour);
    }

    public void run() throws IOException  {
        stdOut.println("\033[34m" + "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=++=+=++=+=++=+=++=+=++=+=+");
        stdOut.println("                                   欢迎来到宠物领养系统");
        stdOut.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=++=+=++=+=+=+=++=+=++=+=++=+=+");

        int  mainChoice = getMainChoice();

        switch (mainChoice){
            case 1:
                masterChoice(getMasterChoice());
                break;
            case 2:
                BufferedReader passwordFile = new BufferedReader(new InputStreamReader(new FileInputStream("password.dat")));

                stdOut.print("密码(默认为999,可在password.dat中修改)> ");
                stdOut.flush();

                String input = stdIn.readLine();
                String password = passwordFile.readLine();

                while (!input.equals(password)){
                    stdOut.print("密码错误，请重试> ");
                    stdOut.flush();

                    input = stdIn.readLine();
                }

                adminOperation();
                break;
            case 3:
                System.exit(1);
                break;
            default:
                break;
        }
    }

    public int getMainChoice() throws IOException{
        int  input;

        do  {
            try  {
                stdOut.println("请输入您的身份：");
                stdOut.print("                                    [1] 我是领/弃养人\n"
                        + "                                    [2] 我是管理员\n"
                        + "                                    [3] 退出系统\n"
                        + "请选择> ");
                stdOut.flush();

                input = Integer.parseInt(stdIn.readLine());

                if (1 <= input && 3 >= input)  {
                    break;
                } else {
                    stdOut.println("输入错误，请重试> ");
                    stdOut.flush();
                }
            } catch (NumberFormatException  e)  {
                stdOut.println("输入错误，请重试> ");
                stdOut.flush();
            }
        }  while (true);

        return  input;
    }

    public int getMasterChoice() throws IOException{
        int input;

        do  {
            try  {
                stdOut.print("                                    [1] 注册\n"
                        + "                                    [2] 登录\n"
                        + "                                    [3] 返回\n"
                        + "请选择> ");
                stdOut.flush();

                input = Integer.parseInt(stdIn.readLine());

                if (1 <= input && 3 >= input)  {
                    break;
                } else {
                    stdOut.println("输入错误，请重试> ");
                    stdOut.flush();
                }
            } catch (NumberFormatException  e)  {
                stdOut.println("输入错误，请重试> ");
                stdOut.flush();
            }
        }  while (true);

        return  input;
    }

    public int getMasterFinalChoice() throws IOException{
        int  input;

        do  {
            try  {
                stdOut.print("                                    [1] 查看\n"
                        + "                                    [2] 领养\n"
                        + "                                    [3] 弃养\n"
                        + "                                    [4] 退出登录（退出后您的弃领信息将无法保存）\n"
                        + "请选择> ");
                stdOut.flush();

                input = Integer.parseInt(stdIn.readLine());

                if (1 <= input && 4 >= input)  {
                    break;
                } else {
                    stdOut.println("输入错误，请重试> ");
                    stdOut.flush();
                }
            } catch (NumberFormatException  nfe)  {
                stdOut.println("输入错误，请重试> ");
                stdOut.flush();
            }
        }  while (true);

        return  input;
    }

    public int getAdminFinalChoice() throws IOException{
        int  input;

        do  {
            try  {
                stdOut.println("========================================================================================");
                stdOut.println("                                  宠物管理系统后台");
                stdOut.println("========================================================================================");
                stdOut.print("                                    [1] 查看客户信息\n"
                        + "                                    [2] 储存（只能储存特定信息）\n"
                        + "                                    [3] 退出\n"
                        + "请选择> ");
                stdOut.flush();

                input = Integer.parseInt(stdIn.readLine());

                if (1 <= input && 3 >= input)  {
                    break;
                } else {
                    stdOut.println("输入错误，请重试> ");
                    stdOut.flush();
                }
            } catch (NumberFormatException  e)  {
                stdOut.println("输入错误，请重试> ");
                stdOut.flush();
            }
        }  while (true);

        return  input;
    }

    public void masterChoice(int choice) throws IOException {
        String id;
        String name;
        String password;
        switch (choice){
            case 1:
                stdOut.println("========================================================================================");
                stdOut.println("                                        注册");
                stdOut.println("========================================================================================");
                stdOut.print("请输入您的账号> ");
                stdOut.flush();
                id = stdIn.readLine();
                stdOut.print("请输入您的姓名> ");
                stdOut.flush();
                name = stdIn.readLine();
                stdOut.print("请输入您的密码> ");
                stdOut.flush();
                password = stdIn.readLine();

                Master newMaster = new Master(id, name, password);
                masterDataBase.addMaster(newMaster);
                writeFile("masterDataBase.dat", newMaster.toString());

                stdOut.println("注册成功！");

                stdOut.print("输入0继续> ");
                stdOut.flush();
                String input = stdIn.readLine();

                while (true){
                    if (input.equals("0")){

                        masterChoice(getMasterChoice());

                        break;
                    }else {
                        stdOut.print("输入错误，输入0继续> ");
                        stdOut.flush();
                        input = stdIn.readLine();
                    }
                }
                break;
            case 2:
                stdOut.println("========================================================================================");
                stdOut.println("                                        登录");
                stdOut.println("========================================================================================");
                do {
                    try {
                        stdOut.print("请输入您的账号> ");
                        stdOut.flush();
                        id = stdIn.readLine();
                        stdOut.print("请输入您的密码> ");
                        stdOut.flush();
                        password = stdIn.readLine();

                        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("masterDataBase.dat")));

                        String masterInfo = in.readLine();


                        while (!masterInfo.startsWith(id)) {
                            masterInfo = in.readLine();
                        }

                        StringTokenizer tokenizer = new StringTokenizer(masterInfo, DELIM);

                        Master master = new Master(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());

                        if (password.equals(master.getPassword())) {
                            stdOut.println("登陆成功！");
                            masterOperantion(master, getMasterFinalChoice());
                            break;
                        } else {
                            stdOut.print("密码错误，请重试> ");
                            stdOut.flush();
                        }
                    } catch (NullPointerException e){
                        stdOut.print("不存在此账号，请重试> ");
                        stdOut.flush();
                    }
                 }while (true);
                break;
            case 3:
                run();
                break;
            default:
                break;
        }
    }

    public void masterOperantion(Master master, int choice) throws IOException {

        if (choice == 4){
            masterChoice(getMasterChoice());
        }else if (choice == 1){
            stdOut.println("========================================================================================");
            stdOut.println("                                      个人中心");
            stdOut.println("========================================================================================");
            displayMaster(master);
        }else if (choice == 2){
            stdOut.println("========================================================================================");
            stdOut.println("                                     宠物领养中心");
            stdOut.println("========================================================================================");
            adoptPet(master);
        }else if (choice == 3){
            stdOut.println("========================================================================================");
            stdOut.println("                                     宠物弃养中心");
            stdOut.println("========================================================================================");
            abandonPet(master);
        }

        stdOut.print("输入0继续> ");
        stdOut.flush();
        String input = stdIn.readLine();

        while (true){
            if (input.equals("0")){
                break;
            }else {
                stdOut.print("输入错误，输入0继续> ");
                stdOut.flush();
                input = stdIn.readLine();
            }
        }

        masterOperantion(master, getMasterFinalChoice());
    }

    public void adminOperation() throws IOException {
        int choice = getAdminFinalChoice();

        if (choice == 3){
            run();
        }else if (choice == 1){
            stdOut.println("宠物主人账号_宠物主人姓名_宠物主人密码");
            displayAdoptionDataBase();
        }else if(choice == 2){
            stdOut.println("========================================================================================");
            stdOut.println("                           领养人信息储存中心（只能储存特定信息）");
            stdOut.println("========================================================================================");
            stdOut.print("                                    [1] Plain Text\n" +
                    "                                    [2] HTML\n" +
                    "                                    [3] XML\n");

            do {
                try {
                    stdOut.print("请选择储存形式> ");
                    stdOut.flush();
                    int input = Integer.parseInt(stdIn.readLine());

                    while (input < 1 || input > 3){
                        stdOut.println("输入错误，请重试> ");
                        stdOut.flush();
                        input = Integer.parseInt(stdIn.readLine());
                    }

                    if (input == 1){
                        this.adoptionDataBaseFormater = PlainTextDataBaseFormatter.getSingletonInstance();
                        rewriteFile(readFilename(), this.adoptionDataBaseFormater.formatAdoptionDataBase(this.MASTERDATABASE));
                        stdOut.println("储存成功！");
                        break;
                    }else if (input == 2){
                        this.adoptionDataBaseFormater = HTMLDataBaseFormatter.getSingletonInstance();
                        rewriteFile(readFilename(), this.adoptionDataBaseFormater.formatAdoptionDataBase(this.MASTERDATABASE));
                        stdOut.println("储存成功！");
                        break;
                    }else if (input == 3){
                        this.adoptionDataBaseFormater = XMLDataBaseFormatter.getSingletonInstance();
                        rewriteFile(readFilename(), this.adoptionDataBaseFormater.formatAdoptionDataBase(this.MASTERDATABASE));
                        stdOut.println("储存成功！");
                        break;
                    }
                }catch (NumberFormatException e){
                    stdOut.println("输入错误，请重试> ");
                    stdOut.flush();
                }
            }while (true);
        }

        stdOut.print("输入0继续> ");
        stdOut.flush();
        String input = stdIn.readLine();

        while (true){
            if (input.equals("0")){
                break;
            }else {
                stdOut.print("输入错误，输入0继续> ");
                stdOut.flush();
                input = stdIn.readLine();
            }
        }

        adminOperation();
    }

    public void displayAdoptionDataBase(){
        if (this.masterDataBase.getNumberOfMasters() == 0){
            stdErr.println("领养者数据库为空！");
        }else{
            for (Master master : this.masterDataBase){
                stdOut.println(master.toString());
            }
        }
    }

    public void displayMaster(Master master){
        stdOut.println("我的账号： " + master.getId());
        stdOut.println("我的姓名： " + master.getName());
        stdOut.println("我的密码： " + master.getPassword());

        stdOut.print("我的宠物信息： ");

        if (master.getAdoptedPets().getNumberOfPets() > 0){
            stdOut.println();
            for (Pet pet : master.getAdoptedPets()){
                stdOut.println(pet.toString());
            }
        }else {
            stdOut.println("您还没有领养宠物哦！快去领养宠物吧！");
        }
    }

    public void adoptPet(Master master) throws IOException {
        if (this.petCatalog.getNumberOfPets() > 0){
            for (Pet pet : this.petCatalog){
                stdOut.println(pet.introduceMyself());

                if (pet instanceof Dog) {
                    if (Math.random() > 0.5 ? true : false){
                        stdOut.println("---- " + ((Dog) pet).eat());
                    }else {
                        stdOut.println("---- " + ((Dog) pet).wag());
                    }
                }else if (pet instanceof Cat){
                    if (Math.random() > 0.5 ? true : false){
                        stdOut.println("---- " + ((Cat) pet).eat());
                    }else {
                        stdOut.println("---- " + ((Cat) pet).play());
                    }
                }else if (pet instanceof Bird){
                    if (Math.random() > 0.5 ? true : false){
                        stdOut.println("---- " + ((Bird) pet).fly());
                    }else {
                        stdOut.println("---- " + ((Bird) pet).sing());
                    }
                }
            }

            do  {
                stdOut.print("请输入您要领养的宠物编号> ");
                stdOut.flush();

                String code = stdIn.readLine();

                int flag = 0;

                for (Pet pet : this.petCatalog){
                    if (code.equals(pet.getCode())){
                        master.getAdoptedPets().addPet(pet);

                        this.petCatalog.removeItem(pet);

                        stdOut.println("领养成功！");
                        flag = 1;
                        break;
                    }
                }

                if (flag == 1){
                    break;
                }else {
                    stdOut.print("当前没有此宠物，请重试> ");
                    stdOut.flush();
                }

            } while (true);

        }else {
            stdOut.println("很抱歉，当前没有可供领2" +
                    "养的宠物！");
        }
    }

    public void abandonPet(Master master) throws IOException {
        if (master.getAdoptedPets().getNumberOfPets() > 0){
            do  {
                stdOut.print("请输入您要弃养的宠物编号> ");
                stdOut.flush();

                String code = stdIn.readLine();

                int flag = 0;

                for (Pet pet : master.getAdoptedPets()){
                    if (code.equals(pet.getCode())){

                        stdOut.println("您确定要弃养这只可爱的宠物吗？");
                        stdOut.print("[1] 狠心弃养\n" +
                                "[2] 还是算了吧\n" +
                                "请输入您的选择> ");
                        stdOut.flush();

                        int choice = Integer.parseInt(stdIn.readLine());

                        if (choice == 1){
                            master.getAdoptedPets().removePet(pet);

                            this.petCatalog.addItem(pet);

                            stdOut.println("弃养成功！");
                            flag = 1;
                            break;
                        }else if (choice == 2){
                            flag = 2;

                            break;
                        }else {
                            stdOut.println("输入错误，默认您不愿狠心丢弃！");
                            flag = 2;

                            break;
                        }
                    }
                }

                if (flag == 1 || flag == 2){
                    break;
                }else if (flag == 0){
                    stdOut.println("您未拥有此宠物！请重试！");
                }

            } while (true);
        }else {
            stdOut.println("您还没有领养宠物哦！快去领养宠物吧！");
        }
    }

    public void writeFile(String filename, String content) throws IOException {
        File file = new File(filename);
        FileWriter fileWriter = new FileWriter(file, true);

        fileWriter.write(content + NEW_LINE);

        fileWriter.close();
    }

    public void rewriteFile(String filename, String content) throws IOException {
        File file = new File(filename);
        FileWriter fileWriter = new FileWriter(file, false);

        fileWriter.write(content);

        fileWriter.close();
    }

    public  String readFilename() throws IOException  {

        stdOut.print("文件名> ");
        stdOut.flush();

        return stdIn.readLine();
    }
}
