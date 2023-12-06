package com.example.hackathon;

public class ArticleModel {

    String articleTitle;
    String articleAuthor;

    String articleContent;
    int articleImage;

    public ArticleModel(String articleTitle, String articleAuthor, int articleImage, String articleContent) {
        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
        this.articleContent = articleContent;
        this.articleImage = articleImage;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public int getArticleImage() {
        return articleImage;
    }

    public String getArticleContent() {
        return articleContent;
    }

}
