package beans;

import java.io.Serializable;

public class AnswerOption implements Serializable {
    private int id;
    private Integer questionId;
    private String text;
    private int isCorrect;

    public AnswerOption() {
    }
    
    private AnswerOption(Builder builder){
        this.id = builder.id;
        this.questionId = builder.questionId;
        this.text = builder.text;
        this.isCorrect = builder.isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOption answerOption = (AnswerOption) o;

        return id == answerOption.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "AnswerOption{" + "id=" + id + ", questionId=" + questionId + ", text=" + text + ", isCorrect=" + isCorrect + '}';
    }
    
    public static class Builder{
        private int id;
        private Integer questionId;
        private String text;
        private int isCorrect;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setQuestionId(Integer questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setIsCorrect(int isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }
        public AnswerOption build(){
            return new AnswerOption(this);
        }
        
    }
}
