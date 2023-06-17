package com.assignment.suzume.tictactoe.board.rules;

import java.io.*;


public enum Rule {
    VARIANT("src/main/java/com/assignment/suzume/tictactoe/board/rules/VariantBoard.txt"),
    REGULAR("src/main/java/com/assignment/suzume/tictactoe//board/rules/RegularBoard.txt"),
    REVERSE("src/main/java/com/assignment/suzume/tictactoe//board/rules/ReverseBoard.txt");

    private String url;
    private String content;

    Rule(String url) {
        this.url = url;
        readContent();
    }

    private void readContent() {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(this.url))) {
            int byteRead;
            char[] buff = new char[8096];
            while((byteRead = reader.read(buff)) != -1) {
                sb.append(buff, 0, byteRead);
            }
            this.content = sb.toString();
        } catch (IOException e) {} 
    }

    public String getContent() {
        if(this.content == null)
            readContent();
        return this.content;
    }
}
