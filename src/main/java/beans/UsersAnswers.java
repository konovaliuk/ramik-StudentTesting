package beans;

import java.io.Serializable;

public class UsersAnswers implements Serializable{
    private int id;
    private Integer userId;
    private Integer questionId;
    private Integer answerId;

    public UsersAnswers() {
    }
    
    private UsersAnswers(Builder builder){
        this.id = builder.id;
        this.userId = builder.userId;
        this.questionId = builder.questionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer chapterId) {
        this.userId = chapterId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersAnswers usersAnswers = (UsersAnswers) o;

        return id == usersAnswers.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "UsersAnswers{" +
                "id=" + id +
                ", userId=" + userId +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                '}';
    }

    public static class Builder{
        private int id;
        private Integer userId;
        private Integer questionId;
        private Integer answerId;
        
        public Builder setId(int id){
            this.id = id;
            return this;
        }
        
        public Builder setUserId(Integer userId){
            this.userId = userId;
            return this;
        }
        
        public Builder setQestionId(Integer questionId){
            this.questionId = questionId;
            return this;
        }
        
        public Builder setAnswerId (Integer answerId){
            this.answerId = answerId;
            return this;
        }
        
        public UsersAnswers build(){
            return new UsersAnswers(this);
        }
     }
}
