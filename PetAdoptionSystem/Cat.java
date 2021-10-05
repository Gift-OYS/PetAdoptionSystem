public class Cat extends Pet {
    public Cat(String code, String name, int age) {
        super(code, name, age);
    }

    public String eat(){
        return "快看啊！" + super.getName() + "正在吃东西呢。";
    }

    public String play(){
        return "快看啊！" + super.getName() + "正在玩呢。";
    }

    public String introduceMyself(){
        return super.introduceMyself() + "，是一只可爱的小猫咪哦！快把我领回家吧！";
    }

    public String toString(){
        return super.toString();
    }
}
