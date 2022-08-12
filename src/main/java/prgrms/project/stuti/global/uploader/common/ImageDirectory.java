package prgrms.project.stuti.global.uploader.common;

import lombok.Getter;

@Getter
public enum ImageDirectory {
	MEMBER("images/members"), POST("images/posts"), STUDY_GROUP("images/study-groups");

	private final String directory;

	ImageDirectory(String directory) {
		this.directory = directory;
	}
}
