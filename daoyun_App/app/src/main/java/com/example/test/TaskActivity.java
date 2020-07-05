package com.example.test;

public class TaskActivity {
    private String taskName;
    private String participantNum;
    private String participateOrNot;
    private String activityExperience;
    private String timeLimit;

    public TaskActivity(String taskName, String participantNum, String participateOrNot,
                        String activityExperience, String timeLimit){
        this.taskName = taskName;
        this.participantNum = participantNum;
        this.participateOrNot = participateOrNot;
        this.activityExperience = activityExperience;
        this.timeLimit = timeLimit;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getParticipantNum() {
        return participantNum;
    }

    public String getParticipateOrNot() {
        return participateOrNot;
    }

    public String getActivityExperience() {
        return activityExperience;
    }

    public String getTimeLimit() {
        return timeLimit;
    }
}
