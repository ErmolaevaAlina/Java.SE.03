package com.company;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Nick on 27.03.2016.
 */
public class InternationalQuiz {

    private LanguageBundle questions;
    private LanguageBundle answers;

    public InternationalQuiz(String locale) {
        questions = LanguageBundle.getBundle("questions", locale);
        answers =  LanguageBundle.getBundle("answers", locale);
    }

    public InternationalQuiz() {
        this("en");
    }

    private void setLocale(String locale) {
        questions = LanguageBundle.getBundle("questions", locale);
        answers = LanguageBundle.getBundle("answers", locale);
    }

    private String question(int number) {
        return questions.getString(number);
    }

    private String answer(int number) {
        return answers.getString(number);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine().toLowerCase()) {
            case "1":
            case "русский":
                setLocale("ru");
                break;
            case "2":
            case "english":
            default:
                setLocale("en");
                break;
        }
        String input;
        while(scanner.hasNext() && !(input = scanner.nextLine()).equals("exit")) {
            int number = Integer.valueOf(input);
            System.out.println(question(number));
            System.out.println(answer(number));
        }
    }


}
