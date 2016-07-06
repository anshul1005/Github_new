package com.qait.automation.github;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class GitHubAPI {
	@SuppressWarnings("deprecation")
	public void gitApiMethod(){
		
		try {
			GitHub github = GitHub.connectUsingPassword(TestData.getvalue("username"), TestData.getvalue("password"));

			GHRepository repo=github.createRepository("new_repo", "sample_api_repo", "abc", true);
			System.out.println(repo.getUrl());
		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}
}