package model;

public class Scoreboard {
    private Score root;

    public Scoreboard() {
        this.root = null;
    }

    public Score getRoot() {
        return root;
    }

    public void setRoot(Score root) {
        this.root = root;
    }

    public void addScore(Score score) {
        if (root == null) {
            root = score;
        } else {
            addScore(root, score);
        }
    }

    private void addScore(Score current, Score newScore) {
        if (newScore.getScore() < current.getScore()) {
            if (current.getLeft() == null) {
                current.setLeft(newScore);
            } else {
                addScore(current.getLeft(), newScore);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newScore);
            } else {
                addScore(current.getRight(), newScore);
            }
        }
    }

    public void inOrderTraversal(Score current) {
        if (current != null) {
            inOrderTraversal(current.getLeft());
            System.out.println(current.getSymbol() + " - " + current.getScore());
            inOrderTraversal(current.getRight());
        }
    }

    public void printScores() {
        inOrderTraversal(root);
    }
}
