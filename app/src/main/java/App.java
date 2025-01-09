import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        // try catch文
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            // if文で1,2,その他の出力を記載
            // 1,その他でCSVDataHandlerのインスタンス化
            if ("1".equals(choice)) {
                CSVDataHandler csvDataHandler = new CSVDataHandler();
                System.out.println("Current mode: CSV");
                // 2でJSONDataHandlerのインスタンス化
            } else if ("2".equals(choice)) {
                JSONDataHandler jsonDataHandler = new JSONDataHandler();
                System.out.println("Current mode: JSON");
                // 1,その他でCSVDataHandlerのインスタンス化
            } else {
                CSVDataHandler csvDataHandler = new CSVDataHandler();
                System.out.println("Current mode: CSV");
            }

            // メインメニューを出力するためにインスタンス化
            RecipeUI recipeUI = new RecipeUI();
            recipeUI.displayMenu();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
