package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    public String getMode() {
        return "CSV";
    }

    // #5レシピ一覧表示機能
    // recipes.csvからレシピデータを読み込み、それをリスト形式で返します。
    public ArrayList<Recipe> readData() throws IOException {
        // ArrayList形式で読み込みデータ保存
        ArrayList<Recipe> recipes = new ArrayList<>();
        // try文、一行ずつ読み込み
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // ファイルから一行ずつ読み込んだものをいれる
            String line;
            // ファイルから一行ずつ読み込み、最終行のnullまで繰り返す
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(",", 2);
                String name = str[0];
                // str[1] 文字列
                String[] str2 = str[1].split(",");
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (String strIngredient : str2) {
                    // System.out.println(strIngredient);
                    Ingredient ingredient = new Ingredient(strIngredient);
                    ingredients.add(ingredient);
                }
                Recipe recipe = new Recipe(name, ingredients);
                recipes.add(recipe);
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: 例外のメッセージ");
            // 例外情報の表示
            ex.printStackTrace();
        }
        return recipes;
    }

    public void writeData(Recipe recipe) throws IOException {
        // tryで一行ずつ追加
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // レシピ名を書き込む処理
            writer.write(recipe.getName());
            // 材料書き込み
            // Ingredientクラスのデータ型を使用し、RecipeクラスのgetIngredientsにいれる
            for(Ingredient ingredient : recipe.getIngredients()){
                // カンマ区切り
                // 入力したレシピ内容を入れる処理
                writer.write("," + ingredient.getName());
            }
            writer.write("\n");
        } catch (IOException ex) {
            // 例外情報の表示
            ex.printStackTrace();
        }
    }

    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }

}
