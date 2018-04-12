package com.company;

import java.util.Locale;

public class LanguageBundle {

    private String[] questionsRu = new String[] { "Вопрос 1", "Вопрос 2"};
    private String[] questionsEn = new String[] { "Question 1", "Question 2"};

    private String[] answersRu = new String[] { "Ответ 1", "Ответ 2"};
    private String[] answersEn = new String[] { "Answer 1", "Answer 2"};

    private String[] currentArray;

    public LanguageBundle(String key, String locale)
    {
        if (key == "questions")
        {
            if (locale == "ru")
            {
                currentArray = questionsRu;
            }
            else if (locale == "en")
            {
                currentArray = questionsEn;
            }
        } else if (key == "answers"){
            if (locale == "ru")
            {
                currentArray = answersRu;
            }
            else if (locale == "en")
            {
                currentArray = answersEn;
            }
        }
    }

    public String getString(int index)
    {
        return currentArray[index];
    }


    public static LanguageBundle getBundle(String key, String locale)
    {
        return new LanguageBundle(key, locale);
    }
}
