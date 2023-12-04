package com.example.hackathon;

public class ArticleModel {

    String articleTitle;
    String articleAuthor;
    int image;

    public ArticleModel(String articleTitle, String articleAuthor, int image) {
        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
        this.image = image;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public int getImage() {
        return image;
    }
}
