package com.dsyu.skim;

public class AllArticles {
    private int numOfArticles = 30;
    private int currentArticle = 0;
    private Article[] allArticles = new Article[numOfArticles];

    public void setNumOfArticles(int responses) {
        if (responses < numOfArticles) {
            numOfArticles = responses;
        }
    }

    public void setArticleAtIndex(int i, Article article) {
        allArticles[i] = article;
    }

    public Article getCurrentArticle() {
        return allArticles[currentArticle];
    }

    public Article getNextArticle() {
        if (currentArticle == numOfArticles - 1) {
            currentArticle = 0;
        }
        else {
            currentArticle++;
        }
        return allArticles[currentArticle];
    }

    public Article getPreviousArticle() {
        if (currentArticle == 0) {
            currentArticle = numOfArticles - 1;
        }
        else {
            currentArticle--;
        }
        return allArticles[currentArticle];
    }

}
