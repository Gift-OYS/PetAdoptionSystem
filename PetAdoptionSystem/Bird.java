public class Bird extends Pet {
    private String color;

    public Bird(String code, String name, int age, String color) {
        super(code, name, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String fly(){
        return "快看啊！" + super.getName() + "正在飞呢。";
    }

    public String sing(){
        return "快看啊！" + super.getName() + "正在唱歌呢。";
    }

    public String introduceMyself(){
        return super.introduceMyself() + "，是一只" + getColor() + "的小鸟哦！快把我领回家吧！";
    }

    public String toString(){
        return super.toString() + "_" + getColor();
    }
}
