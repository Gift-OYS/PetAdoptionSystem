public class Pet {
    private String code;
    private String name;
    private int age;

    public Pet(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean equals(Object object){
        return object instanceof Pet && getCode().equals(((Pet) object).getCode());
    }

    public String introduceMyself(){
        return "---- 你好！我叫" + getName() + "，今年" + getAge() + "岁了，我的编号是" + getCode();
    }

    public String toString(){
        return getCode() + "_" + getName() +"_" + getAge();
    }
}
