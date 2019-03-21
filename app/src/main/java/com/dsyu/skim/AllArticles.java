package com.dsyu.skim;

public class AllArticles {
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

    public int getNumOfArticles() {
        return numOfArticles;
    }

    public int getCurrentArticleNum() {
        return currentArticleNum;
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
