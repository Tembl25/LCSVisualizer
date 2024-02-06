package com.example.laba3_proekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LCSVisualizerController {
    @FXML
    private TextField inputField1;

    @FXML
    private TextField inputField2;

    @FXML
    private Label resultLabel;

    @FXML
    private TextArea matrixTextArea;

    @FXML
    private void visualizeLCS() {
        String str1 = inputField1.getText();
        String str2 = inputField2.getText();

        String lcs = findLCS(str1, str2);
        resultLabel.setText("Наибольшая общая подпоследовательность: " + lcs);
    }

    private String findLCS(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Вывод матрицы в TextArea
        StringBuilder matrixStr = new StringBuilder();
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                matrixStr.append(dp[i][j]).append(" ");
            }
            matrixStr.append("\n");
        }
        matrixTextArea.setText(matrixStr.toString());

        int index = dp[m][n];
        char[] lcs = new char[index];
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs[index - 1] = str1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new String(lcs);
    }

    @FXML
    private void resetFields() {
        inputField1.clear();
        inputField2.clear();
        resultLabel.setText("");
        matrixTextArea.clear();
    }

}
