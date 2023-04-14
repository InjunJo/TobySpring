package user.domain;

public enum Level {

    GOLD(4,null), SILVER(3,GOLD),BRONZE(2,SILVER),BASIC(1, BRONZE);

    private final int value;
    private final Level next;

    Level(int value,Level next){
        this.value = value;
        this.next = next;
    }

    public int intValue(){
        return value;
    }

    public Level nextLevel(){
        return this.next;
    }


    public static Level valueOf(int value){

        switch (value){

            case 1: return BASIC;
            case 2: return BRONZE;
            case 3: return SILVER;
            case 4: return GOLD;
            default: throw new AssertionError("unknown value : "+value);

        }

    }
}
