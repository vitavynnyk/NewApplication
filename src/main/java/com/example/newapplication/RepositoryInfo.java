package com.example.newapplication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RepositoryInfo {
    @JsonProperty("Repository Name")
    private String repositoryName;
    @JsonProperty("Owner Login")
    private String ownerLogin;
    @JsonProperty("Branches")
    private List<BranchInfo> branches;


    @Getter
    public static class BranchInfo {
        private String name;
        private Commit commit;

        @Getter
        public static class Commit {
            private String sha;

        }
    }

}



