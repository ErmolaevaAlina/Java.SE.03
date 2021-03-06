import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class HtmlParser {
    private long currentLineNumber;
    private int currentPicture;
    private int currentReference;
    private String currentLine;


    Pattern linkPattern = Pattern.compile(".*<img.*/pic(\\d+)\\.jpg\".*>.*");
    Pattern referencePattern = Pattern.compile(".*[Рр]ис[\\.а-яА-Я]+\\s(\\d{1,2}).*");
    Pattern sentencePattern = Pattern.compile("[^.]*[Рр]ис[\\.а-яА-Я]+\\s\\d{1,2}+[^.]*\\.");
    Matcher matcher;

    public static void main(String[] args) throws IOException {
        new HtmlParser().parse("C:\\Users\\Livon\\Desktop\\se03task3\\file\\file.html");
        System.out.println();
    }

    public void parse(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while((currentLine = reader.readLine()) != null) {
                currentLineNumber++;
                if(containsLink())
                    currentPicture = getCurrentPicture();
                if(containsReference()) {
                    currentReference = getReference();
                    if(currentReference > currentPicture)
                        System.err.println("Wrong reference at line: " + currentLineNumber +
                                ". Referenced picture " + currentReference +
                                " while last introduced picture was " + currentPicture);
                    Stream.of(extractSentences()).forEach(System.out::println);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean containsLink() {
        matcher = linkPattern.matcher(currentLine);
        return matcher.matches();
    }

    private boolean containsReference() {
        matcher = referencePattern.matcher(currentLine);
        return matcher.matches();    }

    private int getCurrentPicture() {
        return Integer.parseInt(matcher.group(1));
    }

    private int getReference() {
        return Integer.parseInt(matcher.group(1));
    }

    private List<String> extractSentences() {
        List<String> sentences = new LinkedList<>();
        matcher = sentencePattern.matcher(currentLine);
        while(matcher.find())
            sentences.add(matcher.group().replaceAll("</?.{1,5}>", "").replaceAll("&nbsp;", ""));
        return sentences;
    }
}
