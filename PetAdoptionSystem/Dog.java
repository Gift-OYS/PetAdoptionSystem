public class Dog extends Pet {
    private String sex;

    public Dog(String code, String name, int age, String sex) {
        super(code, name, age);
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public String eat(){
        return "快看啊！" + super.getName() + "正在吃东西呢。";
    }

    public String wag(){
        return "快看啊！" + super.getName() + "正在摇尾巴呢。";
    }

    public String introduceMyself(){
        return super.introduceMyself() + "，我是一条小狗，是" + getSex() + "哦！快把我领回家吧！";
    }

    public String toString(){
        return super.toString() + "_" + getSex();
    }
}
