package com.lphr.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "candidate")
public class Candidate {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String candidateName;
    private Integer postId;
    private Integer resumeChannel;
    private Integer resumeSource;
    private Integer candidateSex;
    private Integer candidateAge;
    private String candidatePhone;
    private String candidateEmail;
    private Integer candidateExperience;
    private String candidateEducation;
    private String candidateLocation;
    private Integer enterpriseId;
    private String originalResumeAddress;
    private Integer commendEmployeeId;
    private Integer departmentHeads;
    private String candidateTag;
    private String archivingReason;
    private String detailedReasons;
    private Date createTime;
    private Date updateTime;
    private String standardResume;
    
}