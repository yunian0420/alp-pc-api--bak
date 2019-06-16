/*package com.lphr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lphr.entity.Candidate;
import com.lphr.mapper.CandidateMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlpPcApiApplicationTests {

	// FIXME
	
	// TODO
	
	// XXX
	
	@Autowired
	private CandidateMapper candidateMapper;
	
	@Test
	public void contextLoads() {
		int id = 1;
		Candidate c = candidateMapper.selectByPrimaryKey(id );
		System.out.println("-----------------YYCC--------------------" + c.getCandidateName());
	}
	
	@Test
	public void test1() {
		Candidate query = new Candidate();
		query.setCandidateName("张明");
		int count = candidateMapper.selectCount(query);
		System.out.println("-----------------YYCC--------------------" + count);
	}
	
	@Test
	public void test2() {
		int id = 1;
		Candidate c = candidateMapper.selectByPrimaryKey(id );
		System.out.println("-----------------YYCC--------------------" + c.getCandidateName());
	}

}
*/