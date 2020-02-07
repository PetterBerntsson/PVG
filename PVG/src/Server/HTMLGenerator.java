package Server;

import java.io.BufferedReader;

public class HTMLGenerator {

    public HTMLGenerator() {

    }


    public String parseToHTML(String input) {

        String[][] tableMatrix = makeTableMatrix(input);

        String header = "";

        //Formaterar Table-header
        for(int i = 0; i < tableMatrix[0].length; i++) {
            header = header + "<th>" + tableMatrix[0][i] + "</th> \n";
        }
        header = "<tr>\n" + header.trim() + "\n</tr>";


        String body = "";

        //hoppa över första index, eftersom det är Header
        for(int i = 1; i < tableMatrix.length; i ++) {
            body = body + "<tr>\n";
            for(int j = 0; j < tableMatrix[i].length; j++) {
                body = body + "<td>" + tableMatrix[i][j] + "</td>\n";
            }

            body = body + "</tr>\n";
        }


        String page = "<table style=\"width:50%\">\n" + header + "\n" + body.trim() + "\n" + "</table>";

        System.out.println(page);

        return page;
    }


    private String[][] makeTableMatrix(String input) {
        String[] rows = input.trim().split("\n");
        String[][] table = new String[rows.length][rows[0].split(";").length]; //[antal rader][antal element i en rad]

        for(int i = 0; i < rows.length; i++) {
            table[i] = rows[i].split(";");
        }

        return table;
    }
}
