package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    // フィールド
    private BufferedReader reader;
    private DataHandler dataHandler;

    // RecipeUIメソッド インスタンス化 同クラスのdataHandler
    public RecipeUI(DataHandler dataHandler) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
        this.reader = reader;
    }

    // displayMenuメソッド
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        // レシピデータを取り出して整形する
        // ArrayList<Recipe> recipes = dataHandler.readData();
        // dataHandler.readData();
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            // レシピデータが１件もない場合はどう処理する？
            // レシピがなにもない状態＝ 空白？
            // レシピデータがnullってどういうこと？
            // レシピデータがnullってつまり中の要素が0件
            if (recipes.size() == 0) {
                System.out.println("No recipes available.");
                // 以降は処理を終了したい
                return;
            }

            System.out.println("Recipes:");
            System.out.println("-----------------------------------");
            for (Recipe recipe : recipes) {
                System.out.print("Recipe Name: ");
                System.out.println(recipe.getName());
                System.out.print("Main Ingredients: ");
                // そのまま出力したい。。。けどできない
                // なぜかというと材料型リストだったので、たくさんの材料型データがまとまっているじょうたいだと出力できなかったから
                // レシビ型のなかから材料型リストを取り出す
                // recipe.getIngredients()
                // ひとつひとつの材料型を取り出したい
                for (Ingredient ingredient : recipe.getIngredients()) {
                    // ひとつひとつ取り出した材料型データに対して、出力します
                    // どんなデータを出力、材料名
                    // 取り出すためには、getName
                    System.out.print(ingredient.getName() + ",");
                }
                System.out.println();
                System.out.println("-----------------------------------");

            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            // ファイル読み込み
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Adding a new recipe.");
            // recipe名書き込み
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            // ingredientsのデータ件数分繰り返し、次の処理を行う
            System.out.println("Enter ingredients (type 'done' when finished):");
            // ジェネリクス とりあえず中身のないArrayList宣言
            // 右の<>内は不要かも
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

            // 繰り返し処理
            while(true){
                System.out.print("Ingredient: ");
                String ingredientInput = reader.readLine();
                // ingredientInput2の内容が"done"であれば抜ける
                if(ingredientInput.equals("done")){
                    break;
                }
                // "done"で無ければ次の配列に書き込み続行
                Ingredient ingredient = new Ingredient(ingredientInput);
                ingredients.add(ingredient);
            }
            // Recipeオブジェクト生成
            Recipe recipe = new Recipe(recipeName , ingredients);
            // writeデータにrecipeを渡す
            dataHandler.writeData(recipe);
            // Recipe added successfully.出力
            System.out.println("Recipe added successfully.");
        } catch (IOException ex) {
            System.out.println("Failed to add new recipe: " + ex.getMessage());
        }
    }

}
