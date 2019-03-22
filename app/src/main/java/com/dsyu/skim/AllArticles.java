package com.dsyu.skim;

public class AllArticles {

    private boolean articlesLoaded = false;
    private int numOfArticles = 20;
    private int currentArticleNum = 0;
    private Article[] allArticles = new Article[numOfArticles];

    public void setNumOfArticles(int results) {
        if (results < numOfArticles) {
            numOfArticles = results;
        }
    }

    public void setArticleAtIndex(int i, Article article) {
        allArticles[i] = article;
    }

    public boolean isArticlesLoaded() {
        return articlesLoaded;
    }

    public void setArticlesLoaded(boolean articlesLoaded) {
        this.articlesLoaded = articlesLoaded;
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

    public Article getCurrentArticle() {
        return allArticles[currentArticleNum];
    }

    public Article getNextArticle() {
        if (currentArticleNum == numOfArticles - 1) {
            currentArticleNum = 0;
        }
        else {
            currentArticleNum++;
        }
        return allArticles[currentArticleNum];
    }

    public Article getPreviousArticle() {
        if (currentArticleNum == 0) {
            currentArticleNum = numOfArticles - 1;
        }
        else {
            currentArticleNum--;
        }
        return allArticles[currentArticleNum];
    }

}
