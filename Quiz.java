import java.util.*;

class Question{
    private char[] ans = new char[3];
    private int score = 0;
    Scanner sc = new Scanner(System.in);

    private char showQuestion(String qus,String opt){

        while (true) {
            System.out.println(qus);
            System.out.println(opt);
            String line = sc.nextLine().trim();
            if(!line.isEmpty()){
                char ch = Character.toUpperCase(line.charAt(0));
                if(validate(ch)){
                    return ch;
                }
            }
        System.out.println("Enter valid choice");
        }
    }
    
    public Boolean validate(char ch){

        if(ch==65||ch==66||ch==67||ch==68){
            return true;
        }

        return false;
    }

    public void easy(){

        ans[0]=showQuestion("Which is the largest continent(By population) in the world?",
                            "A) Africa   B) Asia   C) South America  D) Australia");
        ans[1]=showQuestion("Which is the longest river in the world?",
                            "A) Indus   B) Yellow   C) Amazon  D) Nile");
        ans[2]=showQuestion("Which country is known as the Land of the Rising Sun?",
                            "A) New Zealand   B) Japan   C) South Korea  D) Australia");
        
    }

    public void medium(){

        ans[0]=showQuestion("Which strait separates India and Sri Lanka?",
                            "A) Malacca Strait   B) Palk Strait   C) Bering Strait  D) Hormuz Strait");
        ans[1]=showQuestion("Which is the highest plateau in the world?",
                            "A) Deccan Plateau   B) Tibetan Plateau   C) Iranian Plateau  D) Colorado Plateau");
        ans[2]=showQuestion("Which African country has the largest population?",
                            "A) Egypt   B) South Africa   C) Nigeria  D) Kenya");

    }

    public void hard(){

        ans[0]=showQuestion("Which country has the greatest number of natural lakes?",
                            "A) Russia   B) Canada   C) USA  D) Finland");
        ans[1]=showQuestion("Which mountain range separates Europe from Asia?",
                            "A) Alps   B) Andes   C) Ural Mountains  D) Himalayas");
        ans[2]=showQuestion("Which desert is the largest non-polar desert in the world?",
                            "A) Gobi Desert   B) Kalahari Desert   C) Sahara Desert  D) Arabian Desert");

    }

    public char[] Check(String level){

        if(level.equals("Easy")){
            char[] key = {'B','D','B'};

            for (int i = 0; i < key.length; i++) {
                if(key[i] == ans[i]){
                    score+=5;
                    continue;
                }
                score-=2;
            }
            return key;
        }
        else if(level.equals("Medium")){
            char[] key = {'B','B','C'};

            for (int i = 0; i < key.length; i++) {
                if(key[i] == ans[i]){
                    score+=5;
                    continue;
                }
                score-=2;
            }
            return key;
        }
        else {
            char[] key = {'B','C','C'};

            for (int i = 0; i < key.length; i++) {
                if(key[i] == ans[i]){
                    score+=5;
                    continue;
                }
                score-=2;
            }
            return key;
        }
    }

    public void displayResult(char[] ans){
        System.out.println("Your Score is " + score + "/15");

        if(score == 15){
            System.out.println("Congratulations on a perfect score");
        }else{
            System.out.println("correct ans were:");
            for (char c : ans) {
                System.out.println(c);
            }
        }

        score = 0;
    }
}

public class Quiz {
    public static void main(String[] args){
        Question obj = new Question();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Quiz");
        System.out.println("Chose Difficulty level");
        System.out.println("Easy    Medium    Hard");
        String input = sc.nextLine().trim();

        if(input.equals("Easy")){
            obj.easy();
            char[]key = obj.Check(input);
            obj.displayResult(key);
        }
        else if(input.equals("Medium")){
            obj.medium();
            char[]key = obj.Check(input);
            obj.displayResult(key);
        }
        else if(input.equals("Hard")){
            obj.hard();
            char[]key = obj.Check(input);
            obj.displayResult(key);
        }
        else{
            System.out.println("Enter valid choise");
        }
        sc.close();
    }
}
