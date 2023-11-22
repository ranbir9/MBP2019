package assign02;

import java.util.ArrayList;

public class CS2420Student extends UofUStudent {
    EmailAddress contactinfo; 
    ArrayList<Double> assignmentScores = new ArrayList<>(); 
    ArrayList<Double> labScores = new ArrayList<>();
    ArrayList<Double> quizScores = new ArrayList<>(); 
    ArrayList<Double> examScores = new ArrayList<>();

    

public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo){
    super(firstName, lastName, uNID);
    this.contactinfo = contactInfo; 
}

public EmailAddress getContactInfo(){
    return contactinfo;  
}

public void addScore(double score, String category){
    
    
        
    switch(category.toLowerCase()){
        case "quiz":
            quizScores.add(score);
            break; 
        case "lab": 
            labScores.add(score); 
            break; 
        case "exam": 
            examScores.add(score); 
            break; 
        case "assignment":
            assignmentScores.add(score); 
            break; 
        default: 
            break; 
    }
}


//average for assignments
public double scoreAverage(String category){
    int points = 0; 
    double average = 0; 
    ArrayList<Double> scores;

    switch(category.toLowerCase()){// Decides which scores to use 
        case "quiz":
            scores = quizScores; 
            break; 
        case "lab": 
            scores = labScores;
            break; 
        case "exam": 
            scores = examScores; 
            break; 
        case "assignment":
            scores = assignmentScores;
            break; 
        default: 
            throw new IllegalArgumentException("Invalid category!");  
    }

    

    
    for (double score : scores ){
        points += score; 
    }
    average = points / (scores.size()); 
    return average; 
}

private boolean oneScoreInCategories(){
    if(examScores.size() < 1 || assignmentScores.size() < 1 || labScores.size() < 1 || quizScores.size() < 1)
        return false; 
    return true; 
}


public double computeFinalScore() {
    double weightedQuiz;
    double weightedAssignment; 
    double weightedLab; 
    double weightedExam; 
    double score; 

    

    if(!oneScoreInCategories())//checks if each category has 1 or more scores at least, returns 0 if it doesn't
        return 0.0; 

    if(scoreAverage("exam") < 65)// if score average is less than 65, final score is exam score, returns exam average
        return scoreAverage("exam");

    weightedQuiz = scoreAverage("quiz") * .1;
    weightedAssignment = scoreAverage("assignment") * .4; 
    weightedLab = scoreAverage("lab") * .1; 
    weightedExam = scoreAverage("exam") * .4; 

    score = weightedAssignment + weightedExam + weightedLab + weightedQuiz; 
    System.out.println();
    return score;
    } 


    public String computeFinalGrade(){
        double finalGrade = computeFinalScore(); 

        if(!oneScoreInCategories())//checks if each category has 1 or more scores at least, returns 0 if it doesn't
            return "N/A";

        if(finalGrade >= 93.0){
            return "A"; 
        } else if (finalGrade >= 90) {
            return "A-";
        } else if (finalGrade >= 87){
            return "B+"; 
        } else if (finalGrade >= 83){
            return "B"; 
        } else if (finalGrade >= 80){
            return "B-"; 
        } else if (finalGrade >= 77){
            return "C+"; 
        } else if (finalGrade >= 73){
            return "C"; 
        } else if (finalGrade >= 70){
            return "C-"; 
        }else if (finalGrade >= 67){
            return "D+"; 
        } else if (finalGrade >= 63){
            return "D"; 
        } else if (finalGrade >= 60){
            return "D-"; 
        } else {
            return "E"; 
        }



        }
        
    }

